package anogueira.offline.geolocator;

/**
 * A service that provides geocoding
 *
 * @author Andre Nogueira
 */
public interface GeoLocatorService {
	
	
	/**
	 * Retrieves a geographic coordinate 
	 * 
	 * @param region The name of a large area (e.g., country)
	 * @param location The name of a specific location (e.g., city)
	 * @return Geographic coordinate related with the region and the location, or null if there is not a match
	 */
	public abstract Coordinate getCoordinate(String region, String location);
	
}

