package logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.Data;

public class Image_Scanner
{
	private static ArrayList<Location> starts = new ArrayList<Location>();

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

		int width = Data.getWidth();
		int height = Data.getHeight();

		for (int w = 0; w < width; w++)
		{
			for (int h = 0; h < height; h++)
			{
				int color = Data.getMap().getRGB(w, h);
				if (!colors.contains(color))
					colors.add(color);
			}
		}

		for (Integer color : colors)
			System.out.println(color);

	}

	public static void find_starts_and_destination()
	{
		int width = Data.getMap().getWidth();
		int height = Data.getMap().getHeight();
		BufferedImage map = Data.getMap();

		int green = -16711936;
		int red = -65536;

		for (int w = 0; w < width; w++)
		{
			for (int h = 0; h < height; h++)
			{
				int pixel = map.getRGB(w, h);

				if (pixel == green)
					Data.setDestination(new Location(w, h));
				else if (pixel == red)
					starts.add(new Location(w, h));

			}
		}
	}

	public static String starts_toString()
	{
		String s = "Starts\n";

		for (Location start : starts)
			s += start.toString() + "\n";

		System.out.println("\nDestination " + Data.getDestination().toString());

		return s;
	}

	public static BufferedImage visualize_locations()
	{
		BufferedImage map = Data.getMap();

		for (Location pixel : starts)
		{
			int x = pixel.getX();
			int y = pixel.getY();

			for (int i = 0; i < 10; i++)
				for (int k = 0; k < 10; k++)
					map.setRGB(x + i, y + k, -65536);

		}

		int x = Data.getDestination().getX();
		int y = Data.getDestination().getY();

		for (int i = 0; i < 10; i++)
			for (int k = 0; k < 10; k++)
				map.setRGB(x + i, y + k, -16711936);

		return map;
	}

}
