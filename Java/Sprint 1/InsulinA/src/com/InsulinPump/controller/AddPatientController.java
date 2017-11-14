package com.InsulinPump.controller;

import com.InsulinPump.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import application.InsulinPumpDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPatientController {


	  @FXML private TextField txtPatientId, txtPatientFirstName, txtPatientLastName, txtPatientAddress, txtPatientBloodT , txtPatientPhoneNumber, txtPatientAge, txtPatientGender, txtPatientHeight, txtPatientWeight, txtPatientInsulinType, txtDoctorId;
	  @FXML private Button btnAddNewPatient, btndoctorHomeButton;

    public void newPatient(ActionEvent event) throws IOException{
    	
    	Patient newPatient = new Patient(txtPatientId.getText(),
    								txtPatientFirstName.getText(),
    								txtPatientLastName.getText(),
    								txtPatientAddress.getText(),
    								txtPatientBloodT.getText(),
    								txtPatientAge.getText(),
    								txtPatientGender.getText(),
    								txtPatientHeight.getText(),
    								txtPatientWeight.getText(),
    								txtPatientInsulinType.getText(),
    								txtPatientPhoneNumber.getText(),
    								txtDoctorId.getText());
    	    	
    	String query = "insert into patient " + "(idPatient, firstName, lastName, address, bloodType, age, gender, height, weight, insulinType, phone, idDoctor) " + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
    	
    	try (Connection conn = InsulinPumpDBConfig.getConnection();
				PreparedStatement insertprofile = conn.prepareStatement(query);) {
    		
    		insertprofile.setString(1, newPatient.getidPatient());
			insertprofile.setString(2, newPatient.getFirstName());
			insertprofile.setString(3, newPatient.getLastName());
			insertprofile.setString(4, newPatient.getAddress());
			insertprofile.setString(5, newPatient.getBloodType());
			insertprofile.setString(6, newPatient.getAge());
			insertprofile.setString(7, newPatient.getGender());
			insertprofile.setString(8, newPatient.getHeight());
			insertprofile.setString(9, newPatient.getWeight());
			insertprofile.setString(10, newPatient.getInsulinType());
			insertprofile.setString(11, newPatient.getPhone());
			insertprofile.setString(12, newPatient.getidDoctor());
    		
			insertprofile.execute();
    	} catch (Exception e) {
    		System.out.println("Status: operation failed due to "+e);

		}
    	
    	if(event.getSource() == btnAddNewPatient) {
    	}
			if(event.getSource() == btnAddNewPatient) {

    	}
			if(event.getSource() == btnAddNewPatient) {

		    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/DoctorPage.fxml"));
		    Scene tableViewScene = new Scene(tableViewParent);
	        window.setScene(tableViewScene);
	        window.show();
			}
    }
    
    @FXML
	public void changeSceneToDoctorHome(ActionEvent event) throws IOException {
			if(event.getSource() == btndoctorHomeButton) {
		    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/DoctorPage.fxml"));
		    Scene tableViewScene = new Scene(tableViewParent);
	        window.setScene(tableViewScene);
	        window.show();
	        	}
	        }

}

