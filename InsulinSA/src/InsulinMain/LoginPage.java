package InsulinMain;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginPage extends Application {
	private Stage primaryStage;
	private AnchorPane DrLoginPage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader (getClass().getResource("DrLoginPage.fxml"));
		DrLoginPage = loader.load();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Insulin Pump");
		showLoginView();		
	}


	private void showLoginView() throws IOException {
		Scene scene = new Scene (DrLoginPage);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
