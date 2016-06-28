package anogueira.offline.geolocator.cache.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import anogueira.offline.geolocator.cache.AbstractGeoLocatorCache;
import anogueira.offline.geolocator.cache.PositionInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@DirtiesContext
public class BasicLocatorCacheTest implements ApplicationContextAware{

	private AbstractGeoLocatorCache cacheGeoLocator;
	private PositionInfoDAO positionInfoDAO;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void cleanDatabase(){
		JdbcTestUtils.deleteFromTables(jdbcTemplate,PositionInfo.class.getSimpleName());
	}

	@Test
	public void cacheInsert(){
		String region = "PT";
		String location = "Lisboa";
		
		Assert.assertNull(positionInfoDAO.find(region,location));
		Assert.assertNotNull(cacheGeoLocator.getCoordinate(region, location));
		Assert.assertNotNull(positionInfoDAO.find(region,location));
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.jdbcTemplate =  context.getBean("jdbcTemplate",JdbcTemplate.class);
		this.cacheGeoLocator = context.getBean("cacheGeoLocator",AbstractGeoLocatorCache.class);
		this.positionInfoDAO = context.getBean("positionInfoDAO",PositionInfoDAO.class);
	}

}
