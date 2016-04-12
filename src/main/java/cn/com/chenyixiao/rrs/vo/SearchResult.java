package cn.com.chenyixiao.rrs.vo;

import java.util.List;

public class SearchResult {
	private Long restaurantId;
	private String restaurantName;
	private List<String> foods;
	private String key;


	public SearchResult() {
	}
	
	public SearchResult(Long restaurantId, String restaurantName, List<String> foods, String key) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.foods = foods;
		this.key = key;
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
	public List<String> getFoods() {
		return foods;
	}
	public void setFoods(List<String> foods) {
		this.foods = foods;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
