package anogueira.offline.geolocator;

/**
 * Represents a GeoLocatorservice exception
 * 
 * @author Andre Nogueira
 */
public class GeoLocatorServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 30173453337033403L;

	public GeoLocatorServiceException(String string) {
		super(string);
	}
}
