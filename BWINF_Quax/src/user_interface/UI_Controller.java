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
import logic.Image_Data_Scanner;

public class UI_Controller implements Displays_Images, Initializable
{

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
	private BufferedImage buffered_map;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		initialize_imageView_size();

	}

	public void btn_file_select_clicked()
	{
		File image_file = select_a_file_in_explorer();
		if (image_file != null)
		{
			setMap(image_file);
			try
			{
				setBuffered_map(ImageIO.read(image_file));
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			display_image_file(getMap());
		}
	}

	@Override
	public void display_bufferedImage(BufferedImage buffered_image)
	{

		Image image = SwingFXUtils.toFXImage(buffered_image, null);

		display_image(image);

	}

	@Override
	public void display_image_file(File image_file)
	{

		Image image = new Image(image_file.toURI().toString());

		display_image(image);

	}

	@Override
	public void display_image(Image image)
	{

		map_container.setImage(image);

	}

	@Override
	public File select_a_file_in_explorer()
	{
		FileChooser file_chooser = new FileChooser();
		file_chooser.setTitle("chose the map file");
		file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));

		Stage file_chooser_window = new Stage();

		return file_chooser.showOpenDialog(file_chooser_window);
	}

	public File getMap()
	{
		return map;
	}

	public void setMap(File map)
	{
		this.map = map;
	}

	public void setBuffered_map(BufferedImage buffered_map)
	{
		this.buffered_map = buffered_map;
	}

	// makes the image as large as possible depending on size of monitor
	public void initialize_imageView_size()
	{
		// gets the size of the screen in pixels
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

		double screen_width = screen_size.width;
		double screen_height = screen_size.height;

		// makes the imageView about as large as the host monitor
		map_container.setFitWidth(screen_width);
		map_container.setFitHeight(screen_height - 150); // leave space for top pane
	}

	/*
	 * scans for the vertices of the starts and displays them in text field Enlarges
	 * these in the image for visualization purposes
	 */
	public void scan_starts_clicked()
	{
		Image_Data_Scanner setup = new Image_Data_Scanner(buffered_map);

		Image_Data_Scanner.find_starts_and_destination();

		String starts = setup.starts_toString();
		String dest = Image_Data_Scanner.getDestination().toString();
		tarea_starts.setText(starts + "\nDestination " + dest);

		display_bufferedImage(setup.visualize_locations());
	}

	public void run_algorithm()
	{
		int chosen_x = Integer.parseInt(tfield_x.getText());
		int chosen_y = Integer.parseInt(tfield_y.getText());
		Location start_loc = new Location(chosen_x, chosen_y);
		Image_Data_Scanner.setStart(start_loc);

		Location destination = Image_Data_Scanner.getDestination();
		Zone dest = new Zone();
		dest.setPixels_in_zone(new Location[] { destination });

		Zone start = new Zone(start_loc, dest);

		ArrayList<Zone> path = new ArrayList<Zone>();
		path.add(start);

		Zone[][] pixels_visited = new Zone[buffered_map.getWidth()][buffered_map.getHeight()];
		int waypoint_cooldown = 0;
		int counter = 0;
		int parameter_cooldown = 0;

		ArrayList<Zone> path_new = Pathfinding_Algorithm.take_step(path, pixels_visited, start, dest, buffered_map,
				counter, waypoint_cooldown, parameter_cooldown);

	}

	/*
	 * multithreaded approach to showing the progress of the algorithm in the
	 * displayed picture
	 */
	public void visualize_results()
	{
		Service<Void> service = new Service<Void>()
		{
			@Override
			protected Task<Void> createTask()
			{
				return new Task<Void>()
				{
					// final CountDownLatch latch = new CountDownLatch(1);

					@Override
					protected Void call() throws Exception
					{

						ArrayList<Zone> path_new = Pathfinding_Algorithm.getHistory();

						// background work
						for (Zone zone : path_new)
						{
							// logic.edit_pixel(x, 300, Color.CYAN.getRGB(), buffered_map);
							Location location = zone.getLocation();
							buffered_map.setRGB(location.getX(), location.getY(), -16711936);

							// FX work
							Platform.runLater(new Runnable()
							{
								@Override
								public void run()
								{
									try
									{
										display_bufferedImage(buffered_map);
									} finally
									{
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
