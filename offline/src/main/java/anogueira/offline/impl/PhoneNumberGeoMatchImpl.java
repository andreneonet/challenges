package anogueira.offline.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.Validate;

import anogueira.offline.PhoneNumberContext;
import anogueira.offline.PhoneNumberGeoMatch;
import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.geolocator.GeoLocatorService;
import anogueira.offline.lookup.LookupInfo;
import anogueira.offline.lookup.LookupService;
import anogueira.offline.util.GeographicUtil;

/**
 * An implementation of the PhoneNumberGeoMath
 * 
 * @author Andre Nogueira
 */
class PhoneNumberGeoMatchImpl implements PhoneNumberGeoMatch{

	private GeoLocatorService geoLocator;
	private LookupService phoneNumberLookup;

	public PhoneNumberGeoMatchImpl(GeoLocatorService geoLocator, LookupService phoneNumberLookup){
		this.geoLocator = geoLocator;
		this.phoneNumberLookup = phoneNumberLookup;
	}


	@Override
	public PhoneNumberContext retrieveClosest(String targetNumber, List<String> customerNumbers, boolean sameCountry) {
		Validate.notBlank(targetNumber,"targetNumber has no content.");
		Validate.notEmpty(customerNumbers,"customerNumbers has no content.");

		PhoneNumberContext targetContext = createPhoneNumberContext(targetNumber);

		List<PhoneNumberContext> customersContexts = new LinkedList<PhoneNumberContext>();
		for(String customerNumber : customerNumbers){
			customersContexts.add(createPhoneNumberContext(customerNumber));
		}

		if(sameCountry){
			customersContexts = new SameContryCriteria().meetCriteria(targetContext,customersContexts);
		}

		return selectClosestCustomerNumber(targetContext,customersContexts);
	}


	private PhoneNumberContext createPhoneNumberContext(String number){
		LookupInfo lookupInfo = phoneNumberLookup.lookup(number); 
		Coordinate coordinate = geoLocator.getCoordinate(lookupInfo.getRegion(),lookupInfo.getLocation());

		return new PhoneNumberContext(number,lookupInfo,coordinate);
	}


	private PhoneNumberContext selectClosestCustomerNumber(PhoneNumberContext targetContext, List<PhoneNumberContext> customersContext) {
		if(customersContext == null || customersContext.isEmpty()){
			return null;
		} else if(customersContext.size() == 1){
			return customersContext.get(0);
		} else {
			return customersContext
					.stream()
					.min((customerContext1, customerContext2) -> Double.compare(
							GeographicUtil.distance(targetContext.getCoordinate(), customerContext1.getCoordinate()),
							GeographicUtil.distance(targetContext.getCoordinate(), customerContext2.getCoordinate())))
					.get();
		}
	}

}
