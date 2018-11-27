package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.model.JDBCParkDAO;
import com.techelevator.npgeek.model.JDBCWeatherDAO;
import com.techelevator.npgeek.model.Weather;

public class JDBCWeatherDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCParkDAO jdbcParkDao;
	private JDBCWeatherDAO jdbcWeatherDao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		
		jdbcParkDao = new JDBCParkDAO(getDataSource());
		jdbcWeatherDao = new JDBCWeatherDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, "
				+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
				+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, "
				+ "numberofanimalspecies) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, "BNP123", "Buckeye National Park", "Ohio", 55000, 400, 250.5, 340, "Woodland", 1955,
				300000, "The heart of it all!", "Joe Buckeye",
				"Lush woodlands with " + "beautiful foliage. Beautiful caves and waterfalls.", 20, 400);

		String sql2 = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql2, "BNP123", 1, 27, 40, "cloudy");
		jdbcTemplate.update(sql2, "BNP123", 2, 34, 41, "rain");
		jdbcTemplate.update(sql2, "BNP123", 3, 45, 55, "cloudy");
		jdbcTemplate.update(sql2, "BNP123", 4, 49, 60, "sunny");
		jdbcTemplate.update(sql2, "BNP123", 5, 51, 59, "partly cloudy");
	}

	@Test
	public void returnsFiveDaysOfForcastFromParkCode() {
		List<Weather> listTest = jdbcWeatherDao.getWeatherByParkCode("BNP123");
		Assert.assertEquals(5, listTest.size());
	}

}
