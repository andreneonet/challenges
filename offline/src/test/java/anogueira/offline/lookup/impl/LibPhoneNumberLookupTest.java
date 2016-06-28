package anogueira.offline.lookup.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import anogueira.offline.lookup.InvalidPhoneNumberException;
import anogueira.offline.lookup.LookupService;
import anogueira.offline.lookup.impl.LookupServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@DirtiesContext
public class LibPhoneNumberLookupTest{
	
	private LookupService  phoneNumberLookup = new LookupServiceImpl();
	@Test
	public void validPhoneNumbers(){
		Assert.assertNotNull(phoneNumberLookup.lookup("+12018840000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+15148710000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+14159690000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+351265120000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+351211230000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+351222220000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+33975180000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+441732600000"));
		Assert.assertNotNull(phoneNumberLookup.lookup("+14159690000"));
	}
	
	@Test(expected=InvalidPhoneNumberException.class)
	public void invalidPhoneNumbers(){
		Assert.assertNull(phoneNumberLookup.lookup("+9690000"));
		Assert.assertNull(phoneNumberLookup.lookup("+351219087654321"));
		Assert.assertNull(phoneNumberLookup.lookup("+351299999999"));
	}

}
