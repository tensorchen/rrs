package cn.com.chenyixiao.rrs.entity;
// Generated 2016-4-6 18:18:32 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Preference3d generated by hbm2java
 */
@Entity
@Table(name = "preference3d", catalog = "rrs")
public class Preference3d implements java.io.Serializable {

	private long id;
	private Long restaurantId;
	private Long foodId;
	private Long userId;

	public Preference3d() {
	}

	public Preference3d(long id) {
		this.id = id;
	}

	public Preference3d(long id, Long restaurantId, Long foodId, Long userId) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.foodId = foodId;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "restaurant_id")
	public Long getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Column(name = "food_id")
	public Long getFoodId() {
		return this.foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}