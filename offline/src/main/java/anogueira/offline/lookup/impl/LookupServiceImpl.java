package anogueira.offline.lookup.impl;

import java.util.Locale;

import org.apache.commons.lang3.Validate;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import anogueira.offline.lookup.InvalidPhoneNumberException;
import anogueira.offline.lookup.LookupInfo;
import anogueira.offline.lookup.LookupService;

/**
 * An implementation of the LookupService interface using the libphonenumber library
 * (https://github.com/googlei18n/libphonenumber)
 * 
 * @author Andre Nogueira
 */
class LookupServiceImpl implements LookupService {

	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	private PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

	public LookupInfo lookup(String number){
		Validate.notEmpty(number,"number has no content.");

		PhoneNumber phoneNumber = null;
		try{
			phoneNumber = phoneUtil.parse(number, "");
		}catch (NumberParseException e) {
			throw new InvalidPhoneNumberException("["+number+"] invalid phone number");
		}

		if(!phoneUtil.isPossibleNumber(phoneNumber) || !phoneUtil.isValidNumber(phoneNumber) ){
			throw new InvalidPhoneNumberException("["+number+"] invalid phone number");
		}

		String country = phoneUtil.getRegionCodeForNumber(phoneNumber);
		String location= geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH);

		return new LookupInfo(country,location);
	}
}


