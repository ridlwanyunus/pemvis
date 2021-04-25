package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.model.Barang;
import application.model.Isi;
import application.model.Pelanggan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class IsiFormController implements Initializable {

	@FXML
	private TextField 	txtIdPelanggan, txtNamaPelanggan, 
						txtKodeBarang, txtNamaBarang, txtHargaBeli, 
						txtHargaJual, txtQuantity, txtTotal;
	
	@FXML
	private TextArea txtAlamat;
	
	private Button 		btnCariPelanggan, btnCariKodeBarang, btnTambahBarang;
	
	private IsiController parentController;
	
	ObservableList<Pelanggan> pelangganList;
	ObservableList<Barang> barangList;
	
	private ObservableList<Isi> listIsi = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pelangganList = FXCollections.observableArrayList();
		barangList = FXCollections.observableArrayList();
		txtQuantityActionPerformed();
		
	}
	
	

	public void createForm(Parent root) {
		
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			Image image = new Image("application/img/Dva.png");
			stage.getIcons().add(image);
			stage.setTitle("Form Isi Nota");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setItemTerpilihPelanggan(Pelanggan pelanggan) {
		txtIdPelanggan.setText(pelanggan.getId());
		txtNamaPelanggan.setText(pelanggan.getNmplgn());
		txtAlamat.setText(pelanggan.getAlamat());
	}
	
	public void setItemTerpilihBarang(Barang barang) {
		txtKodeBarang.setText(barang.getKdBarang());
		txtNamaBarang.setText(barang.getNmBarang());
		txtHargaBeli.setText(barang.getHargabeli().toString());
		txtHargaJual.setText(barang.getHargajual().toString());
	}
	
	public void bCariPelangganActionPerformed(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPelanggan.fxml"));
		Parent root = loader.load();
		DataPelangganController controller = loader.getController();
		controller.setParentController(this);
		controller.open(root);
	}
	
	public void bCariBarangActionPerformed(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Barang.fxml"));
		Parent root = loader.load();
		BarangController controller = loader.getController();
		controller.setParentController(this);
		controller.open(root);
	}
	
	public void setParentController(IsiController controller) {
		parentController = controller;
		listIsi = parentController.getListIsi();
	}

	public void updateForm(Parent root, Isi rowData) {
		// TODO Auto-generated method stub
		
	}

	private void txtQuantityActionPerformed() {
		// TODO Auto-generated method stub
		txtQuantity.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				try {
					int hargaJual = Integer.parseInt(txtHargaJual.getText());
					int qty = Integer.parseInt(arg2);
					int total = qty * hargaJual;
					txtTotal.setText(String.valueOf(total));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public void bTambahActionPerformed(ActionEvent event) {
		Isi isi = new Isi(
			parentController.getIdNota(),
			txtKodeBarang.getText(),
			txtNamaBarang.getText(),
			Integer.parseInt(txtHargaBeli.getText()),
			Integer.parseInt(txtHargaJual.getText()),
			Integer.parseInt(txtQuantity.getText())
		);
		listIsi.add(isi);
		parentController.setListIsi(listIsi);
		parentController.updateTable(isi);
		Pelanggan pelanggan = new Pelanggan();
		pelanggan.setId(txtIdPelanggan.getText());
		pelanggan.setNmplgn(txtNamaPelanggan.getText());
		pelanggan.setAlamat(txtAlamat.getText());
		parentController.setPelanggan(pelanggan);
	}
	
}
