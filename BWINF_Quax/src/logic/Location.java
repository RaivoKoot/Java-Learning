package logic;

public class Location implements Comparable<Location> {

	private int x;
	private int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Location move_one_toward(Location direction) {
		int change_x = (direction.getX() - x);
		int change_y = (direction.getY() - y);

		int new_x = x;
		int new_y = y;

		new_x = increment_number(new_x, change_x);
		new_y = increment_number(new_y, change_y);

		return new Location(new_x, new_y);

	}

	private int increment_number(int initial, int change) {
		if (change > 0)
			initial++;
		else if (change < 0)
			initial--;

		return initial;
	}

	@Override
	public int compareTo(Location arg0) {

		if (x == arg0.getX() && y == arg0.getY())
			return 0;

		return -1;
	}

	public String toString() {
		String s = "(" + x + "|" + y + ")";
		return s;
	}

	public int get_distance_to(Location destination) {
		int delta_x = Math.abs(x - destination.getX());
		int delta_y = Math.abs(y - destination.getY());

		int manhatten_distance = delta_x + delta_y;

		return manhatten_distance;
	}

	public int get_largest_1d_distance_to(Location destination) {
		int delta_x = Math.abs(x - destination.getX());
		int delta_y = Math.abs(y - destination.getY());

		if (delta_x > delta_y)
			return delta_x;

		return delta_y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Location loc = (Location) obj;

		if (compareTo(loc) == 0)
			return true;

		return false;

	}
}
