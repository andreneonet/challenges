package anogueira.offline.lookup;

/**
 * Represents a Lookup service for phone numbers
 * 
 * @author Andre Nogueira
 */
public interface LookupService {

	/**
	 * 
	 * @param phoneNumber a phone number to lookup
	 * @return information about the phone number
	 * @throws InvalidPhoneNumberException if phoneNumber is invalid 
	 */
	public abstract LookupInfo lookup(String phoneNumber);
}


