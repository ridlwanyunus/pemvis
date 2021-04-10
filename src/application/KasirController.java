package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.koneksi.Koneksi;
import application.model.Barang;
import application.model.Kasir;
import application.model.Pelanggan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KasirController implements Initializable {

	private Connection conn = new Koneksi().connect();
	
	@FXML
	private TableView<Kasir> tableKasir;
	
	@FXML
	private TableColumn<Kasir, String> idKasir;
	
	@FXML
	private TableColumn<Kasir, String> nmKasir;
	
	@FXML
	private TableColumn<Kasir, String> jenisKelamin;
	
	@FXML
	private TableColumn<Kasir, String> telepon;
	
	@FXML
	private TableColumn<Kasir, String> agama;
	
	@FXML
	private TableColumn<Kasir, String> alamat;
	
	@FXML
	private TextField txtcari;
	
	private KasirFormController insertChildController;
	private KasirFormController updateChildController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datatable();
	}
	
	protected void datatable() {
		ObservableList<Kasir> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = "";
			String sql = "SELECT * FROM kasir where id_kasir like '%"+cariitem+"%' or nm_kasir like '%"+cariitem+"%' order by id_kasir asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Kasir kasir = new Kasir(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getString(3),
					hasil.getString(4),
					hasil.getString(5),
					hasil.getString(6),
					null
				);
				list.add(kasir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idKasir.setCellValueFactory(new PropertyValueFactory<Kasir, String>("idKasir"));
		nmKasir.setCellValueFactory(new PropertyValueFactory<Kasir, String>("nmKasir"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Kasir, String>("jenisKelamin"));
		telepon.setCellValueFactory(new PropertyValueFactory<Kasir, String>("noTelepon"));
		agama.setCellValueFactory(new PropertyValueFactory<Kasir, String>("agama"));
		alamat.setCellValueFactory(new PropertyValueFactory<Kasir, String>("alamat"));
		tableKasir.setItems(list);
		tableKasir.setRowFactory(
			tv -> {
				TableRow<Kasir>  row = new TableRow<>();
				row.setOnMouseClicked(
					event -> {
						tblplgMouseClicked(event, row);
					}
				);
				return row;
			}
		);
	}

	public void refresh(ActionEvent event) {
		ObservableList<Kasir> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = txtcari.getText();
			String sql = "SELECT * FROM kasir where id_kasir like '%"+cariitem+"%' or nm_kasir like '%"+cariitem+"%' order by id_kasir asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Kasir kasir = new Kasir(
						hasil.getString(1),
						hasil.getString(2),
						hasil.getString(3),
						hasil.getString(4),
						hasil.getString(5),
						hasil.getString(6),
						null
					);
					list.add(kasir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idKasir.setCellValueFactory(new PropertyValueFactory<Kasir, String>("idKasir"));
		nmKasir.setCellValueFactory(new PropertyValueFactory<Kasir, String>("nmKasir"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Kasir, String>("jenisKelamin"));
		telepon.setCellValueFactory(new PropertyValueFactory<Kasir, String>("noTelepon"));
		agama.setCellValueFactory(new PropertyValueFactory<Kasir, String>("agama"));
		alamat.setCellValueFactory(new PropertyValueFactory<Kasir, String>("alamat"));
		tableKasir.setItems(list);
	}
	
	public void tblplgMouseClicked(MouseEvent event, TableRow<Kasir>  row){
		if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
			Kasir rowData = row.getItem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KasirForm.fxml"));
			try {
				Parent root = (Parent) loader.load();
				updateChildController = loader.getController();
				updateChildController.setParentController(this);
				updateChildController.updateForm(root, rowData);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void showForm(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KasirForm.fxml"));
			Parent root = (Parent) loader.load();
			
			insertChildController = loader.getController();
			insertChildController.setParentController(this);
			insertChildController.createForm(root);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
