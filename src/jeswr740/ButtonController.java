package jeswr740;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * Class for handling the input from the buttons in the program. 
 * 
 * @author Jesper Wrang (jeswr740) <jeswr740@student.liu.se>
 */
public class ButtonController {
	
	/**
	 * UI-objects generated from the main FXML-file
	 */
	@FXML VBox container;
	@FXML ImageView image;
	@FXML Label output;
			
	/**
	 * The current loaded image.
	 */
	private File file;
	
	/**
	 * Changes made to the image.
	 */
	private double rotation = 0;
	private double scale = 1;
	private double scaleFactor = 0.1;
	
    /**
     * Opens an image and sets its as the current image being viewed.
     * This also resets the changes made to the image.
     * 
     * @param event Not used
     */
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

    /**
     * Rotates the image 90 degrees clockwise.
     * 
     * @param event Not used
     */
    @FXML protected void rotateClockwise(ActionEvent event) {
    	rotation += 90;
    	redrawWindow();
    }
    
    /**
     * Rotates the image 90 degrees counterclockwise
     * 
     * @param event Not used
     */
    @FXML protected void rotateCounterclockwise(ActionEvent event) {
        rotation -= 90;
        redrawWindow();
    }
    
    /**
     * Increase the size of the image by the scale factor.
     * 
     * @param event Not used
     */
    @FXML protected void increaseSize(ActionEvent event) {
        scale += scaleFactor;
        redrawWindow();
    }
    
    /**
     * Decrease the size of the image by the scale factor.
     * Will not decrease the size if the current scale is already 0.
     * 
     * @param event Not used
     */
    @FXML protected void decreaseSize(ActionEvent event) {
    	if(scale > scaleFactor) {
    		scale -= scaleFactor;
    		redrawWindow();
    	}
    }
     
    /**
     * Redraws the current image by the current rotation and scale.
     * Also prints the current image size and file size. 
     */
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
