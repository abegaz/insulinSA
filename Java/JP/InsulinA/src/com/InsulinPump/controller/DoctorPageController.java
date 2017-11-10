package com.InsulinPump.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class DoctorPageController {
	@FXML
	private Button addPatient;

	// Event Listener on Button[#addPatient].onAction
	@FXML
	public void changeSceneToPatientInsert(ActionEvent event) throws IOException {
			if(event.getSource() == addPatient) {
		    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/AddPatient.fxml"));
		    Scene tableViewScene = new Scene(tableViewParent);
	        window.setScene(tableViewScene);
	        window.show();
	        }
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
