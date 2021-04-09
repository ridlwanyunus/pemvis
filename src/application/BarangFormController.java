package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.koneksi.Koneksi;
import application.model.Barang;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BarangFormController implements Initializable {
	@FXML
	private TextField txtid;
	
	@FXML
	private TextField txtnm;
	
	@FXML
	private ChoiceBox<String> cbjenis=new ChoiceBox<>();
	
	@FXML
	private TextField txthbeli;
	
	@FXML
	private TextField txthjual;
	
	@FXML
	private Button closeButton;
	
	private Connection conn = new Koneksi().connect();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		cbjenis = new ChoiceBox(FXCollections.observableArrayList("Makanan", "Minuman"));
		cbjenis.getItems().add("Makanan");
		cbjenis.getItems().add("Minuman");
	}
	
	
	public void createForm(Parent root) {
		
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			Image image = new Image("application/img/Dva.png");
			stage.getIcons().add(image);
			stage.setTitle("Form Barang");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void bsimpanActionPerformed(ActionEvent event) throws IOException {
		String jenis = cbjenis.getValue();
		
		String sql = "insert into barang values (?,?,?,?,?)";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtid.getText());
			stat.setString(2, txtnm.getText());
			stat.setString(3, jenis);
			stat.setInt(4, new Integer(txthbeli.getText()));
			stat.setInt(5,  new Integer(txthjual.getText()));
			stat.execute();
			
			myAlert("Data berhasil disimpan");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			myAlert("Data gagal disimpan");
		}
	}
	
	public void bubahActionPerformed(ActionEvent event) throws IOException {
		String jenis = cbjenis.getValue();
		
		String sql = "update barang set nm_barang=?, jenis=?, hargabeli=?, hargajual=? where kd_barang='"+txtid.getText()+"'";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtnm.getText());
			stat.setString(2, jenis);
			stat.setInt(3, new Integer(txthbeli.getText()));
			stat.setInt(4, new Integer(txthjual.getText()));
			stat.executeUpdate();
			
			myAlert("Data berhasil diubah");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Barang.fxml"));
			Parent root = loader.load();
			BarangController controller = loader.getController();
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
			String sql = "delete from barang where kd_barang='"+txtid.getText()+"'";
			try {
				PreparedStatement stat = conn.prepareStatement(sql);
				stat.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data "+txtid.getText()+" berhasil dihapus");
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
		cbjenis.setValue("");
		txthbeli.setText("");
		txthjual.setText("");
	}
	
	public void bkeluarActionPerformed(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void refreshTable() {
		
	}
	
	public void updateForm(Barang barang) {
		txtid.setText(String.valueOf(barang.getKdBarang()));
		txtid.setEditable(false);
		txtid.setDisable(true);
		txtnm.setText(barang.getNmBarang());
		cbjenis.setValue(barang.getJenis());
		txthbeli.setText(String.valueOf(barang.getHargabeli()));
		txthjual.setText(String.valueOf(barang.getHargajual()));
	}
}
