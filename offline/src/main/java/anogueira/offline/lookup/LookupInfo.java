package anogueira.offline.lookup;

/**
 * Represents the information about a phone number
 *
 * @author Andre Nogueira
 */
public class LookupInfo {

	private String region;
	private String location;

	public LookupInfo(String country, String location) {
		this.region = country;
		this.location = location;
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
	
	@Override
	public String toString() {
		return "LookupInfo [region=" + region + ", location=" + location + "]";
	}

}
