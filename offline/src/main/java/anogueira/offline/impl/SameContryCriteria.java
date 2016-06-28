package anogueira.offline.impl;

import java.util.function.Predicate;

import anogueira.offline.PhoneNumberContext;
import anogueira.offline.lookup.LookupInfo;

/**
 * This class implements the same country criteria
 * 
 * @author Andre Nogueira
 */
class SameContryCriteria extends AbstractCriteria{

	@Override
	protected Predicate<? super PhoneNumberContext> getFilter(PhoneNumberContext targetContext) {
		return customerContext -> getRegion(customerContext).equalsIgnoreCase(getRegion(targetContext));
	}

	private String getRegion(PhoneNumberContext numberContext){
		return getLookupInfo(numberContext).getRegion();
	}

	private LookupInfo getLookupInfo(PhoneNumberContext numberContext){
		return numberContext.getLookupInfo();
	}

}
