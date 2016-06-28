package anogueira.offline.impl;

import java.util.List;

import anogueira.offline.PhoneNumberContext;

/**
 * Represents a filter to retrieve a set of customers numbers according a certain criteria
 * 
 * @author Andre Nogueira
 */
interface Criteria {
	public List<PhoneNumberContext> meetCriteria(PhoneNumberContext targetContext, List<PhoneNumberContext> customersContext);
}
