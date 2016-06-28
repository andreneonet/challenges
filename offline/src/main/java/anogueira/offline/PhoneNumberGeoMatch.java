package anogueira.offline;

import java.util.List;

/**
 * Represent a service that uses geographic information to select phone numbers.
 * 
 * @author Andre Nogueira
 */
public interface PhoneNumberGeoMatch {
	
	/**
	 * 
	 * @param targetNumber A phone number to call 
	 * @param customerNumbers A list of customer's numbers 
	 * @param sameCountry Only select matches that belong to the same country
	 * @return Customer's number information that is closet (geographic) to the phone number to call, or null if there is not a match.
	 * @throws InvalidPhoneNumberException if any phone number is invalid 
	 */
	public abstract PhoneNumberContext retrieveClosest(String targetNumber, List<String> customerNumbers, boolean sameCountry);
}
