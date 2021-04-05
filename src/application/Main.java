package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginMenu.fxml"));
			Parent root = loader.load();
			
			LoginController controller = loader.getController();
			Image banner = new Image("application/login2.png");
			controller.setBanner(banner);
			
			Scene scene = new Scene(root,960,540);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			String css = getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Image image = new Image("application/img/Dva.png");
			primaryStage.setTitle("Pemrograman Visual");
			primaryStage.getIcons().add(image);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
