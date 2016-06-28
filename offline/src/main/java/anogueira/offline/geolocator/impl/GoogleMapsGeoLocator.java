package anogueira.offline.geolocator.impl;

import org.apache.commons.lang3.Validate;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.geolocator.GeoLocatorService;
import anogueira.offline.geolocator.GeoLocatorServiceException;

/**
 * An implementation of the GeoLocatorService using the Java Client for Google Maps Services
 * (https://github.com/googlemaps/google-maps-services-java/)
 * 
 * @author Andre Nogueira
 */
class GoogleMapsGeoLocator implements GeoLocatorService {

	private GeoApiContext geoApiContext; 

	public GoogleMapsGeoLocator(String apiKey, int maxQps){
		this.geoApiContext = new GeoApiContext();
		this.geoApiContext.setApiKey(apiKey);
		this.geoApiContext.setQueryRateLimit(maxQps);
	}
	
	public Coordinate getCoordinate(String region, String location) {
		Validate.notNull(region,"region can not be null.");
		Validate.notNull(location,"location can not be null.");
		
		String address = String.format("%s %s",region,location);
		
		Coordinate coordinate = null;
		try {
			
			GeocodingResult[] results = GeocodingApi.geocode(geoApiContext,address).await();
			if(results.length > 0){
				coordinate = new Coordinate(
						results[0].geometry.location.lat,
						results[0].geometry.location.lng);
			}
		} catch (Exception e) {
			 throw new GeoLocatorServiceException(e.getMessage());
		}

		return coordinate;		
	}

}
