package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.koneksi.Koneksi;
import application.model.Barang;
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

public class BarangController implements Initializable {

	private Connection conn = new Koneksi().connect();
	
	@FXML
	private TableView<Barang> tableBarang;
	
	@FXML
	private TableColumn<Barang, String> kdBarang;
	
	@FXML
	private TableColumn<Barang, String> namaBarang;
	
	@FXML
	private TableColumn<Barang, String> jenisBarang;
	
	@FXML
	private TableColumn<Barang, Integer> hargaBeli;
	
	@FXML
	private TableColumn<Barang, Integer> hargaJual;
	
	@FXML
	private TextField txtcari;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datatable();
	}
	
	protected void datatable() {
		System.out.println("datatable");
		ObservableList<Barang> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = "";
			String sql = "SELECT * FROM barang where kd_barang like '%"+cariitem+"%' or nm_barang like '%"+cariitem+"%' order by kd_barang asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Barang barang = new Barang(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getString(3),
					hasil.getInt(4),
					hasil.getInt(5)
				);
				list.add(barang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		kdBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("kdBarang"));
		namaBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("nmBarang"));
		jenisBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("jenis"));
		hargaBeli.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("hargabeli"));
		hargaJual.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("hargajual"));
		tableBarang.setItems(list);
		tableBarang.setRowFactory(
			tv -> {
				TableRow<Barang>  row = new TableRow<>();
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
		ObservableList<Barang> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = txtcari.getText();
			String sql = "SELECT * FROM barang where kd_barang like '%"+cariitem+"%' or nm_barang like '%"+cariitem+"%' order by kd_barang asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				Barang barang = new Barang(
						hasil.getString(1),
						hasil.getString(2),
						hasil.getString(3),
						hasil.getInt(4),
						hasil.getInt(5)
					);
					list.add(barang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		kdBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("kdBarang"));
		namaBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("nmBarang"));
		jenisBarang.setCellValueFactory(new PropertyValueFactory<Barang, String>("jenis"));
		hargaBeli.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("hargabeli"));
		hargaJual.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("hargajual"));
		tableBarang.setItems(list);
	}
	
	public void tblplgMouseClicked(MouseEvent event, TableRow<Barang>  row){
		if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
			Barang rowData = row.getItem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BarangForm.fxml"));
			try {
				Parent root1 = (Parent) loader.load();
				BarangFormController controller = loader.getController();
				controller.updateForm(rowData);
				Scene scene = new Scene(root1);
				String css = this.getClass().getResource("application.css").toExternalForm();
				scene.getStylesheets().add(css);
				Stage stage = new Stage();
				Image image = new Image("application/img/Dva.png");
				stage.getIcons().add(image);
				stage.setTitle("Form Barang");
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void showForm(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BarangForm.fxml"));
			Parent root1 = (Parent) loader.load();
			
			BarangFormController controller = loader.getController();
			controller.createForm(root1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
