package anogueira.offline.lookup;

/**
 * Represents an invalid phone number exception (e.g., a not viable phone number)
 * 
 * @author Andre Nogueira
 */
public class InvalidPhoneNumberException extends RuntimeException {
	
	private static final long serialVersionUID = 8057849373210993367L;
	
	public InvalidPhoneNumberException(String string) {
		super(string);
	}
}
