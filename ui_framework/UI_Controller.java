package user_interface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import data.Data;
import java.awt.Color;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Location;
import logic.Pathfinding_Algorithm;
import logic.Zone;
import logic.Image_Scanner;

public class UI_Controller implements Displays_Images, Initializable {

	@FXML
	private Button btn_select_file;
	@FXML
	private Button btn_scan_starts;
	@FXML
	private Button btn_run_algorithm;
	@FXML
	private Button btn_visualize_results;

	@FXML
	private TextField tfield_x;
	@FXML
	private TextField tfield_y;
	@FXML
	private TextArea tarea_starts;

	@FXML
	private ImageView map_container;
	private File map;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//initialize_imageView_size();

	}

	public void btn_file_select_clicked() {
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
	}

	public File getMap() {
		return map;
	}

	public void setMap(File map) {
		this.map = map;
	}

	// makes the image as large as possible depending on size of monitor
	public void initialize_imageView_size() {
		// gets the size of the screen in pixels
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

		double screen_width = screen_size.width;
		double screen_height = screen_size.height;

		// makes the imageView about as large as the host monitor
		map_container.setFitWidth(screen_width);
		map_container.setFitHeight(screen_height - 150); // leave space for top
															// pane
	}

	/*
	 * scans for the vertices of the starts and displays them in text field
	 * Enlarges these in the image for visualization purposes
	 */
	public void scan_starts_clicked() {
	}

	public void run_algorithm() {

	}

	/*
	 * multithreaded approach to showing the progress of the algorithm in the
	 * displayed picture
	 */
	public void visualize_results() {
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					// final CountDownLatch latch = new CountDownLatch(1);

					@Override
					protected Void call() throws Exception {

						ArrayList<Zone> path = Data.getPath();
						BufferedImage buffered_map = Data.getMap();

						// background work
						for (Zone zone : path) {
							// logic.edit_pixel(x, 300, Color.CYAN.getRGB(),
							// buffered_map);
							Location location = zone.getLocation();
							buffered_map.setRGB(location.getX(), location.getY(), -16711936);

							// FX work
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										display_bufferedImage(buffered_map);
									} finally {
										// latch.countDown();
									}
								}
							});

							Thread.sleep(20);
						}

						// latch.await();

						// Keep with the background work
						return null;
					}
				};
			}
		};
		service.start();
	}

}
