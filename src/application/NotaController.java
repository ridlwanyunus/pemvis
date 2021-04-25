package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import application.koneksi.Koneksi;
import application.model.Barang;
import application.model.Nota;
import application.model.Pelanggan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class NotaController implements Initializable {
	
	private Connection conn = new Koneksi().connect();
	
	@FXML
	private TableView<Nota> tableNota;
	
	@FXML
	private TableColumn<Nota, String> idNota;
	
	@FXML
	private TableColumn<Nota, String> idPelanggan;
	
	@FXML
	private TableColumn<Nota, String> idKasir;
	
	@FXML
	private TableColumn<Nota, Date> tglNota;
	
	@FXML
	private TextField txtcari;
	
	private IsiController insertChildController;
	private IsiController updateChildController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datatable();
		
	}
	
	protected void datatable() {
		ObservableList<Nota> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = "";
			String sql = "SELECT * FROM nota where id_nota like '%"+cariitem+"%' order by id_nota asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				Nota nota = new Nota(
					hasil.getString(1),
					df.parse(hasil.getString(2)),
					hasil.getString(3),
					hasil.getString(4)
				);
				list.add(nota);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idNota.setCellValueFactory(new PropertyValueFactory<Nota, String>("idNota"));
//		tglNota.setCellFactory(column -> {
//			TableCell<Nota, Date> cell = new TableCell<Nota, Date>() {
//				private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//				
//				@Override
//				protected void updateItem(Date item, boolean empty) {
//					super.updateItem(item, empty);
//					if(empty) {
//						setText(null);
//					} else {
//						setText(sdf.format(item));
//					}
//				}
//				
//			};
//			return cell;
//		});
		tglNota.setCellValueFactory(new PropertyValueFactory<Nota, Date>("tglNota"));
		idPelanggan.setCellValueFactory(new PropertyValueFactory<Nota, String>("idPelanggan"));
		idKasir.setCellValueFactory(new PropertyValueFactory<Nota, String>("idKasir"));
		tableNota.setItems(list);
		tableNota.setRowFactory(
			tv -> {
				TableRow<Nota>  row = new TableRow<>();
				row.setOnMouseClicked(
					event -> {
						//tblplgMouseClicked(event, row);
					}
				);
				return row;
			}
		);
	}

	public void refresh(ActionEvent event) {
		ObservableList<Nota> list = FXCollections.observableArrayList() ;
		try {
			String cariitem = txtcari.getText();
			String sql = "SELECT * FROM nota where id_nota like '%"+cariitem+"%' order by id_nota asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while (hasil.next()) {
				Nota nota = new Nota(
					hasil.getString(1), 
					new Date(hasil.getString(2)), 
					hasil.getString(3),
					hasil.getString(4)
				);
				list.add(nota);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		idNota.setCellValueFactory(new PropertyValueFactory<Nota, String>("idNota"));
		tglNota.setCellValueFactory(new PropertyValueFactory<Nota, Date>("tglNota"));
		idPelanggan.setCellValueFactory(new PropertyValueFactory<Nota, String>("idPelanggan"));
		idKasir.setCellValueFactory(new PropertyValueFactory<Nota, String>("idKasir"));
		tableNota.setItems(list);
	}
	
	public void tblplgMouseClicked(MouseEvent event, TableRow<Nota>  row){
		if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
			Nota rowData = row.getItem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Isi.fxml"));
			try {
				Parent root = loader.load();
				updateChildController = loader.getController();
				updateChildController.setParentController(this);
				updateChildController.updateForm(root, rowData);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showForm(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Isi.fxml"));
		Parent root;
		try {
			root = loader.load();
			insertChildController = loader.getController();
			insertChildController.setParentController(this);
			insertChildController.createForm(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
