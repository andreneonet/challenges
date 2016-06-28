package anogueira.offline.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import anogueira.offline.geolocator.Coordinate;
import anogueira.offline.util.GeographicUtil.ConvertFactorUnit;

/**
 * @author Andre Nogueira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@DirtiesContext
public class GeographicUtilTest{
	
	private final Coordinate LISBOA_PT = new Coordinate(38.7222524,-9.139336599999979);
	private final Coordinate LONDON_GB = new Coordinate(51.5073509,-0.12775829999998223);
	private final Coordinate CALIFORNIA_US = new Coordinate(36.778261,-119.41793239999998);
	private final Coordinate NEW_YORK_US = new Coordinate(40.7127837,-74.00594130000002);
	private final Coordinate MIAMI_US = new Coordinate(25.7616798,-80.19179020000001);
	private final Coordinate AUSTIN_US = new Coordinate(30.267153,-97.74306079999997);
	
	
	@Test
	public void correctDistancesKilometers(){
		Assert.assertTrue(GeographicUtil.distance(LISBOA_PT,LONDON_GB)<GeographicUtil.distance(LISBOA_PT,NEW_YORK_US));
		Assert.assertTrue(GeographicUtil.distance(CALIFORNIA_US,NEW_YORK_US)<GeographicUtil.distance(CALIFORNIA_US,LISBOA_PT));
		Assert.assertTrue(GeographicUtil.distance(MIAMI_US,NEW_YORK_US)<GeographicUtil.distance(AUSTIN_US,NEW_YORK_US));
	}
	
		
	@Test
	public void correctDistancesMiles(){
		Assert.assertTrue(GeographicUtil.distance(LISBOA_PT, LONDON_GB, ConvertFactorUnit.MILES) < GeographicUtil.distance(LISBOA_PT,NEW_YORK_US, ConvertFactorUnit.MILES));
		Assert.assertTrue(GeographicUtil.distance(CALIFORNIA_US, NEW_YORK_US, ConvertFactorUnit.MILES) < GeographicUtil.distance(CALIFORNIA_US,LISBOA_PT, ConvertFactorUnit.MILES));
		Assert.assertTrue(GeographicUtil.distance(MIAMI_US, NEW_YORK_US, ConvertFactorUnit.MILES) < GeographicUtil.distance(AUSTIN_US,NEW_YORK_US, ConvertFactorUnit.MILES));
	}

}
