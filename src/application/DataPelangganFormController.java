package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.koneksi.Koneksi;
import application.model.Pelanggan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DataPelangganFormController implements Initializable{
	
	@FXML
	private TextField txtid;
	
	@FXML
	private TextField txtnm;
	
	@FXML
	private RadioButton rlaki;
	
	@FXML
	private RadioButton rperempuan;
	
	@FXML
	private TextField txttelp;
	
	@FXML
	private TextArea txtalamat;
	
	@FXML
	private Button closeButton;
	
	private DataPelangganController parentController;
	
	private Connection conn = new Koneksi().connect();
	
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
	
	public void bsimpanActionPerformed(ActionEvent event) throws IOException {
		String jenis = null;
		if(rlaki.isSelected()) {
			jenis = "Laki-Laki";
		} else 
		if(rperempuan.isSelected()) {
			jenis = "Perempuan";
		}
		String sql = "insert into pelanggan values (?,?,?,?,?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtid.getText());
			stat.setString(2, txtnm.getText());
			stat.setString(3, jenis);
			stat.setString(4, txttelp.getText());
			stat.setString(5, txtalamat.getText());
			stat.execute();
			
			myAlert("Data berhasil disimpan");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			myAlert("Data gagal disimpan");
		}
	}
	
	public void bubahActionPerformed(ActionEvent event) throws IOException {
		String jenis = null;
		if(rlaki.isSelected()) {
			jenis = "Laki-Laki";
		} else 
		if(rperempuan.isSelected()) {
			jenis = "Perempuan";
		}
		String sql = "update pelanggan set nmplgn=?, jenis=?, telepon=?, alamat=? where id='"+txtid.getText()+"'";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtnm.getText());
			stat.setString(2, jenis);
			stat.setString(3, txttelp.getText());
			stat.setString(4, txtalamat.getText());
			stat.executeUpdate();
			
			myAlert("Data berhasil diubah");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			myAlert("Data gagal diubah");
		}
	}
	
	public void bhapusActionPerformed(ActionEvent event) {
		int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi dialog", JOptionPane.YES_NO_OPTION);
		if(ok==0) {
			String sql = "delete from pelanggan where id='"+txtid.getText()+"'";
			try {
				PreparedStatement stat = conn.prepareStatement(sql);
				stat.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data "+txtid.getText()+" berhasil dihapus");
				kosong(event);
				parentController.refresh(event);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Data gagal dihapus");
			}
		}
	}
	
	public void myAlert(String message) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
		Parent root = loader.load();
		AlertController alert = loader.getController();
		alert.show(message);
		Scene scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Stage stage = new Stage();
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setTitle("Form Data Pelanggan");
		stage.setScene(scene);
		stage.show();
	}
	
	public void kosong(ActionEvent event) {
		txtid.setText("");
		txtnm.setText("");
		rperempuan.setSelected(false);
		rlaki.setSelected(false);
		txttelp.setText("");
		txtalamat.setText("");
	}
	
	public void bkeluarActionPerformed(ActionEvent event) {
		parentController.refresh(event);
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void refreshTable() {
		
	}
	
	public void updateForm(Parent root, Pelanggan pelanggan) {
		txtid.setText(String.valueOf(pelanggan.getId()));
		txtid.setEditable(false);
		txtid.setDisable(true);
		txtnm.setText(pelanggan.getNmplgn());
		if(pelanggan.getJenis().equalsIgnoreCase("Perempuan")) {
			rperempuan.setSelected(true);
		} else {
			rlaki.setSelected(true);
		}
		txttelp.setText(pelanggan.getTelepon());
		txtalamat.setText(pelanggan.getAlamat());
		Scene scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Stage stage = new Stage();
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setTitle("Form Data Pelanggan");
		stage.setScene(scene);
		stage.show();
	}

	public void setParentController(DataPelangganController controller) {
		parentController = controller;
	}

}
