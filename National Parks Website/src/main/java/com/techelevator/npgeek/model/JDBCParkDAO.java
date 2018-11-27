package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		String sql = "SELECT * FROM park ORDER BY parkname";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while (result.next()) {
			Park park = mapRowToPark(result);
			parks.add(park);
		}
		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park park = null;
		String sql = "SELECT * FROM park WHERE parkcode = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkCode);
		if (result.next()) {
			park = mapRowToPark(result);
		}
		return park;
	}

	private Park mapRowToPark(SqlRowSet result) {
		Park park = new Park();
		park.setParkCode(result.getString("parkcode"));
		park.setParkName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getInt("acreage"));
		park.setElevationInFeet(result.getInt("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setNumberOfCampsites(result.getInt("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getInt("yearfounded"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));

		return park;
	}
}

