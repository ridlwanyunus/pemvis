package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import application.koneksi.Koneksi;
import application.model.UserID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	private Parent root;
	private Scene scene;
	private Stage stage;
	
	@FXML
	private ImageView imageViewLogin;
	
	@FXML
	private AnchorPane loginAnchorPane1;
	
	@FXML
	private TextField tfUsername;
	
	@FXML
	private TextField tfPassword;
	
	@FXML
	private Label labelUsername;
	
	@FXML
	private Label labelPassword;
	
	@FXML
	private Label labelBanner;
	
	@FXML
	private Button logniButton;
	
	private Connection conn = new Koneksi().connect();
	
	public void setBanner(Image image) throws IOException{
		imageViewLogin.setImage(image);
	}
	
	public void login(ActionEvent e){
		String sql = "SELECT * FROM kasir WHERE id_kasir='"+tfUsername.getText()+"' AND password='"+tfPassword.getText()+"'";

		try {
			Statement statement = conn.createStatement();
			ResultSet hasil = statement.executeQuery(sql);
			if(hasil.next()) {
				UserID.setUserLogin(hasil.getString(1));
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
			} else {
				myAlert("Username atau Password yang Anda masukkan salah");
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			e1.printStackTrace();
		}
	}
	
	public void logout(ActionEvent e) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginMenu.fxml"));
		root = loader.load();
		stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		String css = getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Image imageBanner = new Image("application/login2.png");
		imageViewLogin.setImage(imageBanner);
		stage.setTitle("Login Pemvis");
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Font font = Font.loadFont(getClass().getResourceAsStream("Poppins-Light.ttf"), 13);
		Font font16 = Font.loadFont(getClass().getResourceAsStream("Poppins-Light.ttf"), 16);
		tfUsername.setFont(font);
		
		labelBanner.setFont(font16);
		labelUsername.setFont(font);
		labelPassword.setFont(font);
		logniButton.setFont(font);
	}
	
	private void myAlert(String message){
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
			Parent root = loader.load();
			AlertController controller = loader.getController();
			controller.message(root, "Username atau Password yang anda masukkan salah");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
