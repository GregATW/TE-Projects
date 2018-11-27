package com.techelevator;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.model.JDBCParkDAO;
import com.techelevator.npgeek.model.JDBCSurveyDAO;
import com.techelevator.npgeek.model.Survey;

import cucumber.api.java.Before;

public class JDBCSurveyDAOIntegrationTest extends DAOIntegrationTest{
	
	private JDBCSurveyDAO surveyDAO;
	private JdbcTemplate jdbcTemplate;
	
	private JDBCParkDAO jdbcParkDao;
	
	@Before
	public void setup() {
		jdbcParkDao = new JDBCParkDAO(getDataSource());
		
		surveyDAO = new JDBCSurveyDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, "
				+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
				+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, "
				+ "numberofanimalspecies) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, "BNP123", "Buckeye National Park", "Ohio", 55000, 400, 250.5, 340, "Woodland", 1955,
				300000, "The heart of it all!", "Joe Buckeye",
				"Lush woodlands with " + "beautiful foliage. Beautiful caves and waterfalls.", 20, 400);
		
		
		
		String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertSurvey, 1, "GNP", "you@hotmail.com", "Maine", "inactive");

		
		
		
//		String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) "
//				+ "VALUES (default, 'GNP', 'you@hotmail.com', 'Maine', 'inactive')";
//		jdbcTemplate = new JdbcTemplate(getDataSource());
//		jdbcTemplate.update(sqlInsertSurvey);
//		surveyDAO = new JDBCSurveyDAO(getDataSource());
	}
	
	@Test
	public void get_all_surveys_returns_at_least_one_survey() {

		List<Survey> listTest = surveyDAO.getAllSurveys();
		Assert.assertNotEquals(0, listTest.size());
	}
	
	@Test
	public void save_survey() {
		//Arrange
		JdbcTemplate  jdbcTemplate = new JdbcTemplate(getDataSource());
		String insertSql = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) "
				+ "VALUES (default, 'GNP', 'you@hotmail.com', 'Maine', 'inactive') RETURNING surveyid";
		SqlRowSet ourResult = jdbcTemplate.queryForRowSet(insertSql);
		
		ourResult.next();
		int surveyId = ourResult.getInt("surveyid");
		surveyDAO.getAllSurveys();
		
		Survey survey = new Survey();
		survey.setEmailAddress("you@hotmail.com");
		survey.setSurveyId(surveyId);
		
		//Act
		surveyDAO.saveSurvey(survey);		
		Survey newsurvey = surveyDAO.getSurveyById(surveyId); 
		
		Assert.assertEquals(survey.getEmailAddress(), newsurvey.getEmailAddress());
		Assert.assertNotEquals(null, survey.getSurveyId());
	}
}
