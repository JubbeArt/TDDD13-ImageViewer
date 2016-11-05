package jeswr740;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ButtonController {
	
	@FXML VBox container;
	@FXML ImageView image;
	@FXML Label output;
			
	private File file;
	private double rotation = 0;
	private double scale = 1;
	
	private double scaleFactor = 0.1;
	
    @FXML protected void openImage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose an image");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.gif"));
        file = chooser.showOpenDialog(null);
        
        if(file == null)
        	return;
        
        rotation = 0;
        scale = 1;

        image.setImage(new Image(file.toURI().toString()));
        Main.stage.setTitle(file.getName());
    
        redrawWindow();
    }

    @FXML protected void rotateClockwise(ActionEvent event) {
    	rotation += 90;
    	redrawWindow();
    }
    
    @FXML protected void rotateCounterclockwise(ActionEvent event) {
        rotation -= 90;
        redrawWindow();
    }
    
    @FXML protected void increaseSize(ActionEvent event) {
        scale += scaleFactor;
        redrawWindow();
    }
    
    @FXML protected void decreaseSize(ActionEvent event) {
    	if(scale > scaleFactor) {
    		scale -= scaleFactor;
    		redrawWindow();
    	}
    }
     
    private void redrawWindow() {
    	if(image.getImage() == null)
    		return;
    	
    	image.setRotate(rotation);
    	image.setScaleX(scale);
    	image.setScaleY(scale);
    	
    	double imgW = scale * image.getImage().getWidth();
    	double imgH = scale * image.getImage().getHeight();
    	
    	boolean isNotTilted = rotation % 180 == 0;
    	
    	double winW = isNotTilted ? imgW : imgH;
    	double winH = isNotTilted ? imgH : imgW;
    	
    	int padding = 40;
    	
    	if(winW + padding > container.getWidth())
    		container.setMinWidth(winW + padding);
    	if(winH + padding > container.getHeight())
    		container.setMinHeight(winH + padding);
    	
      	double size = file.length() / 1000.0;    	
    	output.setText(String.format(" %d x %d pixels  %.2f kB", (int) winW, (int) winH, size));
      	
    	Main.stage.sizeToScene();    	
    }
        
}
