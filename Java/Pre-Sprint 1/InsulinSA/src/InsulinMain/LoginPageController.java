package InsulinMain;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class LoginPageController implements Initializable {

	@FXML
	private JFXButton btn_patient1, btn_physician1;	
	@FXML
	private AnchorPane pn_patient1, pn_physician1;	
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
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

	public void initialize (URL url, ResourceBundle rb) {
	}
}


