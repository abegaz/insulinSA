package com.InsulinPump.controller;

import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.InsulinPump.model.Record;
import com.jfoenix.controls.JFXButton;

import application.InsulinPumpDBConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;

public class UserDataController {
	
	@FXML private AnchorPane paneUserData;
	@FXML private TableView<Record> tblPatientData;
	@FXML private TableColumn<Record, String> clnRecordId;
	@FXML private TableColumn<Record, String> clnReadingTime;
	@FXML private TableColumn<Record, String> clnGlucoseLevel;
	@FXML private TableColumn<Record, String> clnInsulinAdmin;
	@FXML private TableColumn<Record, String> clnStatus;
	@FXML private JFXButton btnPatientMainMenu;
	String IDP;
	
   
 // ObservableList: A list that enables listeners to track changes when they occur
    // The following  method will return an ObservableList of  object
    public ObservableList<Record>  getRecordList(){
    	
    	ObservableList<Record> record = FXCollections.observableArrayList();
        String SQLQuery = "SELECT * FROM records WHERE idPatient = ? ORDER BY idRecords DESC;"; //ADD WHERE idPatient = ''
       	ResultSet rs = null;

       	try(
       			Connection conn = InsulinPumpDBConfig.getConnection();
       			PreparedStatement displayprofile = conn.prepareStatement(SQLQuery);
       	){
       		displayprofile.setString(1, IDP);
       		rs = displayprofile.executeQuery();
       		// check to see if receiving any data
       		while (rs.next()){
       			record.add(new Record(rs.getString("idRecords").toString(),rs.getString("dateTime").toString(),rs.getString("glucoseReading").toString(),rs.getString("insulinAmount").toString(),
       					rs.getString("status").toString(),rs.getString("idPatient").toString(),rs.getString("idDoctor").toString()));
       		}
       	}catch(SQLException ex){
       		InsulinPumpDBConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return record;
    }
    
    // Method used to enable the detailed view button on mouse click event
	@FXML
	public void changeSceneToPatientHome(ActionEvent event) throws IOException {
		    Stage stage = (Stage)btnPatientMainMenu.getScene().getWindow();
		    stage.close();
		}
	       
	
	
	public void setID(String setID){
		IDP = setID;
		
        //set up the columns in the table
		clnRecordId.setCellValueFactory(new PropertyValueFactory<Record, String>("idRecords"));
		clnReadingTime.setCellValueFactory(new PropertyValueFactory<Record, String>("dateTime"));
		clnGlucoseLevel.setCellValueFactory(new PropertyValueFactory<Record, String>("glucoseReading"));
		clnInsulinAdmin.setCellValueFactory(new PropertyValueFactory<Record, String>("InsulinAmount"));
		clnStatus.setCellValueFactory(new PropertyValueFactory<Record, String>("Status"));	
		tblPatientData.setItems(getRecordList());
		
		tblPatientData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}	
	
	
	}