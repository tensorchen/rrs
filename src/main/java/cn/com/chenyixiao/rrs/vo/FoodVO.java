package cn.com.chenyixiao.rrs.vo;

public class FoodVO {
	private String foodName;
	private int    foodRecommendNum;
	
	
	public FoodVO() {
	}

	public FoodVO(String foodName, int foodRecommendNum) {
		this.foodName = foodName;
		this.foodRecommendNum = foodRecommendNum;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodRecommendNum() {
		return foodRecommendNum;
	}
	public void setFoodRecommendNum(int foodRecommendNum) {
		this.foodRecommendNum = foodRecommendNum;
	}
	
	
}
