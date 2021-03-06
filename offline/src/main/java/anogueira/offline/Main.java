package anogueira.offline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class to launch the Offline Phone Number Geolocation program
 * 
 * @author Andre Nogueira
 */
public class Main {
	private static boolean optionSameCountry = false;
	private static String targetNumber = null;
	private static List<String> customerNumbers = new ArrayList<String>();

	public static void main(String [] args){

		if(args.length < 2 || !validateArguments(args)) {
			System.out.println("Usage: java Main [--same-country-only] <target number> <customer numbers...>");
			System.exit(1);
		}

		AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

		PhoneNumberGeoMatch numberGeoMatch = context.getBean("phoneNumberGeoMatch",PhoneNumberGeoMatch.class);

		try{
			
			PhoneNumberContext numberContext = numberGeoMatch.retrieveClosest(targetNumber, customerNumbers, optionSameCountry);
			
			System.out.println(formatOutput(numberContext));
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		context.close();
	}

	private static boolean validateArguments(String [] args) {
		int index = 0;

		if(args[index].equalsIgnoreCase("--same-country-only")){
			optionSameCountry = true;
			index++;
		}

		targetNumber = args[index++];

		for( ; index < args.length ; index++) {
			customerNumbers.add(args[index]);
		}

		return customerNumbers.size() > 0; 
	}

	private static String formatOutput(PhoneNumberContext numberContext){
		if(numberContext == null){
			return "no match found";
		}else{
			String phoneNumber =  numberContext.getNumber();
			String region =  numberContext.getLookupInfo().getRegion();
			String location = numberContext.getLookupInfo().getLocation();
			
			if(location == null){
				location = "";
			}
			return String.format("%s ( Region: %s, Location: %s)",phoneNumber,region,location);
		}
	}
}
