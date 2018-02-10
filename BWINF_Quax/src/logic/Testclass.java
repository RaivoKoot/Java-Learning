package logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.Data;

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
		BufferedImage map = null;
		try
		{
			map = ImageIO.read(new File("C:\\Users\\spong\\quax3.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Data.setMap(map);
		Data.add_waypoint(new Location(1898, 591));
		Image_Scanner.find_starts_and_destination();

		Location destination = Data.getDestination();
		Zone dest = new Zone();
		dest.setPixels_in_zone(new Location[] { destination });
		Zone start = new Zone(new Location(1898, 591), dest);

		ArrayList<Zone> path = Pathfinding_Algorithm.find_path(start, dest, map);

	}

}
