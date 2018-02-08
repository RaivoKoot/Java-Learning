package logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Image_Data_Scanner
{
	private static ArrayList<Location> starts;
	private static Location destination;
	private static Location start;
	private static BufferedImage map;

	private static int width;
	private static int height;
	
	private static int theta_one;
	private static int theta_two;
	private static int theta_three;

	private static ArrayList<Location> waypoints;

	public Image_Data_Scanner(BufferedImage map)
	{
		theta_one = 4;
		theta_two = 1;
		theta_three = 1;

		waypoints = new ArrayList<Location>();
		starts = new ArrayList<Location>();
		destination = null;

		Image_Data_Scanner.setWidth(map.getWidth());
		Image_Data_Scanner.setHeight(map.getHeight());

		this.map = map;
	}

	public static Location getDestination()
	{
		return destination;
	}

	public static void setDestination(Location destination)
	{
		Image_Data_Scanner.destination = destination;
	}

	public ArrayList<Location> getStarts()
	{
		return starts;
	}

	// -1 is white
	// -65536 is red
	// -16711936 is green
	// -16777216 is black
	// this method is to find out the integer values corresponding to colors of the
	// image
	public void print_colors()
	{

		ArrayList<Integer> colors = new ArrayList<Integer>();

		int width = map.getWidth();
		int height = map.getHeight();

		for (int w = 0; w < width; w++)
		{
			for (int h = 0; h < height; h++)
			{
				int color = map.getRGB(w, h);
				if (!colors.contains(color))
					colors.add(color);
			}
		}

		for (Integer color : colors)
			System.out.println(color);

	}

	public static void find_starts_and_destination()
	{
		int width = map.getWidth();
		int height = map.getHeight();

		int green = -16711936;
		int red = -65536;

		for (int w = 0; w < width; w++)
		{
			for (int h = 0; h < height; h++)
			{
				int pixel = map.getRGB(w, h);

				if (pixel == green)
					setDestination(new Location(w, h));
				else if (pixel == red)
					starts.add(new Location(w, h));

			}
		}
	}

	public String starts_toString()
	{
		String s = "Starts\n";

		for (Location start : starts)
			s += start.toString() + "\n";

		System.out.println("\nDestination " + destination.toString());

		return s;
	}

	public BufferedImage visualize_locations()
	{

		for (Location pixel : starts)
		{
			int x = pixel.getX();
			int y = pixel.getY();

			for (int i = 0; i < 10; i++)
				for (int k = 0; k < 10; k++)
					map.setRGB(x + i, y + k, -65536);

		}

		int x = destination.getX();
		int y = destination.getY();

		for (int i = 0; i < 10; i++)
			for (int k = 0; k < 10; k++)
				map.setRGB(x + i, y + k, -16711936);

		return map;
	}

	public static Location getStart()
	{
		return start;
	}

	public static void setStart(Location start)
	{
		Image_Data_Scanner.start = start;
		waypoints.add(start);
	}

	public static BufferedImage getMap()
	{
		return map;
	}

	public static void setMap(BufferedImage map)
	{
		Image_Data_Scanner.map = map;
	}

	public static void add_waypoint(Location loc)
	{
		waypoints.add(loc);
	}

	public static ArrayList<Location> getWaypoints()
	{
		return waypoints;
	}

	public static int getWidth()
	{
		return width;
	}

	public static void setWidth(int width)
	{
		Image_Data_Scanner.width = width;
	}

	public static int getHeight()
	{
		return height;
	}

	public static void setHeight(int height)
	{
		Image_Data_Scanner.height = height;
	}

	public static int getTheta_one()
	{
		return theta_one;
	}

	public static void setTheta_one(int theta_one)
	{
		Image_Data_Scanner.theta_one = theta_one;
	}

	public static int getTheta_two()
	{
		return theta_two;
	}

	public static void setTheta_two(int theta_two)
	{
		Image_Data_Scanner.theta_two = theta_two;
	}

	public static int getTheta_three()
	{
		return theta_three;
	}

	public static void setTheta_three(int theta_three)
	{
		Image_Data_Scanner.theta_three = theta_three;
	}
}
