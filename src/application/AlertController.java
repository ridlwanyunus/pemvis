package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AlertController implements Initializable {

	@FXML
	private Label labelAlert;
	
	@FXML
	private Button btnCloseAlert;
	
	public void show(String label) {
		labelAlert.setText(label);
	}
	
	public void message(Parent root, String label) {
		
		labelAlert.setText(label);
		Scene scene = new Scene(root);
		String css = getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Stage stage = new Stage();
		stage.setTitle("Notifikasi");
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setScene(scene);
		stage.show();
	}
	
	public void close(ActionEvent event) {
		Stage stage = (Stage) btnCloseAlert.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		labelAlert.setWrapText(true);
		Font font = Font.loadFont(getClass().getResourceAsStream("Poppins-Light.ttf"), 13);
		labelAlert.setFont(font);
		btnCloseAlert.setFont(font);
		
	}
}
