package logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Zone implements Comparable<Zone>
{

	private int terrain; // -1 water, 0 mixed, 1 land
	private Location[] pixels_in_zone;
	private boolean computed;
	private boolean neighbours_sorted;

	private ZoneList neighbors;
	private ZoneList path_belonging_to;
	private int distance_to_destination;
	private int cost;

	private int times_visited;

	private Zone zone_arrived_from;

	public Zone()
	{
		setTimes_visited(0);

		distance_to_destination = -1;

		terrain = -999;
		neighbors = new ZoneList();

		neighbours_sorted = false;
		computed = false;
	}

	// initialize 2x2 from a location
	public Zone(Location initial_pixel, Zone destination)
	{
		setTimes_visited(0);
		terrain = -999;
		neighbors = new ZoneList();

		neighbours_sorted = false;
		computed = false;

		pixels_in_zone = new Location[4];

		pixels_in_zone[0] = initial_pixel;
		int x = initial_pixel.getX();
		int y = initial_pixel.getY();

		// creates 3 pixels
		pixels_in_zone[1] = new Location(x + 1, y + 0);
		pixels_in_zone[2] = new Location(x + 1, y + 1);
		pixels_in_zone[3] = new Location(x + 0, y + 1);

		compute_dist_to(destination);
		compute_cost();

	}

	// creates or finds the 8 surrounding neighbor zones of this one and stores
	// them in 'neighbors'
	public void create_neighbor_zones(Zone[][] zone_storage, Zone destination)
	{
		try
		{
			System.out.println("size " + neighbors.getSize());
			if (neighbors.getSize() != 0)
			{
				System.out.println("RETURN");
				return;
			}
			System.out.println("not return");
			Location center = getLocation();
			int center_x = center.getX();
			int center_y = center.getY();

			// create the 8 neighboring zones
			for (int x = center_x - 1; x < center_x + 2; x++)

				for (int y = center_y - 1; y < center_y + 2; y++)
				{
					if (x == center_x && y == center_y)
						continue;
					System.out.println("iteration");

					Zone neighbor = zone_storage[x][y];

					if (neighbor == null)
					{
						neighbor = new Zone(new Location(x, y), destination);
						zone_storage[x][y] = neighbor;
					} else
						neighbor.compute_cost();

					neighbors.addZone(neighbor);

				}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void compute_dist_to(Zone destination)
	{
		Location dest = destination.getLocation();
		Location here = getLocation();

		distance_to_destination = here.get_distance_to(dest);
	}

	// Computes the cost of the current location
	public void compute_cost()
	{
		Location here = getLocation();

		ArrayList<Location> waypoints = Image_Data_Scanner.getWaypoints();
		Location start = Image_Data_Scanner.getStart();

		Location last_waypoint = waypoints.get(waypoints.size() - 1);

		// alternate largest 1d distance
		// int dist_to_last_waypoint = here.get_distance_to(last_waypoint);
		// int dist_to_start = here.get_largest_1d_distance_to(start);

		// standard distance
		int dist_to_last_waypoint = here.get_distance_to(last_waypoint);
		int dist_to_start = here.get_distance_to(start);

		int theta_one = Image_Data_Scanner.getTheta_one();
		int theta_two = Image_Data_Scanner.getTheta_two();
		int theta_three = Image_Data_Scanner.getTheta_three();

		// theta_two = 0;
		// theta_three = 0;

		cost = (distance_to_destination * theta_one) - (dist_to_start * theta_two)
				- (dist_to_last_waypoint * theta_three);
	}

	public void sort_neighbours()
	{
		neighbors.sort();
	}

	public Zone get_next_best_neighbour()
	{
		Zone next = neighbors.get_next_closest_zone();

		return next;
	}

	public boolean isPassable(BufferedImage map)
	{

		int white = -1;
		int terrain = 4;
		int amt_pixels = pixels_in_zone.length;

		// check terrain of each pixel
		for (int i = 0; i < amt_pixels; i++)
		{
			Location pixel = pixels_in_zone[i];
			// compare the color of the pixel
			if (map.getRGB(pixel.getX(), pixel.getY()) == white)
			{
				// large zone and one pixel is water -> unsure
				if (amt_pixels > 4)
					return false;

				terrain--;
			}
		}

		// all four pixels are water
		if (terrain == 0)
			return false;

		// if the zone is out of bounds of the image
		boolean out_of_bounds = is_out_of_bounds();
		if (out_of_bounds)
			return false;

		return true;
	}

	public int getDistance_to_destination()
	{
		return distance_to_destination;
	}

	public void setDistance_to_destination(int distance_to_destination)
	{
		this.distance_to_destination = distance_to_destination;
	}

	// returns true if this zone contains specified pixel
	public boolean includes_location(Location location)
	{

		System.out.println(pixels_in_zone.length);

		// checks if the pixel location is included in the zones pixels
		for (int i = 0; i < pixels_in_zone.length; i++)
			if (pixels_in_zone[i].compareTo(location) == 0)
				return true;

		return false;
	}

	public boolean add_zone_going_to(Zone zone)
	{
		if (neighbors.contains(zone))
			return false;

		neighbors.addZone(zone);

		return true;
	}

	public int getTerrain()
	{
		return terrain;
	}

	public void setTerrain(int terrain)
	{
		this.terrain = terrain;
	}

	public Location[] getPixels_in_zone()
	{
		return pixels_in_zone;
	}

	public void setPixels_in_zone(Location[] pixels_in_zone)
	{
		this.pixels_in_zone = pixels_in_zone;
	}

	public ZoneList getPath_belonging_to()
	{
		return path_belonging_to;
	}

	public void setPath_belonging_to(ZoneList path_belonging_to)
	{
		this.path_belonging_to = path_belonging_to;
	}

	public ZoneList getZones_going_to()
	{
		return neighbors;
	}

	public void setZones_going_to(ZoneList zones_going_to)
	{
		this.neighbors = zones_going_to;
	}

	public Location getLocation()
	{
		return pixels_in_zone[0];
	}

	@Override
	public int compareTo(Zone o)
	{
		Location pixel1 = pixels_in_zone[0];
		Location pixel2 = o.getPixels_in_zone()[0];

		int comparator = pixel1.compareTo(pixel2);

		if (comparator != 0)
			comparator = Integer.compare(cost, o.getCost());

		return comparator;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(int cost)
	{
		this.cost = cost;
	}

	public boolean isComputed()
	{
		return computed;
	}

	public void setComputed(boolean computed)
	{
		this.computed = computed;
	}

	public boolean isNeighbours_sorted()
	{
		return neighbours_sorted;
	}

	public void setNeighbours_sorted(boolean neighbours_sorted)
	{
		this.neighbours_sorted = neighbours_sorted;
	}

	public String toString()
	{
		String s = getLocation().toString();

		return s;
	}

	public Zone getZone_arrived_from()
	{
		return zone_arrived_from;
	}

	public void setZone_arrived_from(Zone zone_arrived_from)
	{
		this.zone_arrived_from = zone_arrived_from;
	}

	public boolean is_out_of_bounds()
	{
		int x = getLocation().getX();
		int y = getLocation().getY();

		if (x >= Image_Data_Scanner.getWidth() - 2 || x == 0)
			return true;

		if (y >= Image_Data_Scanner.getHeight() - 2 || y == 0)
			return true;

		return false;
	}

	public int getTimes_visited()
	{
		return times_visited;
	}

	public void setTimes_visited(int times_visited)
	{
		this.times_visited = times_visited;
	}

}
