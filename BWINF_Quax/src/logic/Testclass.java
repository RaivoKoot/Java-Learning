package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public class Testclass
{
	/*
	 * public static void main(String[] args) { Location destination = new
	 * Location(50, 50); Zone dest = new Zone(); dest.setPixels_in_zone(new
	 * Location[] { destination });
	 * 
	 * Zone z1 = new Zone(new Location(1, 1), dest); Zone z2 = new Zone(new
	 * Location(2, 2), dest); Zone z3 = new Zone(new Location(3, 3), dest); Zone z4
	 * = new Zone(new Location(4, 4), dest);
	 * 
	 * z1.setComputed(false); z2.setComputed(true); z3.setComputed(true);
	 * z4.setComputed(true);
	 * 
	 * ZoneList zl = new ZoneList(); zl.addZone(z1); zl.addZone(z2); zl.addZone(z3);
	 * zl.addZone(z4); zl.sort();
	 * 
	 * System.out.println(Arrays.toString(zl.getZones().toArray()));
	 * 
	 * System.out.println(zl.get_next_closest_zone().toString());
	 * //System.out.println(zl.get_next_closest_zone().isPassable(map)); }
	 */

	public static void main(String[] args)
	{
		File image_file = new File("C:\\Users\\spong\\quax2.png");
		BufferedImage buffered_map = null;

		try
		{
			buffered_map = ImageIO.read(image_file);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image_Data_Scanner setup = new Image_Data_Scanner(buffered_map);

		Location start_loc = new Location(844, 66);
		Image_Data_Scanner.setStart(start_loc);

		Image_Data_Scanner.find_starts_and_destination();
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

}
