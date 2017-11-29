package com.InsulinPump.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import application.InsulinPumpDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientMainMenuController {
	
	@FXML
	Text foodLbl;
	@FXML
	Text statusLbl;
	@FXML
	Text insulinDoseLbl;
	@FXML
	Text currentLevelLbl;
	@FXML
	Text batteryLevelLbl;
	@FXML
	Text insulinLevelLbl;
	@FXML
	Text DateTimeLbl;
	@FXML
	LineChart<String, Double> lineChart;
	@FXML
	Button btnBolus, btnUserDataHistory, btnLoad;
	@FXML
	ProgressBar batteryProgressBar, insulinProgressBar;

	static int dose = 0;

	static int reading0;
	static int reading1;
	int reading2;

	static int safeMin = 60;
	static int safeMax = 120;
	static int minDose = 10;
	static int maxSingleDose = 20;
	static int vialAmount = 50;
	static int updatedVialAmount;
	static int compDose = 0;
	String ID;

	@FXML
	public void initialize() {
		Timer timer = new Timer();
		timer.schedule(new Timers(), 0, 300000); // 300000

	}

	@FXML
	private void loadChart(ActionEvent event) {
		lineChart.getData().clear();
		String query2 = "SELECT dateTime,glucoseReading FROM records ORDER BY dateTime ASC";
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("Glucose Level");

		try {
			Connection conn2 = InsulinPumpDBConfig.getConnection();
			ResultSet rs = conn2.createStatement().executeQuery(query2);
			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
			}
			lineChart.getData().add(series);
		} catch (Exception e) {

		}
	}

	public void runPump() {
		reading0 = 120; // for testing
		reading1 = 100; // for testing

		// random number generated every time for reading 2
		Random rand = new Random();
		int reading2 = rand.nextInt(220) + 60;
		currentLevelLbl.setText("Current Blood Sugar: " + reading2);

		/* Sugar reading is low */
		if (reading2 < safeMin) {
			compDose = 0;
			statusLbl.setText("Sugar is too low");
			foodLbl.setText("Eat a scone to increase blood sugar");
		}

		/* Sugar is within safe range */
		else if (reading2 >= safeMin && reading2 <= safeMax) {
			// If sugar level is stable or falling
			if (reading2 <= reading1) {
				compDose = 0;
				statusLbl.setText("Sugar is stable or falling");
				foodLbl.setText("No need to eat any food");
			}
			// If sugar level increasing
			else {
				// If rate of increase is falling
				if ((reading2 - reading1) < (reading1 - reading0)) {
					compDose = 0;
					statusLbl.setText("rate of increase is falling");
					foodLbl.setText("Eat hummus to stabilize rate of increase");
				}
				// If rate of increase is increasing
				else if ((reading2 - reading1) >= (reading1 - reading0)) {

					statusLbl.setText("rate of increase is increasing");
					foodLbl.setText("Eat peanuts to stablize rate of increase");

					// If dose is rounded to zero, deliver the min dose
					if ((reading2 - reading1) / 40 == 0) {
						// set the amount to deliver to the min dose
						compDose = minDose;
					} else if ((reading2 - reading1) / 40 > 0) {
						// Set the amount to deliver
						compDose = (reading2 - reading1) / 40;
					}
				}
			}
		}

		/* Sugar is High */
		else if (reading2 > safeMax) {

			statusLbl.setText("Sugar is High");
			foodLbl.setText("Eat some ginger to decrease blood sugar");

			// If Sugar level increasing
			if (reading2 > reading1) {

				statusLbl.setText("Sugar is High and increasing");
				foodLbl.setText("Eat some yogurt to decrease and stablize blood sugar");

				// If dose is rounded to zero, deliver the min dose
				if ((reading2 - reading1) / 40 == 0) {
					compDose = minDose;
				} else if ((reading2 - reading1) / 40 > 0) {
					// Set the amount to deliver
					compDose = (reading2 - reading1) / 40;
				}
			}
			// If the Sugar level is stable
			else if (reading2 == reading1) {
				compDose = minDose;
			}
			// If the Sugar level is falling
			else if (reading2 < reading1) {

				statusLbl.setText("Sugar level is falling");
				foodLbl.setText("Eat a bran muffin to increase and stablize blood sugar");

				// If rate of decrease increasing
				if ((reading2 - reading1) <= (reading1 - reading0)) {
					statusLbl.setText("rate of decrease is increasing");
					foodLbl.setText("Eat a corn muffin to stablize rate of decrease");
					compDose = 0;
				}
				// If rate of decrease decreasing
				else {
					statusLbl.setText("rate of decrease is decreasing");
					foodLbl.setText("Eat oatmeal to stablize rate of decrease");
					compDose = minDose;
				}
			}
		}

		if (compDose <= maxSingleDose) {
			dose = compDose;
			insulinDoseLbl.setText("Suggested Dose: " + dose + "mL");
		}
		// //If the single dose computed is too high
		else {
			dose = maxSingleDose;
			insulinDoseLbl.setText("Suggested Dose: " + dose + "mL");
		}

		if (dose > 0) {
			// Display to the user the dose delivered
			insulinDoseLbl.setText("Delivered Dose: " + dose + "mL");
		}

		// //Adjust the reading values to accomodate for the new reading

		// reading0=reading1;
		// reading1=reading2;
		// reading2=0;

		String Time = getTime();
		InsulinLeft();
		BatteryLevel();

		String query = "INSERT INTO records (dateTime, glucoseReading, insulinAmount, status, idPatient, idDoctor) VALUES (?,?,?,?,?,?);";

		try (Connection conn = InsulinPumpDBConfig.getConnection();
				PreparedStatement updateprofile = conn.prepareStatement(query);) {
			updateprofile.setString(1, Time);
			updateprofile.setString(2, "" + reading2);
			updateprofile.setString(3, "" + dose);
			updateprofile.setString(4, statusLbl.getText());
			updateprofile.setString(5, ID);
			updateprofile.setString(6, "1");
			updateprofile.execute();
			System.out.println("Record Created");
		} catch (Exception e) {
			System.out.println("Status: operation failed due to " + e);
		}

	}

	public void InsulinLeft() // Insulin left over in the reservour/vial.
	{
		// System.out.println("Vial total =" + vialAmount);
		updatedVialAmount = (vialAmount - dose);
		vialAmount = updatedVialAmount;

		// System.out.println("Insulin leftover: " + vialAmount + "mL");
		// insulinLevelLbl.setText(vialAmount + "mL");
		insulinProgressBar.setProgress((double) updatedVialAmount / 100);

		if (vialAmount <= 0) {
			// System.out.println("WARNING: No insulin vial needed");
		} else if (vialAmount > 0) {
			// System.out.println( "Vial State: Good");
		}

	}

	public void BatteryLevel() {

		Random rand = new Random();
		double pickedNumber = rand.nextInt(99) + 1;

		// batteryLevelLbl.setText(pickedNumber + "%");
		double batteryPercent = pickedNumber / 100;
		batteryProgressBar.setProgress(batteryPercent);
		// ProgressBar batteryProgressBar = new ProgressBar();
		// batteryProgressBar.setProgress(pickedNumber);

		if (pickedNumber <= 20) {
			// System.out.println("WARNING: Battery Level Low:" +pickedNumber +"%");
		} else if (pickedNumber > 20) {
			// System.out.println("Battery Level good:" + pickedNumber+ "%");
		}
	}

	// Display the Date/Time
	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		// System.out.println(formatter.format(date));
		DateTimeLbl.setText("Date & Time: " + formatter.format(date));
		return formatter.format(date);
	}

	// Timer Class & Methods - In charge of running the methods every t amount of
	// miliseconds
	public class Timers extends TimerTask {

		// this method performs the task
		public void run() {
			runPump();
		}
	}

	public void setID(String setID) {
		ID = setID;
	}

	public String getID() {
		return ID;
	}

	public void changeScenetoUserData(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../com/InsulinPump/view/UserData.fxml"));
		Parent root = (Parent) loader.load();

		UserDataController controllers = loader.getController();
		controllers.setID(getID());
		Stage stage = new Stage();
		stage.setTitle("Insulin Pump");
		stage.getIcons().add(new Image("/com/InsulinPump/images/blueHeartbeat.png"));
		stage.setScene(new Scene(root));
		stage.show();
	}

}