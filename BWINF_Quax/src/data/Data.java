package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import logic.Location;
import logic.Zone;

public class Data
{

	private static ArrayList<Zone> path;
	private static ArrayList<Location> waypoints = new ArrayList<Location>();

	private static Location destination;

	private static BufferedImage map;

	private static int width;
	private static int height;

	private static ArrayList<Zone> available_nodes = new ArrayList<Zone>();

	public static void add_available_node(Zone neighbor)
	{
		available_nodes.add(neighbor);
	}

	public static void sort_available_nodes()
	{
		for (Zone node : available_nodes)
			node.compute_cost();
		available_nodes.sort(null);
	}

	public static Zone get_best_available_zone()
	{
		for (Zone node : available_nodes)
			if (!node.isComputed())
				return node;

		return null;
	}

	public static Location get_last_waypoint()
	{
		Location last_waypoint = waypoints.get(waypoints.size() - 1);
		return last_waypoint;
	}

	public static void add_waypoint(Location waypoint)
	{
		waypoints.add(waypoint);
	}

	public static ArrayList<Location> getWaypoints()
	{
		return waypoints;
	}

	public static Location getDestination()
	{
		return destination;
	}

	public static void setDestination(Location destination)
	{
		Data.destination = destination;
	}

	public static Location get_start()
	{
		Location last_waypoint = waypoints.get(0);
		return last_waypoint;
	}

	public static BufferedImage getMap()
	{
		return map;
	}

	public static ArrayList<Zone> getPath()
	{
		return path;
	}

	public static void setPath(ArrayList<Zone> path)
	{
		Data.path = path;
	}

	public static void setMap(BufferedImage map)
	{
		Data.map = map;
		Data.setWidth(map.getWidth());
		Data.setHeight(map.getHeight());
	}

	public static int getWidth()
	{
		return width;
	}

	public static void setWidth(int width)
	{
		Data.width = width;
	}

	public static int getHeight()
	{
		return height;
	}

	public static void setHeight(int height)
	{
		Data.height = height;
	}
}
