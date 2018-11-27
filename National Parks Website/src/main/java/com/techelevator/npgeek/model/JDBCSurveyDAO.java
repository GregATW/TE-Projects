package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired						
	public JDBCSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Survey> getAllSurveys() {
		List<Survey> surveys = new ArrayList<>();
		String sql = "SELECT park.parkname, COUNT(*) FROM survey_result JOIN park ON park.parkcode = "
				+ "survey_result.parkcode GROUP BY park.parkname ORDER BY COUNT(*) DESC";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while (result.next()) {
			Survey survey = mapRowToSurvey(result);
			surveys.add(survey);
		}
		return surveys;
	}
	
	@Override
	public List<SurveyResults> getSurveyResults() {
		List<SurveyResults> surveyResults = new ArrayList<>();
		String sql = "SELECT park.parkname, park.parkcode, COUNT(*) AS votes FROM survey_result JOIN park ON park.parkcode = "
				+ "survey_result.parkcode GROUP BY park.parkname, park.parkcode ORDER BY COUNT(*) DESC, park.parkname";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while (result.next()) {
			SurveyResults surveyResult = mapRowToSurveyResults(result);
			surveyResults.add(surveyResult);
		}
		return surveyResults;
	}
	
	@Override
	public Survey getSurveyById(int surveyId) {
		Survey survey = null;
		String sql = "SELECT surveyid, parkcode, emailaddress, "
				+ "state, activitylevel FROM survey_result WHERE surveyid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, surveyId);			
		if(results.next()) {
			survey = mapRowToSurvey(results);
		}
		return null;
	}
	
	@Override
	public void saveSurvey(Survey newSurvey) {
		Long surveyId = getNextSurveyId();
		String sql = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) values (?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, surveyId, newSurvey.getParkCode(), newSurvey.getEmailAddress(), newSurvey.getState(),
				newSurvey.getActivityLevel());
		newSurvey.setSurveyId(surveyId);
	}
	
	private Long getNextSurveyId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long surveyId = null;
		if(results.next()) {
			surveyId = results.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new survey");
		}
		return surveyId;
	}

	private Survey mapRowToSurvey(SqlRowSet result) {
		Survey survey = new Survey();
		survey.setSurveyId(result.getLong("surveyid"));
		survey.setParkCode(result.getString("parkcode"));
		survey.setEmailAddress(result.getString("emailaddress"));
		survey.setState(result.getString("state"));
		survey.setActivityLevel(result.getString("activitylevel"));

		return survey;
	}
	
	private SurveyResults mapRowToSurveyResults(SqlRowSet result) {
		SurveyResults surveyResults = new SurveyResults();
		surveyResults.setParkCode(result.getString("parkCode"));
		surveyResults.setParkName(result.getString("parkName"));
		surveyResults.setVotes(result.getInt("votes"));
	
		return surveyResults;
	}
}