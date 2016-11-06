package jeswr740;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the ImageViewr. Initializes the program and loads the main FXML-file.
 * 
 * @author Jesper Wrang
 */
public class Main extends Application {

	/**
	 * The name of the main FXML-file.
	 */
	String fxmlFile = "image_viewer.fxml";
	/**
	 * The primary stage in the program.
	 */
	static Stage stage;
	
	/**
	 * Launches the program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Loads the main FXML-file and shows the window.
	 * 
	 * @param primaryStage The main stage given by JavaFx 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
		
		stage = primaryStage;
		
		primaryStage.setTitle("ImageViewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();	
	}

}
