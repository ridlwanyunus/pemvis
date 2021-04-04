package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Pelanggan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DataPelangganFormController implements Initializable{
	
	@FXML
	private TextField idPelanggan;
	
	@FXML
	private TextField nama;
	
	@FXML
	private RadioButton bLakiLaki;
	
	@FXML
	private RadioButton bPerempuan;
	
	@FXML
	private TextField telepon;
	
	@FXML
	private TextArea alamat;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//idPelanggan.setText("Hello there");
	}
	
	
	public void createForm(Parent root) {
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			Image image = new Image("application/img/Dva.png");
			stage.getIcons().add(image);
			stage.setTitle("Form Data Pelanggan");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void updateForm(Pelanggan pelanggan) {
		idPelanggan.setText(String.valueOf(pelanggan.getIdPelanggan()));
		idPelanggan.setEditable(false);
		idPelanggan.setDisable(true);
		nama.setText(pelanggan.getNama());
		if(pelanggan.getJenisKelamin() == "Perempuan") {
			bPerempuan.setSelected(true);
		} else {
			bLakiLaki.setSelected(true);
		}
		telepon.setText(pelanggan.getTelepon());
		alamat.setText(pelanggan.getAlamat());
	}


}
