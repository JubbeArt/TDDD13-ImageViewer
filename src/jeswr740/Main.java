package jeswr740;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	String fxmlFile = "image_viewer.fxml";
	static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
		
		stage = primaryStage;
		
		primaryStage.setTitle("ImageViewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();	
	}

}
