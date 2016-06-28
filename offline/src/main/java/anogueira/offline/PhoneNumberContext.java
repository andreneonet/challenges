package anogueira.offline;

import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.lookup.LookupInfo;

/**
 * Represents the information about a phone number and its geographic location
 * 
 * @author Andre Nogueira
 */
public class PhoneNumberContext {
	
	private String number;
	private LookupInfo lookupInfo;
	private Coordinate coordinate;
	
	public PhoneNumberContext(String number, LookupInfo lookupInfo, Coordinate coordinate) {
		this.number = number;
		this.lookupInfo = lookupInfo;
		this.coordinate = coordinate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	public LookupInfo getLookupInfo() {
		return lookupInfo;
	}
	public void setLookupInfo(LookupInfo lookupInfo) {
		this.lookupInfo = lookupInfo;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "PhoneNumberContext [number=" + number + ", lookupInfo=" + lookupInfo + ", coordinate=" + coordinate
				+ "]";
	}
}
