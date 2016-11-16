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
	
	// UI-objects generated from the main FXML-file
	@FXML VBox container;
	@FXML ImageView image;
	@FXML Label output;
	
	private File file;
	
	private double rotation = 0;
	private double scale = 1;
	private double scaleFactor = 0.1;
	
	// Opens an image an sets it on the ImageView
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

    // Rotate the image 90 deg to the right
    @FXML protected void rotateClockwise(ActionEvent event) {
    	rotation += 90;
    	redrawWindow();
    }
    
    // Rotates the image 90 deg to the left
    @FXML protected void rotateCounterclockwise(ActionEvent event) {
        rotation -= 90;
        redrawWindow();
    }
    
    // Scale the image up by the scale factor
    @FXML protected void increaseSize(ActionEvent event) {
        scale += scaleFactor;
        redrawWindow();
    }
    
    // Decrease the size of the image by the scale factor.
    // Will not decrease the size if the current scale is already 0.
    @FXML protected void decreaseSize(ActionEvent event) {
    	if(scale > scaleFactor) {
    		scale -= scaleFactor;
    		redrawWindow();
    	}
    }
     
    // Redraws the current image by the current rotation and scale.
    // Resizes the window if necessary.
    // Also prints the current image size and file size. 
    private void redrawWindow() {
    	if(image.getImage() == null)
    		return;
    	
    	// Transform the image
    	image.setRotate(rotation);
    	image.setScaleX(scale);
    	image.setScaleY(scale);
    	
    	double imgW = scale * image.getImage().getWidth();
    	double imgH = scale * image.getImage().getHeight();
    	
    	boolean isNotTilted = rotation % 180 == 0;
    	
    	// Calculate the image-size
    	double winW = isNotTilted ? imgW : imgH;
    	double winH = isNotTilted ? imgH : imgW;
    	
    	int padding = 40;

    	// Checks if window needs to be bigger
    	if(winW + padding > container.getWidth())
    		container.setMinWidth(winW + padding);
    	if(winH + padding > container.getHeight())
    		container.setMinHeight(winH + padding);
    	
    	// Output current image size and image size (the size doesnt change)
      	double size = file.length() / 1000.0;    	
    	output.setText(String.format(" %d x %d pixels  %.2f kB", (int) winW, (int) winH, size));
      	
    	Main.stage.sizeToScene();    	
    }
        
}
