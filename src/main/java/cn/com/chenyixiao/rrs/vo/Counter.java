package cn.com.chenyixiao.rrs.vo;

public class Counter {
	private Long userNum;
	private Long foodNum;
	private Long restaurantNum;
	private Long preference2d;
	private Long preference3d;
	private Long restaurantFood;
	

	public Counter() {
		super();
	}

	
	public Counter(Long userNum, Long foodNum, Long restaurantNum, Long preference2d, Long preference3d,
			Long restaurantFood) {
		super();
		this.userNum = userNum;
		this.foodNum = foodNum;
		this.restaurantNum = restaurantNum;
		this.preference2d = preference2d;
		this.preference3d = preference3d;
		this.restaurantFood = restaurantFood;
	}


	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Long getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(Long foodNum) {
		this.foodNum = foodNum;
	}
	public Long getRestaurantNum() {
		return restaurantNum;
	}
	public void setRestaurantNum(Long restaurantNum) {
		this.restaurantNum = restaurantNum;
	}
	public Long getPreference2d() {
		return preference2d;
	}
	public void setPreference2d(Long preference2d) {
		this.preference2d = preference2d;
	}
	public Long getPreference3d() {
		return preference3d;
	}
	public void setPreference3d(Long preference3d) {
		this.preference3d = preference3d;
	}
	
	
	public Long getRestaurantFood() {
		return restaurantFood;
	}


	public void setRestaurantFood(Long restaurantFood) {
		this.restaurantFood = restaurantFood;
	}


}
