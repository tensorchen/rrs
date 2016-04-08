package cn.com.chenyixiao.rrs.vo;

import java.util.List;

public class PreferenceVO {

	private Long userId;
	private String userName;
	private List<Double> scores;
	private List<String> recommendFoods;
	
	public PreferenceVO() {
	}
	
	public PreferenceVO(Long userId, String userName, List<Double> scores, List<String> recommendFoods) {
		this.userId = userId;
		this.userName = userName;
		this.scores = scores;
		this.recommendFoods = recommendFoods;
	}



	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Double> getScores() {
		return scores;
	}
	public void setScores(List<Double> scores) {
		this.scores = scores;
	}
	public List<String> getRecommendFoods() {
		return recommendFoods;
	}
	public void setRecommendFoods(List<String> recommendFoods) {
		this.recommendFoods = recommendFoods;
	}
	
}
