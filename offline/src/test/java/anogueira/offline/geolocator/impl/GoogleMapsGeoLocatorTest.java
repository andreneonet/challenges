package anogueira.offline.geolocator.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import anogueira.offline.geolocator.GeoLocatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@DirtiesContext
public class GoogleMapsGeoLocatorTest implements ApplicationContextAware{

	private GeoLocatorService geoLocator;

	@Test
	public void emptyRegion(){
		Assert.assertNotNull(geoLocator.getCoordinate("","lisboa"));
	}
	
	@Test
	public void emptyLocation(){
		Assert.assertNotNull(geoLocator.getCoordinate("pt",""));
	}
	
	@Test
	public void invalidLocations(){
		Assert.assertNull(geoLocator.getCoordinate("","xpto"));
		Assert.assertNull(geoLocator.getCoordinate("",""));
		Assert.assertNull(geoLocator.getCoordinate(" "," "));
	}
	
	@Test
	public void validLocations(){
		Assert.assertNotNull(geoLocator.getCoordinate("pt","lisboa"));
		Assert.assertNotNull(geoLocator.getCoordinate("fr","paris"));
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.geoLocator = context.getBean("geoLocator",GeoLocatorService.class);
	}

}
