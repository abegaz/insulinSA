package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private TextField TextUsername;
	@FXML
	private PasswordField TextPassword;
	
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/PatientMainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
	// This method will redirect the scene into the patient TableView
    public void changeSceneToDoctorMainMenu(ActionEvent event) throws IOException
    {
    	String username = "Insulinpump";
    	String password = "1234";

    	if(TextUsername.getText().equals(username) && TextPassword.getText().equals(password)) {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../view/DoctorMainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Username Error");
    		alert.setHeaderText("Username/Password is invalid");
    		alert.showAndWait();
    	}
    }


	public void initialize (URL url, ResourceBundle rb) {
	}
}


