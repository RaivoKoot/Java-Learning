package logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.Data;
import data.Parameters;

public class Zone implements Comparable<Zone>
{

	private int terrain; // -1 water, 0 mixed, 1 land
	private ArrayList<Location> pixels_in_zone;
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
	public Zone(Location initial_pixel, Zone destination, boolean large)
	{
		setTimes_visited(0);
		terrain = -999;
		neighbors = new ZoneList();

		neighbours_sorted = false;
		computed = false;

		pixels_in_zone = new ArrayList<Location>();

		if (!large)
			add_pixels(initial_pixel);

		int dist_dest = compute_dist_to(destination);
		setDistance_to_destination(dist_dest);
		compute_cost();

	}
	
	/*
	 * make long zone creation possible through these two methods
	 */
	
	// gives this zone a 10*2 length by concatenating ten 2*2 pixel squares
	public void make_long_zone(Location initial_pixel)
	{
		Location pixel = initial_pixel;
		for (int i = 0; i < 10; i++)
		{
			add_pixels(pixel);
			pixel = get_next_closest_pixel_to_destination(pixel);
		}

	}

	private Location get_next_closest_pixel_to_destination(Location start)
	{
		Location destination = Data.getDestination();
		int dest_x = destination.getX();
		int dest_y = destination.getY();

		int new_x = start.getX();
		int new_y = start.getY();

		if (dest_x > new_x)
			new_x++;
		else if (dest_x < new_x)
			new_x--;

		if (dest_y > new_y)
			new_y++;
		else if (dest_y < new_y)
			new_y--;

		Location next_closest_location_to_destination = new Location(new_x, new_y);

		return next_closest_location_to_destination;
	}

	// adds the pixel and the three surrounding pixels to the zones list
	public void add_pixels(Location initial_pixel)
	{

		add_pixel(initial_pixel);
		int x = initial_pixel.getX();
		int y = initial_pixel.getY();

		// creates 3 pixels
		Location surrounding_pix_1 = new Location(x + 1, y + 0);
		Location surrounding_pix_2 = new Location(x + 1, y + 1);
		Location surrounding_pix_3 = new Location(x + 0, y + 1);

		add_pixel(surrounding_pix_1);
		add_pixel(surrounding_pix_2);
		add_pixel(surrounding_pix_3);
	}

	// adds a single pixel to the zones list if it does not already contain it
	public boolean add_pixel(Location pixel)
	{
		if (pixels_in_zone.contains(pixel))
			return false;

		pixels_in_zone.add(pixel);
		return true;
	}

	// creates or finds the 8 surrounding neighbor zones of this one and stores
	// them in 'neighbors'
	public void create_neighbor_zones(Zone[][] zone_storage, Zone destination)
	{
		try
		{
			if (neighbors.getSize() != 0)
				return;

			Location center = getLocation();
			int center_x = center.getX();
			int center_y = center.getY();

			// create the 8 neighboring zones
			for (int x = center_x - 1; x < center_x + 2; x++)

				for (int y = center_y - 1; y < center_y + 2; y++)
				{
					if (x == center_x && y == center_y)
						continue;

					Zone neighbor = zone_storage[x][y];

					if (neighbor == null)
					{
						neighbor = new Zone(new Location(x, y), destination, false);
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

	public int compute_dist_to(Zone destination)
	{
		Location dest = destination.getLocation();
		Location here = getLocation();

		return here.get_distance_to(dest);
	}

	// Computes the cost of the current location
	public void compute_cost()
	{
		Location here = getLocation();

		Location start = Data.get_start();

		Location last_waypoint = Data.get_last_waypoint();

		// alternate largest 1d distance
		// int dist_to_last_waypoint = here.get_distance_to(last_waypoint);
		// int dist_to_start = here.get_largest_1d_distance_to(start);

		// standard distance
		int dist_to_last_waypoint = here.get_distance_to(last_waypoint);
		int dist_to_start = here.get_distance_to(start);
		int sum_waypoint_distances = 0;

		ArrayList<Location> waypoints = Data.getWaypoints();
		for (int i = 1; i < waypoints.size(); i++)
			sum_waypoint_distances += here.get_distance_to(waypoints.get(i));

		if (dist_to_last_waypoint > 40 && Data.getWaypoints().size() > 1)
		{
			Parameters.setTheta_last_waypoint(0);
			Parameters.setTheta_start(0);
		}

		int weight_destination = Parameters.getTheta_destination();
		int weight_start = Parameters.getTheta_start();
		int weight_waypoint = Parameters.getTheta_last_waypoint();

		// cost function
		cost = (distance_to_destination * weight_destination) - (dist_to_start * weight_start)
				- (/* dist_to_last_waypoint */ sum_waypoint_distances * weight_waypoint);
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
		int amt_pixels = pixels_in_zone.size();

		// check terrain of each pixel
		for (Location pixel : pixels_in_zone)
		{
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

		System.out.println(pixels_in_zone.size());

		// checks if the pixel location is included in the zones pixels
		return pixels_in_zone.contains(location);
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

	public ArrayList<Location> getPixels_in_zone()
	{
		return pixels_in_zone;
	}

	public void setPixels_in_zone(ArrayList<Location> pixels_in_zone)
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
		int length = pixels_in_zone.size();

		if (length == 4)
			return pixels_in_zone.get(0);

		return pixels_in_zone.get(length - 1);
	}

	@Override
	public int compareTo(Zone o)
	{
		Location pixel1 = getLocation();
		Location pixel2 = o.getLocation();

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

		if (x >= Data.getWidth() - 2 || x == 0)
			return true;

		if (y >= Data.getHeight() - 2 || y == 0)
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
