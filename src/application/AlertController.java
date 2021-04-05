package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	public void close(ActionEvent event) {
		Stage stage = (Stage) btnCloseAlert.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Font font = Font.loadFont(getClass().getResourceAsStream("Poppins-Light.ttf"), 13);
		labelAlert.setFont(font);
		btnCloseAlert.setFont(font);
		
	}
}
