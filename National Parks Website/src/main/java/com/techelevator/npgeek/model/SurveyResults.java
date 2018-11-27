package com.techelevator.npgeek.model;


public class SurveyResults {

	private String parkCode;
	private String parkName;
	private int votes;
	private String imageName;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getImageName() {
		String imageName = parkCode.toLowerCase();
		return imageName;
	}

	public void setImageName() {
		this.imageName = parkCode.toLowerCase();
	}

}