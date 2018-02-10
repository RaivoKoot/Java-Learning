package data;

public class Parameters
{
	private static int theta_destination = 4;
	private static int theta_start = 0;
	private static int theta_last_waypoint = 0;
	
	private static double threshold_standard_deviation = 4;

	public static int getTheta_destination()
	{
		return theta_destination;
	}

	public static void setTheta_destination(int theta_destination)
	{
		Parameters.theta_destination = theta_destination;
	}

	public static int getTheta_start()
	{
		return theta_start;
	}

	public static void setTheta_start(int theta_start)
	{
		Parameters.theta_start = theta_start;
	}

	public static int getTheta_last_waypoint()
	{
		return theta_last_waypoint;
	}

	public static void setTheta_last_waypoint(int theta_last_waypoint)
	{
		Parameters.theta_last_waypoint = theta_last_waypoint;
	}

	public static double getThreshold_standard_deviation()
	{
		return threshold_standard_deviation;
	}

	public static void setThreshold_standard_deviation(double threshold_standard_deviation)
	{
		Parameters.threshold_standard_deviation = threshold_standard_deviation;
	}

}
