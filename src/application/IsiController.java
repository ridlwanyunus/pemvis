package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import application.koneksi.Koneksi;
import application.model.Barang;
import application.model.Isi;
import application.model.Nota;
import application.model.Pelanggan;
import application.model.UserID;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class IsiController implements Initializable{
private Connection conn = new Koneksi().connect();
	
	@FXML
	private TableView<Isi> tableIsi;
	
	@FXML
	private TableColumn<Isi, String> kdBarang;
	
	@FXML
	private TableColumn<Isi, String> nama;
	
	@FXML
	private TableColumn<Isi, Integer> hb;
	
	@FXML
	private TableColumn<Isi, Integer> hj;
	
	@FXML
	private TableColumn<Isi, Integer> qty;
	
	@FXML
	private TableColumn<Isi, Integer> total;
	
	@FXML
	private TextField txtcari, txtNota, txtTotal;
	
	@FXML
	private Label lblIdKasir, lblName;
	
	@FXML
	private MFXDatePicker txtTglNota;
	
	private IsiFormController insertChildController;
	private IsiFormController updateChildController;
	
	private NotaController parentController;
	
	@FXML
	private Button btnTambah, btnSimpan, btnHapus, btnBatal, btnKeluar, btnCari;
	
	private ObservableList<Isi> listIsi = FXCollections.observableArrayList();
	
	private String idNota;

	private Pelanggan pelanggan;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nama();
		autoNumber();
		datatable();
		hitung();
	}
	
	protected void nama() {
		lblIdKasir.setText(UserID.getUserLogin());
		try {
			String sql = "SELECT * FROM kasir WHERE id_kasir='"+UserID.getUserLogin()+"'";
			Statement stmt = conn.createStatement();
			ResultSet hasil = stmt.executeQuery(sql);
			if(hasil.next()) {
				lblName.setText(hasil.getString("nm_kasir"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void datatable() {
		ObservableList<Isi> list = FXCollections.observableArrayList() ;
		
		try {
			String cariitem = txtNota.getText();
			System.out.println(txtNota.getText());
			String sql = "SELECT * FROM isi where id_nota like '%"+cariitem+"%' order by id_nota asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				
				Isi isi = new Isi(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getString(3),
					hasil.getInt(4),
					hasil.getInt(5),
					hasil.getInt(6)
				);
				list.add(isi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		kdBarang.setCellValueFactory(new PropertyValueFactory<Isi, String>("idBarang"));
		nama.setCellValueFactory(new PropertyValueFactory<Isi, String>("nmBrg"));
		hb.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hb"));
		hj.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hj"));
		qty.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("qty"));
		total.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHj() * cellData.getValue().getQty()).asObject());
		tableIsi.setItems(list);
		tableIsi.setRowFactory(
			tv -> {
				TableRow<Isi>  row = new TableRow<>();
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
		ObservableList<Isi> list = FXCollections.observableArrayList() ;
		try {
			String nmBrg = txtcari.getText();
			String sql = "SELECT * FROM isi where id_nota like '%"+txtNota.getText()+"%' AND nm_brg like '"+nmBrg+"' order by id_nota asc";
			Statement stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			
			while(hasil.next()) {
				
				Isi isi = new Isi(
						hasil.getString(1),
						hasil.getString(2),
						hasil.getString(3),
						hasil.getInt(4),
						hasil.getInt(5),
						hasil.getInt(6)
				);
				list.add(isi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		kdBarang.setCellValueFactory(new PropertyValueFactory<Isi, String>("idBarang"));
		nama.setCellValueFactory(new PropertyValueFactory<Isi, String>("nmBrg"));
		hb.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hb"));
		hj.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hj"));
		qty.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("qty"));
		total.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHj() * cellData.getValue().getQty()).asObject());
		tableIsi.setItems(list);
	}
	
	public void updateTable(Isi isi) {
		ObservableList<Isi> list = getListIsi();

		kdBarang.setCellValueFactory(new PropertyValueFactory<Isi, String>("idBarang"));
		nama.setCellValueFactory(new PropertyValueFactory<Isi, String>("nmBrg"));
		hb.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hb"));
		hj.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("hj"));
		qty.setCellValueFactory(new PropertyValueFactory<Isi, Integer>("qty"));
		total.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHj() * cellData.getValue().getQty()).asObject());
		tableIsi.setItems(list);
	}
	
	public void tblplgMouseClicked(MouseEvent event, TableRow<Isi>  row){
		if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
			Isi rowData = row.getItem();
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IsiForm.fxml"));
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
	
	public void updateForm(Parent root, Nota nota) {

		Scene scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		Stage stage = new Stage();
		Image image = new Image("application/img/Dva.png");
		stage.getIcons().add(image);
		stage.setTitle("Form Isi Nota");
		stage.setScene(scene);
		stage.show();
	}
	
	public void setParentController(NotaController controller) {
		parentController = controller;
	}
	
	public ObservableList<Isi> getListIsi() {
		return listIsi;
	}

	public void setListIsi(ObservableList<Isi> listIsi) {
		this.listIsi = listIsi;
	}
	
	public void autoNumber() {
		
		try {
			String sql = "SELECT id_nota FROM nota order by id_nota asc";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			txtNota.setText("IN0001");
			
			while(rs.next()) {
				idNota = rs.getString("id_nota").substring(2);
				int an = Integer.parseInt(idNota) + 1;
				
				String nol = "";
				if(an < 10) {
					nol = "000";
				} else
				if(an < 100) {
					nol = "00";
				} else
				if(an < 1000) {
					nol = "0";
				} else 
				if(an < 10000) {
					nol = "";
				}
				
				txtNota.setText("IN"+nol+an);
				
			}
			txtNota.setEditable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void bSimpanActionPerformed(ActionEvent event) throws IOException {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fd = txtTglNota.getDate().format(sdf);
		String sql = "INSERT INTO nota VALUES (?,?,?,?)";
		String zsql = "INSERT INTO isi VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, txtNota.getText());
			stat.setString(2, fd);
			stat.setString(3, pelanggan.getId());
			stat.setString(4, lblIdKasir.getText());
			stat.executeUpdate();
			
			int t = tableIsi.getItems().size();
			for(int i=0; i<t; i++) {
				String xkd = tableIsi.getItems().get(i).getIdBarang();
				String xnm = tableIsi.getItems().get(i).getNmBrg();
				int xhb = tableIsi.getItems().get(i).getHb();
				int xhj = tableIsi.getItems().get(i).getHj();
				int qty = tableIsi.getItems().get(i).getQty();
				PreparedStatement stat2 = conn.prepareStatement(zsql);
				stat2.setString(1, txtNota.getText());
				stat2.setString(2, xkd);
				stat2.setString(3, xnm);
				stat2.setInt(4, xhb);
				stat2.setInt(5, xhj);
				stat2.setInt(6, qty);
				stat2.executeUpdate();
			}
			cetak();
			myAlert("Data berhasil disimpan");
		} catch (Exception e) {
			myAlert("Data gagal disimpan");
			e.printStackTrace();
		}
		
		
	}
	
	public void bHapusActionPerformed(ActionEvent event) {
		Isi isi = tableIsi.getSelectionModel().getSelectedItem();
		listIsi.remove(isi);
		updateTable(isi);
		
	}
	
	public String getIdNota() {
		return idNota;
	}

	public void setIdNota(String idNota) {
		this.idNota = idNota;
	}
	
	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
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
	
	public void hitung() {
		listIsi.addListener(new ListChangeListener<Isi>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Isi> pChange) {
				while(pChange.next()) {
					int total = 0;
					int size = tableIsi.getItems().size();
					
					for(int i=0; i<size; i++) {
						total = total + Integer.valueOf(tableIsi.getItems().get(i).getHj() * tableIsi.getItems().get(i).getQty());
					}
					txtTotal.setText(String.valueOf(total));
				}
			}
		});
	}
	
	public void cetak() {
		try {
			String path = "./src/application/report6.jasper";
			HashMap parameter = new HashMap<>();
			parameter.put("id_nota", txtNota.getText());
			JasperPrint print = JasperFillManager.fillReport(path, parameter, conn);
			JasperViewer.viewReport(print, false);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
