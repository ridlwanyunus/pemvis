package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {

	private Parent root;
	private Scene scene;
	private Stage stage;
	
	public void login(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
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
	
	public void logout(ActionEvent e) throws IOException{
		
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
	
}
