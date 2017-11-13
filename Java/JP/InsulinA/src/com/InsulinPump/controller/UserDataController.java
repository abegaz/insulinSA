package com.InsulinPump.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class UserDataController {
	@FXML
	private AnchorPane paneUserData;
	@FXML
	private TableView<?> tblPatientData;
	@FXML
	private TableColumn<?, ?> clnPatientId;
	@FXML
	private TableColumn<?, ?> clnReadingTime;
	@FXML
	private TableColumn<?, ?> clnGlucoseLevel;
	@FXML
	private TableColumn<?, ?> clnCurrentGlucose;
	@FXML
	private TableColumn<?, ?> clnHighLow;
	@FXML
	private JFXButton btnPatientMainMenu;

	@FXML
	public void changeSceneToPatientHome(ActionEvent event) throws IOException {
			if(event.getSource() == btnPatientMainMenu) {
		    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/PatientMainMenu.fxml"));
		    Scene tableViewScene = new Scene(tableViewParent);
	        window.setScene(tableViewScene);
	        window.show();}
	        }
	}