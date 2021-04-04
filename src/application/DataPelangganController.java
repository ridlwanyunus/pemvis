package application;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DataPelangganController implements Initializable {

	@FXML
	private TableView<Pelanggan> tablePelanggan;
	
	@FXML
	private TableColumn<Pelanggan, Integer> idPelanggan;
	
	@FXML
	private TableColumn<Pelanggan, String> nama;
	
	@FXML
	private TableColumn<Pelanggan, String> jenisKelamin;
	
	@FXML
	private TableColumn<Pelanggan, String> telepon;
	
	@FXML
	private TableColumn<Pelanggan, String> alamat;
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Pelanggan> list = FXCollections.observableArrayList(
				new Pelanggan(1, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(2, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(3, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(4, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(1, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(2, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(3, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(4, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(1, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(2, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(3, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(4, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(1, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(2, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(3, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(4, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah"),
				new Pelanggan(5, "Ridlwan Yunus", "Laki-Laki", "082212345678", "Jln Madrasah")
		) ;
		
		// TODO Auto-generated method stub
		idPelanggan.setCellValueFactory(new PropertyValueFactory<Pelanggan, Integer>("idPelanggan"));
		nama.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("nama"));
		jenisKelamin.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("jenisKelamin"));
		telepon.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("telepon"));
		alamat.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("alamat"));
		tablePelanggan.setItems(list);
		tablePelanggan.setRowFactory(
			tv -> {
				TableRow<Pelanggan>  row = new TableRow<>();
				row.setOnMouseClicked(
					event -> {
						if(event.getClickCount() == 2 && (!(row.isEmpty()))) {
							Pelanggan rowData = row.getItem();
							System.out.println(rowData.getIdPelanggan());
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
				);
				return row;
			}
		);
	}

	public void showForm(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DataPelangganForm.fxml"));
			Parent root1 = (Parent) loader.load();
			
			DataPelangganFormController controller = loader.getController();
			controller.createForm(root1);
			
//			Scene scene = new Scene(root1);
//			String css = this.getClass().getResource("application.css").toExternalForm();
//			scene.getStylesheets().add(css);
//			Stage stage = new Stage();
//			Image image = new Image("application/img/Dva.png");
//			stage.getIcons().add(image);
//			stage.setTitle("Form Data Pelanggan");
//			stage.setScene(scene);
//			stage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
