package com.model;

public class Policy {
	private int id;
	private String title;
	private double premiumAmount;
	private String description;
	private int termDuration;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTermDuration() {
		return termDuration;
	}
	public void setTermDuration(int termDuration) {
		this.termDuration = termDuration;
	}

}