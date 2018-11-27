package com.techelevator;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.npgeek.model.JDBCParkDAO;
import com.techelevator.npgeek.model.Park;

public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCParkDAO jdbcParkDao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		
		jdbcParkDao = new JDBCParkDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, "
				+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
				+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, "
				+ "numberofanimalspecies) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, "BNP123", "Buckeye National Park", "Ohio", 55000, 400, 250.5, 340, "Woodland", 1955,
				300000, "The heart of it all!", "Joe Buckeye",
				"Lush woodlands with " + "beautiful foliage. Beautiful caves and waterfalls.", 20, 400);
	}

	@Test
	public void get_all_parks_returns_at_least_one_park() {

		List<Park> listTest = jdbcParkDao.getAllParks();

		Assert.assertNotEquals(0, listTest.size());
	}

	@Test
	public void invalidParkCodeReturnNull() {
		
		Park parkTest = jdbcParkDao.getParkByParkCode("XYZABC789");

		Assert.assertNull(parkTest);
	}

	@Test
	public void validParkCodeReturnsCorrectInfo() {
		
		Park parkTest = jdbcParkDao.getParkByParkCode("BNP123");

		Assert.assertEquals("Buckeye National Park", parkTest.getParkName());
		Assert.assertEquals("Ohio", parkTest.getState());
		Assert.assertEquals(55000, parkTest.getAcreage());
		Assert.assertEquals(400, parkTest.getElevationInFeet());
		Assert.assertEquals(250.5, parkTest.getMilesOfTrail(), 0.01);
		Assert.assertEquals(340, parkTest.getNumberOfCampsites());
		Assert.assertEquals("Woodland", parkTest.getClimate());
		Assert.assertEquals(1955, parkTest.getYearFounded());
		Assert.assertEquals(300000, parkTest.getAnnualVisitorCount());
		Assert.assertEquals("The heart of it all!", parkTest.getInspirationalQuote());
		Assert.assertEquals("Joe Buckeye", parkTest.getInspirationalQuoteSource());
		Assert.assertEquals("Lush woodlands with beautiful foliage. Beautiful caves and waterfalls.",
				parkTest.getParkDescription());
		Assert.assertEquals(20, parkTest.getEntryFee());
		Assert.assertEquals(400, parkTest.getNumberOfAnimalSpecies());
	}

}
