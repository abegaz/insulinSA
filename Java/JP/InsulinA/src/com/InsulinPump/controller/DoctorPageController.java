package com.InsulinPump.controller;

import com.InsulinPump.model.*;

import application.InsulinPumpDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorPageController {
	
		@FXML private TableView<Patient> customerTableView;
	    @FXML private TableColumn<Patient, String> firstNameColumn;
	    @FXML private TableColumn<Patient, String> lastNameColumn;
	    @FXML private TableColumn<Patient, String> creditHistoryColumn;
	    @FXML private TableColumn<Patient, String> addressColumn;
	    @FXML private TableColumn<Patient, String> phoneColumn;
	
	    @FXML private TextField firstNameTextField;
	    @FXML private TextField lastNameTextField;
	    @FXML private TextField creditHistoryTextField;
	    @FXML private TextField addressTextField;
	    @FXML private TextField phoneTextField;
	   
	    @FXML private ComboBox MenuSelectDoctor;
	    @FXML private Button buttonAddPatient;
	
	    
	    public void initialize(){
	    	ResultSet rs = null;
	    	try (Connection conn = InsulinPumpDBConfig.getConnection()) { 
	    		
	    		ObservableList<Object> data = FXCollections.observableArrayList();
	    		PreparedStatement stmt = conn.prepareStatement("SELECT CONCAT(FName, ' ', Minit, ' ', LName) as Name FROM doctor;");
	    		rs = stmt.executeQuery();
	    		
	    		while(rs.next()) {
	    			data.add(rs.getString("Name"));	
	    		}
	    		MenuSelectDoctor.setItems(data);
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
	    }
	    
	    public void populateTable(ActionEvent event) {
	    	
	    }
	    
	    public void newPatientButtonPushed(ActionEvent event) throws IOException {
	    	
	        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../../../com/InsulinPump/view/AddPatient.fxml"));
	        Scene tableViewScene = new Scene(tableViewParent);
	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        window.setScene(tableViewScene);
	        window.show();
	    }
	

}
