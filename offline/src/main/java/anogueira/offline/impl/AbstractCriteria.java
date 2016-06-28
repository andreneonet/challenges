package anogueira.offline.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import anogueira.offline.PhoneNumberContext;

/**
 * A generic implementation of a criteria
 * 
 * @author Andre Nogueira
 */
abstract class AbstractCriteria implements Criteria {
	@Override
	public List<PhoneNumberContext> meetCriteria(PhoneNumberContext targetContext, List<PhoneNumberContext> customersContext) {
		return customersContext
				.stream()
				.filter(getFilter(targetContext))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the specific criteria to use
	 * 
	 * @param targetContext The phone number context of the target number
	 * @return A filter to be used as criteria
	 */
	protected abstract Predicate<? super PhoneNumberContext> getFilter(PhoneNumberContext targetContext);
	
}
