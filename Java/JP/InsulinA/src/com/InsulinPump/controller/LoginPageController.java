package com.InsulinPump.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import application.InsulinPumpDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPageController implements Initializable {

	@FXML
	private JFXButton btn_patient1, btn_physician1;	
	@FXML
	private AnchorPane pn_patient1, pn_physician1;	
	@FXML
	private TextField TextUsername, IDPatient;
	@FXML
	private PasswordField TextPassword;
	//SAMPLE ID 23415689
	
	@FXML
	private void handleUserChange(ActionEvent event) {
		if (event.getSource() == btn_patient1)
		{
			pn_patient1.toFront();
		}
		else
			if(event.getSource() == btn_physician1)
			{
				pn_physician1.toFront();
			}
	}
	
	// This method will redirect the scene into the patient TableView
    public void changeSceneToPatientMainMenu(ActionEvent event) throws IOException
    {
   	
    	ResultSet rs = null;
    	String SQLQuery = "SELECT * FROM patient WHERE idPatient = ?;";
    	
    	try(
        	    Connection conn = InsulinPumpDBConfig.getConnection();
        	    PreparedStatement displayid = conn.prepareStatement(SQLQuery);
        	){	
    			displayid.setString(1, IDPatient.getText());
        	    rs = displayid.executeQuery();
        	    
        	    //If the result set found a match, continues
        	    if(rs.next()) {
        	    	
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../com/InsulinPump/view/PatientMainMenu.fxml"));
        	    	Parent root = (Parent) loader.load();
        	        PatientMainMenuController controller = loader.getController();
        	        controller.setID(IDPatient.getText());
        	        Stage stage = new Stage();
        	        stage.setTitle("Insulin Pump");
        		    stage.getIcons().add(new Image("/com/InsulinPump/images/blueHeartbeat.png"));
        	        stage.setScene(new Scene (root));
        	        stage.show();
        	    }
        	    
        	    //If not shows error
        	    else {
        	    		Alert alert = new Alert(AlertType.ERROR);
        	    		alert.setTitle("Invalid Patient");
        	    		alert.setHeaderText("Patient is Invalid");
        	    		alert.setContentText("The Patient ID entered is not present in our current system, please enter a valid Patient ID");
        	    		alert.showAndWait();
        	    }
        	    
        	} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);

    		}
    }
    
	// This method will redirect the scene into the patient TableView
    public void changeSceneToDoctorPage(ActionEvent event) throws IOException
    {
    	String username = "Insulinpump";
    	String password = "1234";

    	if(TextUsername.getText().equals(username) && TextPassword.getText().equals(password)) {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/DoctorPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Insulin Pump");
	    window.getIcons().add(new Image("/com/InsulinPump/images/blueHeartbeat.png"));
        window.setScene(tableViewScene);
        window.show();
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Username Error");
    		alert.setHeaderText("Username Error");
    		alert.setContentText("The Username/Password entered is invalid, please enter a valid Username/Password");
    		alert.showAndWait();
    	}
    }


	public void initialize (URL url, ResourceBundle rb) {
	}
}


