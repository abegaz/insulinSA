package com.InsulinPump.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PatientMainMenuController {
	
	@FXML Text statusLbl;
	@FXML Text insulinDoseLbl;
	@FXML Text currentLevelLbl;
	@FXML Text batteryLevelLbl;
	@FXML Text insulinLevelLbl;
	@FXML Text DateTimeLbl;
	@FXML Button btnBolus;
	
	static int dose=0;

 	static int reading0;
 	static int reading1;
	int reading2;

	static int safeMin=60;
	static int safeMax=120;
	static int minDose = 10;
	static int maxSingleDose=20;
	static int vialAmount=50;
	static int updatedVialAmount;
	static int compDose=0;
	
	@FXML public void initialize() {
	 	Timer timer = new Timer();
		timer.schedule(new Timers(), 0 , 300000); //300000
		
		getTime();
		
	}
	
	public void runPump()
		{

			reading0 = 120; //for testing
			reading1 = 100; //for testing

			// random number generated every time for reading 2
			Random rand = new Random();
			int reading2 = rand.nextInt(220) + 60;
			currentLevelLbl.setText("Current Blood Sugar: "+reading2);



			/*Sugar reading is low*/
			if (reading2 < safeMin)
			{
				compDose = 0;
				statusLbl.setText("Sugar is too low");
			}
	
			/*Sugar is within safe range*/
			else if (reading2 >= safeMin && reading2 <= safeMax)
			{
				// If sugar level is stable or falling
				if (reading2 <= reading1)
				{
					compDose = 0;
					statusLbl.setText("Sugar is stable or falling");
				}
				// If sugar level increasing
				else
				{
					// If rate of increase is falling
					if ((reading2 - reading1) < (reading1 - reading0))
					{
						compDose = 0;
						statusLbl.setText("rate of increase is falling");
					}
					// If rate of increase is increasing
					else if ((reading2 - reading1) >= (reading1 - reading0))
					{

						statusLbl.setText("rate of increase is increasing");

						// If dose is rounded to zero, deliver the min dose
						if ((reading2 - reading1) / 40 == 0)
						{
							//set the amount to deliver to the min dose
							compDose = minDose;
						}
						else if ((reading2 - reading1) / 40 > 0)
						{
							//Set the amount to deliver
							compDose = (reading2 - reading1) / 40;
						}
					}
				}
			}
	
			/*Sugar is High*/
			else if (reading2 > safeMax)
			{

				statusLbl.setText("Sugar is High");

				// If Sugar level increasing
				if (reading2 > reading1)
				{

					statusLbl.setText("Sugar is High and increasing");

					// If dose is rounded to zero, deliver the min dose
					if ((reading2 - reading1) / 40 == 0)
					{
						compDose = minDose;
					}
					else if ((reading2 - reading1) / 40 > 0)
					{
						//Set the amount to deliver
						compDose = (reading2 - reading1) / 40;
					}
				}
				// If the Sugar level is stable
				else if (reading2 == reading1)
				{
					compDose = minDose;
				}
				// If the Sugar level is falling
				else if (reading2 < reading1)
				{

					statusLbl.setText("Sugar level is falling");

					// If rate of decrease increasing
					if ((reading2 - reading1) <= (reading1 - reading0))
					{
						statusLbl.setText("rate of decrease is increasing");
						compDose = 0;
					}
					// If rate of decrease decreasing
					else
					{
						statusLbl.setText("rate of decrease is decreasing");
						compDose = minDose;
					}
				}
			}

			if (compDose <= maxSingleDose)
			{
				dose = compDose;
				insulinDoseLbl.setText("Suggested Dose: "+ dose+"mL");
			}
//			//If the single dose computed is too high
			else
			{
				dose = maxSingleDose;
				insulinDoseLbl.setText("Suggested Dose: "+ dose+"mL");
			}

			if (dose > 0)
			{
				//Display to the user the dose delivered
				insulinDoseLbl.setText("Delivered Dose: "+ dose+"mL");
			}

//			//Adjust the reading values to accomodate for the new reading

//			reading0=reading1;
//			reading1=reading2;
//			reading2=0;

			InsulinLeft();
			BatteryLevel();
		}
		public  void InsulinLeft() // Insulin left over in the reservour/vial.
		{
			//System.out.println("Vial total =" + vialAmount);
			updatedVialAmount=(vialAmount-dose);
			vialAmount=updatedVialAmount;
			
			//System.out.println("Insulin leftover: " + vialAmount + "mL");
			insulinLevelLbl.setText(vialAmount+"mL");
			
			if (vialAmount <=0)
			{
				//System.out.println("WARNING: No insulin vial needed");
			}
			else if (vialAmount > 0)
			{
				//System.out.println( "Vial State: Good");
			}
			
		}
	 
		public  void BatteryLevel(){
			
			Random rand = new Random(); 
			int pickedNumber = rand.nextInt(99) + 1; 
		 
			batteryLevelLbl.setText(pickedNumber+"%");
		
			if (pickedNumber <=20){
			//System.out.println("WARNING: Battery Level Low:" +pickedNumber +"%");
			}
			else if (pickedNumber > 20){
			//System.out.println("Battery Level good:" + pickedNumber+ "%");
			}
		}
		
		//Return the Date/Time
		public void getTime() {
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    //System.out.println(formatter.format(date));  
		    DateTimeLbl.setText("Date & Time: " +formatter.format(date));
		}
		
		//Timer Class & Methods - In charge of running the methods every t amount of miliseconds
		public class Timers extends TimerTask{	

			//this method performs the task
			public void run () {
			// need to automatically input yes in the test controller for check bs.
			runPump();
			}
		}
		
		

}