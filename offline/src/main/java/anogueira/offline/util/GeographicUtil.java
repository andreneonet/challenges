package anogueira.offline.util;

import anogueira.offline.geolocator.Coordinate;

/**
 * A utility class to calculate the distance between two geographic coordinates. 
 * This class make use of code available in https://www.geodatasource.com/developers/java
 *
 * @author Andre Nogueira
 */
public final class GeographicUtil {

	/**
	 * Represents length units 
	 * @author Andre Nogueira
	 */
	public enum LengthUnit {
		KILOMETERS  (1.609344),
		MILES(0.8684);

		private final double  measure;

		LengthUnit(double measure) {
			this.measure = measure;
		}

		public double getMeasure() {
			return this.measure;
		}
	}


	private GeographicUtil(){}

	/**
	 * This routine calculates the distance (in Kilometers) between two geographic coordinates 
	 *
	 * @param coord1 Coordinate 1
	 * @param coord2 Coordinate 2
	 * @return the distance in kilometers between two coordinates
	 */
	public static double distance(Coordinate coord1, Coordinate coord2){
		return distance(coord1,coord2,LengthUnit.KILOMETERS);
	}

	/**
	 * This routine calculates the distance between two geographic coordinates based on a unit of length
	 * 
	 * @param coord1 Coordinate 1
	 * @param coord2 Coordinate 2
	 * @param unit The unit of length
	 * @return the distance between two coordinates according to the unit of length
	 */
	public static double distance(Coordinate coord1, Coordinate coord2, LengthUnit unit){
		double theta = coord1.getLongitude() - coord2.getLongitude();
		double dist = Math.sin(deg2rad(coord1.getLatitude())) * Math.sin(deg2rad(coord2.getLatitude())) + Math.cos(deg2rad(coord1.getLatitude())) * Math.cos(deg2rad(coord2.getLatitude())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * unit.getMeasure();

		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
