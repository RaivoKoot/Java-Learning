package logic;

import java.util.ArrayList;

public class ZoneList
{

	private ArrayList<Zone> zones;
	private boolean sorted;

	public Zone get_next_closest_zone()
	{
		for (Zone zone : zones)
			if (!zone.isComputed())
				return zone;

		return null;

	}

	public ZoneList()
	{
		zones = new ArrayList<Zone>();
		setSorted(false);
	}

	public void addZone(Zone zone)
	{
		zones.add(zone);
	}

	public int getSize()
	{
		return zones.size();
	}

	public Zone get(int index)
	{

		return zones.get(index);
	}

	public int getLength()
	{
		return zones.size();
	}

	public boolean contains(Zone zone)
	{
		return zones.contains(zone);
	}

	public ArrayList<Zone> getZones()
	{
		return zones;
	}

	public void addAll(ZoneList zones)
	{
		this.zones.addAll(zones.getZones());
	}

	public void sort()
	{
		// skip sorting if already done
		if (sorted)
			return;

		zones.sort(null);
		this.setSorted(true);
	}

	public ZoneList clone()
	{
		ZoneList clone = new ZoneList();

		clone.addAll(this);

		return clone;
	}

	public boolean isSorted()
	{
		return sorted;
	}

	public void setSorted(boolean sorted)
	{
		this.sorted = sorted;
	}
}
