package com.InsulinPump.controller;

import com.InsulinPump.model.*;
import application.InsulinPumpDBConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class DoctorPageController {
	
	//configure the Patient table view
		@FXML private TableView<Patient> tableViewInfo;
	    @FXML private TableColumn<Patient, String> cPatientId;
	    @FXML private TableColumn<Patient, String> cFirstName;
	    @FXML private TableColumn<Patient, String> cLastName;
	    @FXML private TableColumn<Patient, String> cAddress;
	    @FXML private TableColumn<Patient, String> cBloodType;
	    @FXML private TableColumn<Patient, String> cAge;
	    @FXML private TableColumn<Patient, String> cGender;
	    @FXML private TableColumn<Patient, String> cHeight;
	    @FXML private TableColumn<Patient, String> cWeight;
	    @FXML private TableColumn<Patient, String> cInsulinType;
	    @FXML private TableColumn<Patient, String> cPhone;
	    @FXML private TableColumn<Patient, String> cDoctor;
	    
	    @FXML private Button addPatient;
	    @FXML private Button bDeletePatient;
	
	@FXML public void initialize() {
		
        //set up the columns in the table
		cPatientId.setCellValueFactory(new PropertyValueFactory<Patient, String>("idPatient"));
		cFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		cLastName.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		cAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		cBloodType.setCellValueFactory(new PropertyValueFactory<Patient, String>("bloodType"));
		cAge.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
		cGender.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
		cHeight.setCellValueFactory(new PropertyValueFactory<Patient, String>("height"));
		cWeight.setCellValueFactory(new PropertyValueFactory<Patient, String>("weight"));
		cInsulinType.setCellValueFactory(new PropertyValueFactory<Patient, String>("insulinType"));
		cPhone.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone"));
		cDoctor.setCellValueFactory(new PropertyValueFactory<Patient, String>("idDoctor"));
		
		tableViewInfo.setItems(getPatientList());
		tableViewInfo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.bDeletePatient.setDisable(true);
	}
	
	// This method removes one or more selected row(s) from the TableView
    public void deleteButtonPushed(){
    	
        ObservableList<Patient> selectedRows, allPatient;
        allPatient = tableViewInfo.getItems();

        //this gives us the rows that were selected
        selectedRows = tableViewInfo.getSelectionModel().getSelectedItems();

        //loop over the selected rows and remove the Patient objects from the table
        for (Patient patient: selectedRows)
        {
        	allPatient.remove(patient);
        	String query = "DELETE FROM patient where idPatient = ?;";

        	try(
        	    Connection conn = InsulinPumpDBConfig.getConnection();
        	    PreparedStatement updateprofile = conn.prepareStatement(query);
        	){
        		updateprofile.setString(1, patient.getidPatient());
        	    updateprofile.executeUpdate();
        	} catch (Exception e) {
    			System.out.println("Status: operation failed due to "+e);

    		}
        }
    }
    
 // ObservableList: A list that enables listeners to track changes when they occur
    // The following  method will return an ObservableList of  object
    public ObservableList<Patient>  getPatientList(){
    	
    	ObservableList<Patient> patient = FXCollections.observableArrayList();

    	//idPatient, firstName, lastName, Address, bloodType, age, gender, height, weight, insulinType, phone, idDoctor
        String SQLQuery = "SELECT * FROM patient ORDER BY idPatient ASC;"; //ADD WHERE idPatient == ''
       	ResultSet rs = null;

       	try(
       			Connection conn = InsulinPumpDBConfig.getConnection();
       			PreparedStatement displayprofile = conn.prepareStatement(SQLQuery);
       	){
       		//displayprofile.setInt(1, cutomerId);
       		rs = displayprofile.executeQuery();
       		// check to see if receiving any data
       		while (rs.next()){
       			patient.add(new Patient(rs.getString("idPatient").toString(),rs.getString("firstName").toString(),rs.getString("lastName").toString(),rs.getString("Address").toString(),rs.getString("bloodType").toString(),
       					rs.getString("age").toString(),rs.getString("gender").toString(),rs.getString("height").toString(),rs.getString("weight").toString(),rs.getString("insulinType").toString(),rs.getString("phone").toString(),rs.getString("idDoctor").toString()));
       		}
       	}catch(SQLException ex){
       		InsulinPumpDBConfig.displayException(ex);
       		return null;
       	}finally{
       		if(rs != null){
       			//rs.close();
       		}
       	}
        return patient;
    }
    
    // Method used to enable the detailed view button on mouse click event
    public void mouseClickedOnTableView(){
         	this.bDeletePatient.setDisable(false);
           //this.deleteCustomerButton.setDisable(false);
       }
	
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
