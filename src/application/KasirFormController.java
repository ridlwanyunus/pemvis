package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.koneksi.Koneksi;
import application.model.Barang;
import application.model.Kasir;
import application.model.Pelanggan;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class KasirFormController implements Initializable {
	
	@FXML
	private Label lblIdKasir;
	
	@FXML
	private TextField txtid;
	
	@FXML
	private Label lblnamaKasir;
	
	@FXML
	private TextField txtnm;
	
	@FXML
	private Label lblJenis;
	
	@FXML
	private RadioButton rlaki;
	
	@FXML
	private RadioButton rperempuan;
	
	@FXML
	private Label lblTelp;
	
	@FXML
	private TextField txttelp;
	
	@FXML
	private Label lblAgama;
	
	@FXML
	private TextField txtagama;
	
	@FXML
	private Label lblAlamat;
	
	@FXML
	private TextArea txtalamat;
	
	@FXML
	private Label lblPassword;
	
	@FXML
	private TextField txtpassword;
	
	@FXML
	private Button btnTambah;
	@FXML
	private Button btnUbah;
	@FXML
	private Button btnHapus;
	@FXML
	private Button btnBatal;
	@FXML
	private Button closeButton;
	
	
	
	private Connection conn = new Koneksi().connect();
	
	private KasirController parentController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Font font = Font.loadFont(getClass().getResourceAsStream("Poppins-Light.ttf"), 13);
		lblIdKasir.setFont(font);
		txtid.setFont(font);
		lblnamaKasir.setFont(font);
		txtnm.setFont(font);
		lblJenis.setFont(font);
		rlaki.setFont(font);
		rperempuan.setFont(font);
		lblTelp.setFont(font);
		txttelp.setFont(font);
		lblAgama.setFont(font);
		txtagama.setFont(font);
		lblAlamat.setFont(font);
		txtalamat.setFont(font);
		lblPassword.setFont(font);
		txtpassword.setFont(font);
		
		btnTambah.setFont(font);
		btnUbah.setFont(font);
		btnHapus.setFont(font);
		btnBatal.setFont(font);
		closeButton.setFont(font);
	}
	
	
	public void createForm(Parent root) {
		
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			Image image = new Image("application/img/Dva.png");
			stage.getIcons().add(image);
			stage.setTitle("Form Kasir");
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
		
		String sql = "insert into kasir values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtid.getText());
			stat.setString(2, txtnm.getText());
			stat.setString(3, jenis);
			stat.setString(4, txttelp.getText());
			stat.setString(5, txtagama.getText());
			stat.setString(6, txtalamat.getText());
			stat.setString(7, txtpassword.getText());
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
		
		String sql = "update kasir set nm_kasir=?, jenis_kelamin=?, no_telepon=?, agama=?, alamat=?, password=? where id_kasir='"+txtid.getText()+"'";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtnm.getText());
			stat.setString(2, jenis);
			stat.setString(3, txttelp.getText());
			stat.setString(4, txtagama.getText());
			stat.setString(5, txtalamat.getText());
			stat.setString(6, txtpassword.getText());
			stat.executeUpdate();
			
			myAlert("Data berhasil diubah");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Kasir.fxml"));
			Parent root = loader.load();
			KasirController controller = loader.getController();
			controller.datatable();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			myAlert("Data gagal diubah");
		}
	}
	
	public void bhapusActionPerformed(ActionEvent event) {
		int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi dialog", JOptionPane.YES_NO_OPTION);
		if(ok==0) {
			String sql = "delete from kasir where id_kasir='"+txtid.getText()+"'";
			try {
				PreparedStatement stat = conn.prepareStatement(sql);
				stat.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data "+txtid.getText()+" berhasil dihapus");
				parentController.refresh(event);
				kosong(event);
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
		stage.setTitle("Alert Box");
		stage.setScene(scene);
		stage.show();
	}
	
	public void kosong(ActionEvent event) {
		txtid.setText("");
		txtnm.setText("");
		rlaki.setSelected(false);
		rperempuan.setSelected(false);
		txttelp.setText("");
		txtalamat.setText("");
		txtpassword.setText("");
	}
	
	public void bkeluarActionPerformed(ActionEvent event) {
		parentController.refresh(event);
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void refreshTable() {
		
	}
	
	public void updateForm(Parent root, Kasir kasir) {
		
		try {
			String sql = "SELECT password FROM kasir where id_kasir='"+kasir.getIdKasir()+"'";
			Statement statement = conn.createStatement();
			ResultSet hasil = statement.executeQuery(sql);
			while(hasil.next()) {
				txtpassword.setText(hasil.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtid.setText(String.valueOf(kasir.getIdKasir()));
		txtid.setEditable(false);
		txtid.setDisable(true);
		txtnm.setText(kasir.getNmKasir());
		if(kasir.getJenisKelamin().equalsIgnoreCase("Perempuan")) {
			rperempuan.setSelected(true);
		} else {
			rlaki.setSelected(true);
		}
		txttelp.setText(kasir.getNoTelepon());
		txtalamat.setText(kasir.getAlamat());
		txtagama.setText(kasir.getAgama());
		
		Scene scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Stage stage = new Stage();
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setTitle("Form Kasir");
		stage.setScene(scene);
		stage.show();
	}


	public void setParentController(KasirController kasirController) {
		parentController = kasirController;
		
	}
}
