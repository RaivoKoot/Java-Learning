package application;

import java.awt.image.BufferedImage;
import java.io.File;

import javafx.scene.image.Image;

public interface Displays_Images {

	public void display_bufferedImage(BufferedImage buffered_image);
	
	public void display_image_file(File image_file);
	
	public void display_image(Image image);
	
	public File select_a_file_in_explorer();
}
