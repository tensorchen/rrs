package cn.com.chenyixiao.rrs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_food", catalog = "rrs")
public class RestaurantFood {
	private long id;
	private long restaurantId;
	private long foodId;
	
	
	public RestaurantFood() {
	}


	public RestaurantFood(long id, long restaurantId, long foodId) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.foodId = foodId;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "restaurant_id")
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	@Column(name = "food_id")
	public long getFoodId() {
		return foodId;
	}
	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}
	
	
}
