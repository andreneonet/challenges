package anogueira.offline.geolocator.cache;

import anogueira.offline.geolocator.Coordinate;

/**
 * Represents the geographic information stored in the cache
 * 
 * @author Andre Nogueira
 */
public class PositionInfo {
	private String region;
	private String location;
	private Coordinate coordinate;
	
	public PositionInfo(String region, String location, Coordinate coordinate) {
		this.region = region;
		this.location = location;
		this.coordinate = coordinate;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public String toString() {
		return "PositionInfo [region=" + region + ", location=" + location + ", coordinate=" + coordinate + "]";
	}
}
