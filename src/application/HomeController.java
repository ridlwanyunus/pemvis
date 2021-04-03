package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	Button btnDataPelanggan;
	
	@FXML
	Button btnMenu1;
	
	@FXML
	Button btnMenu2;
	
	@FXML
	Button btnMenu3;
	
	@FXML
	Button btnLogout;
	
	private Parent root;
	private Scene scene;
	private Stage stage;
	
	
	public void btnDataPelangganHanlder(ActionEvent event) {

		resetButton();
		btnDataPelanggan.getStyleClass().add("buttonMenuActive");
	}
	
	public void btnMenu1Hanlder(ActionEvent event) {
		resetButton();
		btnMenu1.getStyleClass().add("buttonMenuActive");
	}
	
	
	public void btnMenu2Hanlder(ActionEvent event) {
		resetButton();
		btnMenu2.getStyleClass().add("buttonMenuActive");
	}
	
	public void btnMenu3Hanlder(ActionEvent event) {
		resetButton();
		btnMenu3.getStyleClass().add("buttonMenuActive");
	}
	
	public void btnLogoutHanlder(ActionEvent event) {
		resetButton();
		btnLogout.getStyleClass().add("buttonMenuActive");
	}
	
	public void logout(ActionEvent e) throws IOException{
		resetButton();
		btnLogout.getStyleClass().add("buttonMenuActive");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginMenu.fxml"));
		root = loader.load();
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		String css = getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		stage.setTitle("Pemrograman Pemvis");
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void resetButton() {
		btnDataPelanggan.getStyleClass().remove("buttonMenuActive");
		btnMenu1.getStyleClass().remove("buttonMenuActive");
		btnMenu2.getStyleClass().remove("buttonMenuActive");
		btnMenu3.getStyleClass().remove("buttonMenuActive");
		btnLogout.getStyleClass().remove("buttonMenuActive");
	}

}