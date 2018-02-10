package logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import data.Data;
import data.Parameters;

public class Pathfinding_Algorithm
{
	public static ArrayList<Zone> history;

	// finds a path between start
	public static ArrayList<Zone> find_path(Zone start, Zone destination, BufferedImage map)
	{
		ArrayList<Zone> path_history = new ArrayList<Zone>();
		Zone[][] zones_visited = new Zone[map.getWidth()][map.getHeight()];

		Zone current_zone = start;
		path_history.add(current_zone);

		// while not arrived at destination or limit not met
		for (int counter = 0; current_zone.getDistance_to_destination() != 0 && counter < 5000; counter++)
		{

			// mark as visited
			current_zone.setComputed(true);

			/* zone is not passable */
			if (!current_zone.isPassable(map))
			{ // go back a zone
				current_zone = current_zone.getZone_arrived_from();
				continue;
			}

			// every ten zones check if the path is stuck
			if (counter % 10 == 0 && path_stuck(path_history))
				// if so create a waypoint
				create_stuck_waypoint(path_history);

			// create neighbors
			current_zone.create_neighbor_zones(zones_visited, destination);
			current_zone.sort_neighbours();

			// chose best neighbor
			Zone next_zone = current_zone.get_next_best_neighbour();

			/* all neighbors have been visited already */
			if (null == next_zone)
			{ // go back a zone
				current_zone = current_zone.getZone_arrived_from();
				continue;
			}

			// set which zone we moved from to the current zone
			next_zone.setZone_arrived_from(current_zone);
			current_zone = next_zone;
			// add new zone to path history
			path_history.add(next_zone);

		}

		return path_history;

	}

	private static void create_stuck_waypoint(ArrayList<Zone> path_history)
	{
		System.out.println("STUCK");
		
		Parameters.setTheta_last_waypoint(1);
		Parameters.setTheta_start(1);
		
		int length = path_history.size();
		Zone waypoint = path_history.get(length - 10);

		Data.add_waypoint(waypoint.getLocation());
	}

	private static boolean path_stuck(ArrayList<Zone> path)
	{
		int length = path.size();

		// if path is too small
		if (length < 10)
			return false;

		// gets last 10 zones
		ArrayList<Zone> recent_path = new ArrayList<Zone>(path.subList(length - 10, length));

		double standard_deviation = calculate_standard_deviation(recent_path);

		double threshold = Parameters.getThreshold_standard_deviation();

		// if sd smaller than arbitrary threshold then path is stuck
		if (standard_deviation < threshold)
			return true;

		return false;
	}

	// change to private
	private static double calculate_standard_deviation(ArrayList<Zone> locations)
	{
		// instead of finding difference of average this takes the first location
		Zone mean = locations.get(0);
		locations.remove(0);

		double sum_deviation = 0;
		for (Zone x : locations)
		{
			int deviation = x.compute_dist_to(mean);
			sum_deviation += deviation;
		}

		double standard_deviation = sum_deviation / locations.size();

		return standard_deviation;

	}

	// recursive version. causes a StackOverflowException when path gets too long

	/*
	 * public static ArrayList<Zone> take_step(ArrayList<Zone> path_history,
	 * Zone[][] zones_visited, Zone current_zone, Zone destination, BufferedImage
	 * map, int counter, int waypoint_cooldown, int parameter_cooldown) {
	 * 
	 * current_zone.setTimes_visited(current_zone.getTimes_visited() + 1);
	 * 
	 * System.out.println(); waypoint_cooldown--; parameter_cooldown--;
	 * System.out.println("iteration: " + counter++);
	 * System.out.println(current_zone.toString());
	 * 
	 * // ending condition if (current_zone.getDistance_to_destination() == 0 ||
	 * path_history.size() > 2000 || counter > 2000) {
	 * System.out.println("ARRIVED AT DESTINATION OR LIMIT");
	 * Pathfinding_Algorithm.setHistory(path_history); return path_history; }
	 * 
	 * // if zone is not passable go back one zone if
	 * (!current_zone.isPassable(map)) { // create a waypoint that we get points for
	 * moving away from if (waypoint_cooldown <= 0) { waypoint_cooldown = 400;
	 * Image_Data_Scanner.add_waypoint(current_zone.getLocation()); }
	 * 
	 * // change parameters to temporarily move around an obstacle
	 * Image_Data_Scanner.setTheta_one(4); Image_Data_Scanner.setTheta_two(1);
	 * Image_Data_Scanner.setTheta_three(1); parameter_cooldown = 15;
	 * 
	 * System.out.println("IMPASSABLE ZONE"); Zone last_zone =
	 * current_zone.getZone_arrived_from(); return take_step(path_history,
	 * zones_visited, last_zone, destination, map, counter, waypoint_cooldown,
	 * parameter_cooldown); }
	 * 
	 * System.out.println("3 creating neighbors"); // create or set the neighbours
	 * of the current zone try { current_zone.create_neighbor_zones(zones_visited,
	 * destination); current_zone.sort_neighbours(); } catch (Error e) {
	 * e.printStackTrace(); }
	 * 
	 * System.out.println("4 chosing closest neighbor"); // chose the next closest
	 * neighbor to destination to move to
	 * 
	 * Zone next_zone = current_zone.get_next_best_neighbour();
	 * 
	 * // if all neighbors have been visited go back one zone if (null == next_zone)
	 * { System.out.println("NO NEIGHBORS LEFT. STEP BACK"); Zone last_zone =
	 * current_zone.getZone_arrived_from(); return take_step(path_history,
	 * zones_visited, last_zone, destination, map, counter, waypoint_cooldown,
	 * parameter_cooldown); }
	 * 
	 * // set which zone we moved from to the current zone
	 * next_zone.setZone_arrived_from(current_zone);
	 * 
	 * System.out.println("5 add zone to history");
	 * 
	 * // mark the new zone as visited next_zone.setComputed(true); // add new zone
	 * to the path history path_history.add(next_zone);
	 * 
	 * // reset parameters to make moving toward destination priority again if
	 * (parameter_cooldown == 0) { Image_Data_Scanner.setTheta_one(4);
	 * Image_Data_Scanner.setTheta_two(1); Image_Data_Scanner.setTheta_three(1); }
	 * 
	 * System.out.println("6 take new step"); // explore from the new zone return
	 * take_step(path_history, zones_visited, next_zone, destination, map, counter,
	 * waypoint_cooldown, parameter_cooldown);
	 * 
	 * }
	 * 
	 */

	public static ArrayList<Zone> getHistory()
	{
		return history;
	}

	public static void setHistory(ArrayList<Zone> history)
	{
		Pathfinding_Algorithm.history = history;
	}
}