package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;

import application.koneksi.Koneksi;
import application.model.Pelanggan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.StageStyle;

public class DataPelangganController implements Initializable {

	private Connection conn = new Koneksi().connect();
	
	@FXML
	private TableView<Pelanggan> tablePelanggan;
	
	@FXML
	private TableColumn<Pelanggan, String> idPelanggan;
	
	@FXML
	private TableColumn<Pelanggan, String> nama;
	
	@FXML
	private TableColumn<Pelanggan, String> jenisKelamin;
	
	@FXML
	private TableColumn<Pelanggan, String> telepon;
	
	@FXML
	private TableColumn<Pelanggan, String> alamat;
	
	@FXML
	private TextField txtcari;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datatable();
	}
	
	protected void datatable() {
		System.out.println("datatable");
		ObservableList<Pelanggan> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = "";
			String sql = "SELECT * FROM pelanggan where id like '%"+cariitem+"%' or nmplgn like '%"+cariitem+"%' order by id asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Pelanggan pelanggan = new Pelanggan(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getString(3),
					hasil.getString(4),
					hasil.getString(5)
				);
				list.add(pelanggan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idPelanggan.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("id"));
		nama.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("nmplgn"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("jenis"));
		telepon.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("telepon"));
		alamat.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("alamat"));
		tablePelanggan.setItems(list);
		tablePelanggan.setRowFactory(
			tv -> {
				TableRow<Pelanggan>  row = new TableRow<>();
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
		System.out.println("im here");
		ObservableList<Pelanggan> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = txtcari.getText();
			String sql = "SELECT * FROM pelanggan where id like '%"+cariitem+"%' or nmplgn like '%"+cariitem+"%' order by id asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Pelanggan pelanggan = new Pelanggan(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getString(3),
					hasil.getString(4),
					hasil.getString(5)
				);
				list.add(pelanggan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idPelanggan.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("id"));
		nama.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("nmplgn"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("jenis"));
		telepon.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("telepon"));
		alamat.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("alamat"));
		tablePelanggan.setItems(list);
	}
	
	public void tblplgMouseClicked(MouseEvent event, TableRow<Pelanggan>  row){
		if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
		Pelanggan rowData = row.getItem();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPelangganForm.fxml"));
		try {
			Parent root1 = (Parent) loader.load();
			DataPelangganFormController controller = loader.getController();
			controller.updateForm(rowData);
			Scene scene = new Scene(root1);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			Image image = new Image("application/img/Dva.png");
			stage.getIcons().add(image);
			stage.setTitle("Form Data Pelanggan");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	}
	
	public void showForm(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPelangganForm.fxml"));
			Parent root1 = (Parent) loader.load();
			
			DataPelangganFormController controller = loader.getController();
			controller.createForm(root1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
