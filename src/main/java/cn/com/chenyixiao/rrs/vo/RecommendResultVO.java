package cn.com.chenyixiao.rrs.vo;

public class RecommendResultVO {

	private Long restaurantId;
	private float score;
	
	
	public RecommendResultVO() {
	}
	
	
	public RecommendResultVO(Long restaurantId, float score) {

		this.restaurantId = restaurantId;
		this.score = score;
	}
	
	
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	
}

