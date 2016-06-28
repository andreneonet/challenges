package anogueira.offline.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import anogueira.offline.PhoneNumberGeoMatch;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@DirtiesContext
public class PhoneNumberGeoMatchImplTest implements ApplicationContextAware{

	private final String NEW_JERSEY_US = "+12018840000";
	private final String MONTREAL_CA   = "+15148710000";
	private final String CALIFORNIA_US = "+14159690000";
	private final String SETUBAL_PT = "+351265120000";
	private final String LISBOA_PT  = "+351211230000";
	private final String PORTO_PT 	 = "+351222220000";
	private final String FRANCE_FR = "+33975180000";
	private final String SEVENOAKS_GB = "+441732600000";
	private final String ALGARVE_PT = "+351282340800";
	private final String BRAGANCA_PT = "+351273304200";
	private final String SEVILHA_ES = "+34955110898";
	private final String LEIRIA_PT = "+351244839500";
	private final String VILA_FRANCA_XIRA_PT = "+351263285600";
	private final String TOLL_FREE_GB = "+448008080000";
	private final String TOLL_FREE_US = "+18009970000";
	private final String TOLL_FREE_ES = "+34900800800";
	private final String INVALID_NUMBER = "098769";
	private final String SYDNEY_AU = "+61292506123";
	private final String TOKYO_JP = "+81332708188";
	private final String SEOUL_KR = "+8227961660";
	
	
	private PhoneNumberGeoMatch phoneNumberGeoMatch;

	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.phoneNumberGeoMatch = context.getBean("phoneNumberGeoMatch",PhoneNumberGeoMatch.class);
	}

	/**
	 * 
	 */
	
	@Test
	public void closestLocationTest1(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(MONTREAL_CA);
		customerNumbers.add(CALIFORNIA_US);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(NEW_JERSEY_US,customerNumbers,false).getNumber().equalsIgnoreCase(MONTREAL_CA));
	}

	@Test
	public void closestLocationTest2(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(LISBOA_PT);
		customerNumbers.add(PORTO_PT);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(SETUBAL_PT,customerNumbers,false).getNumber().equalsIgnoreCase(LISBOA_PT));
	}

	@Test
	public void closestLocationTest3(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(SEVENOAKS_GB);
		customerNumbers.add(CALIFORNIA_US);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(FRANCE_FR,customerNumbers,false).getNumber().equalsIgnoreCase(SEVENOAKS_GB));
	}

	@Test
	public void closestLocationTest4(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(BRAGANCA_PT);
		customerNumbers.add(SEVILHA_ES);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(ALGARVE_PT,customerNumbers,false).getNumber().equalsIgnoreCase(SEVILHA_ES));
	}
	
	@Test
	public void closestLocationTest5(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(TOKYO_JP);
		customerNumbers.add(SYDNEY_AU);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(SEOUL_KR,customerNumbers,false).getNumber().equalsIgnoreCase(TOKYO_JP));
	}


	/**
	 * 
	 */
	
	@Test
	public void closestLocationSameContryTest1(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(MONTREAL_CA);
		customerNumbers.add(CALIFORNIA_US);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(NEW_JERSEY_US,customerNumbers,true).getNumber().equalsIgnoreCase(CALIFORNIA_US));
	}

	@Test
	public void closestLocationSameContryTest2(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(SEVENOAKS_GB);
		customerNumbers.add(CALIFORNIA_US);

		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(FRANCE_FR,customerNumbers,true));
	}

	@Test
	public void closestLocationSameContryTest3(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(BRAGANCA_PT);
		customerNumbers.add(SEVILHA_ES);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(ALGARVE_PT,customerNumbers,true).getNumber().equalsIgnoreCase(BRAGANCA_PT));
	}

	@Test
	public void closestLocationSameContryTest4(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(BRAGANCA_PT);
		customerNumbers.add(ALGARVE_PT);
		customerNumbers.add(LEIRIA_PT);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,customerNumbers,true).getNumber().equalsIgnoreCase(LEIRIA_PT));
	}

	@Test
	public void closestLocationSameContryTest5(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(BRAGANCA_PT);
		customerNumbers.add(ALGARVE_PT);
		customerNumbers.add(LEIRIA_PT);
		customerNumbers.add(VILA_FRANCA_XIRA_PT);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,customerNumbers,true).getNumber().equalsIgnoreCase(VILA_FRANCA_XIRA_PT));
	}

	@Test
	public void closestWithoutLocation(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(TOLL_FREE_US);
		customerNumbers.add(TOLL_FREE_GB);
		
		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,customerNumbers,false).getNumber().equalsIgnoreCase(TOLL_FREE_GB));
	}
	
	@Test
	public void closestWithoutLocation2(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(TOLL_FREE_US);
		customerNumbers.add(TOLL_FREE_GB);
		customerNumbers.add(TOLL_FREE_ES);
		
		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,customerNumbers,false).getNumber().equalsIgnoreCase(TOLL_FREE_ES));
	}


	
	
	@Test
	public void closestWithoutLocationSameCountry(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(TOLL_FREE_US);
		customerNumbers.add(TOLL_FREE_GB);
		
		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,customerNumbers,true));
	}

	/**
	 * 
	 */
	
	@Test(expected= RuntimeException.class)
	public void nullTargetNumber(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(LISBOA_PT);

		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(null,customerNumbers,false));
	}

	@Test(expected= RuntimeException.class)
	public void nullTargetNumberSameCountry(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(LISBOA_PT);

		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(null,customerNumbers,true));
	}

	@Test(expected= RuntimeException.class)
	public void nullCustomerNumbers(){
		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,null,false));
	}

	@Test(expected= RuntimeException.class)
	public void nullCustomerNumbersSameCountry(){
		Assert.assertNull(phoneNumberGeoMatch.retrieveClosest(LISBOA_PT,null,true));
	}
	
	/**
	 * 
	 */
	
	@Test(expected=RuntimeException.class)
	public void invalidNumber(){
		List<String> customerNumbers = new ArrayList<>();
		customerNumbers.add(MONTREAL_CA);
		customerNumbers.add(CALIFORNIA_US);
		customerNumbers.add(INVALID_NUMBER);

		Assert.assertTrue(phoneNumberGeoMatch.retrieveClosest(NEW_JERSEY_US,customerNumbers,false).getNumber().equalsIgnoreCase(MONTREAL_CA));
	}
}
