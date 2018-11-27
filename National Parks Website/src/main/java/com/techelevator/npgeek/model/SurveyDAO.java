package com.techelevator.npgeek.model;

import java.util.List;

public interface SurveyDAO {
	
	public List<Survey> getAllSurveys();
	
	public Survey getSurveyById(int surveyID);
	
	public void saveSurvey(Survey newSurvey);
	
	public List<SurveyResults> getSurveyResults();

}
