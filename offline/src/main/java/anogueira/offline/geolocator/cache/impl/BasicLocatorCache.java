package anogueira.offline.geolocator.cache.impl;

import anogueira.offline.geolocator.GeoLocatorService;
import anogueira.offline.geolocator.cache.AbstractGeoLocatorCache;
import anogueira.offline.geolocator.cache.PositionInfo;

/**
 * 
 * @author Andre Nogueira
 */
class BasicLocatorCache extends AbstractGeoLocatorCache{

	private PositionInfoDAO positionInfoDAO;
	
	public BasicLocatorCache(GeoLocatorService geoLocator, PositionInfoDAO positionInfoDAO) {
		super(geoLocator);
		this.positionInfoDAO = positionInfoDAO;
	}

	@Override
	public PositionInfo loadPositionInfo(String region, String location) {
		return positionInfoDAO.find(region, location);
	}

	@Override
	public void storePositionInfo(PositionInfo position) {
		positionInfoDAO.insert(position);
	}

}
