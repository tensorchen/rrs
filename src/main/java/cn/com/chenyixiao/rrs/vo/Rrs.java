package cn.com.chenyixiao.rrs.vo;

import java.util.List;

public class Rrs {
	private Long restaurantId;
	private String restaurantName;
	private String restaurantRegion;
	private String restaurantCategory;
	
	private List<FoodVO> foods;
	private List<PreferenceVO> preferences;
	
	
	public Rrs() {
	}

	public Rrs(Long restaurantId, String restaurantName, String restaurantRegion, String restaurantCategory,
			List<FoodVO> foods, List<PreferenceVO> preferences) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantRegion = restaurantRegion;
		this.restaurantCategory = restaurantCategory;
		this.foods = foods;
		this.preferences = preferences;
	}
	
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantRegion() {
		return restaurantRegion;
	}
	public void setRestaurantRegion(String restaurantRegion) {
		this.restaurantRegion = restaurantRegion;
	}
	public String getRestaurantCategory() {
		return restaurantCategory;
	}
	public void setRestaurantCategory(String restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}
	public List<FoodVO> getFoods() {
		return foods;
	}
	public void setFoods(List<FoodVO> foods) {
		this.foods = foods;
	}
	public List<PreferenceVO> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<PreferenceVO> preferences) {
		this.preferences = preferences;
	}
	
	
}
