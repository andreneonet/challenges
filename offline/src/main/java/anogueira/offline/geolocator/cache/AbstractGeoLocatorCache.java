package anogueira.offline.geolocator.cache;

import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.geolocator.GeoLocatorService;

/**
 * Generic implementation of a cache system 
 * 
 * @author Andre Nogueira
 */
public abstract class AbstractGeoLocatorCache implements GeoLocatorService {

	protected GeoLocatorService geoLocator;

	public AbstractGeoLocatorCache(GeoLocatorService geoLocator){
		this.geoLocator = geoLocator;
	}

	@Override
	public Coordinate getCoordinate(String region, String location) {
		PositionInfo position = loadPositionInfo(region,location);

		if(position == null){
			try{
				Coordinate coordinate = geoLocator.getCoordinate(region, location);
				
				position = new PositionInfo(region, location,coordinate); 

				if(coordinate !=null){
					storePositionInfo(position);
				}
			}catch(Exception e){
				return null;
			}
		}

		return position.getCoordinate();
	}

	/**
	 * Retrieves a PositionInfo from the cache
	 * 
	 * @param region The name of a large area (e.g., country)
	 * @param location The name of a specific location (e.g., city)
	 * @return the PositionInfo related with the region and location, or null if it does not exist
	 */
	public abstract PositionInfo loadPositionInfo(String region, String location);

	/**
	 * Stores a PositionInfo in the cache
	 *
	 * @param position The PositionInfo
	 */
	public abstract void storePositionInfo(PositionInfo position);

}
