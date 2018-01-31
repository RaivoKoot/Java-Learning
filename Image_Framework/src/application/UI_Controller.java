package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UI_Controller implements Displays_Images, Initializable {

	@FXML
	private Button btn_file_select;
	@FXML
	private Label lbl_selected_file_name;
	@FXML
	private Button btn_start_algorithm;

	@FXML
	private ImageView map_container;
	private File map;

	private Logic_Layer logic;

	// makes the image as large as possible depending on size of monitor
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initialize_imageView_size();
		
		logic = new Logic_Layer();

	}

	public void btn_file_select_clicked() {
		File image_file = select_a_file_in_explorer();

		setMap(image_file);

		display_image_file(getMap());
	}

	public void btn_start_algorithm_clicked() {

		logic.setMap(map);
		logic.edit_image();
		BufferedImage new_map = logic.getMap();
		
		display_bufferedImage(new_map);
	}

	@Override
	public void display_bufferedImage(BufferedImage buffered_image) {

		Image image = SwingFXUtils.toFXImage(buffered_image, null);

		display_image(image);

	}

	@Override
	public void display_image_file(File image_file) {

		Image image = new Image(image_file.toURI().toString());

		display_image(image);

	}

	@Override
	public void display_image(Image image) {

		map_container.setImage(image);

	}

	@Override
	public File select_a_file_in_explorer() {
		FileChooser file_chooser = new FileChooser();
		file_chooser.setTitle("chose the map file");
		file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));

		Stage file_chooser_window = new Stage();

		return file_chooser.showOpenDialog(file_chooser_window);
	}

	public File getMap() {
		return map;
	}

	public void setMap(File map) {
		this.map = map;
	}

	public void initialize_imageView_size() {
		// gets the size of the screen in pixels
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

		double screen_width = screen_size.width;
		double screen_height = screen_size.height;

		// makes the imageView about as large as the host monitor
		map_container.setFitWidth(screen_width);
		map_container.setFitHeight(screen_height - 150); // leave space for top pane
	}

}
