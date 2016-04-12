package cn.com.chenyixiao.rrs.dao;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.RestaurantFood;

public interface RestaurantFoodDAO {

	public void addRestaurantFood(RestaurantFood restaurantFood);
	public void updateRestaurantFood(RestaurantFood restaurantFood);
	public RestaurantFood getRestaurantFood(Long id);
	public List<RestaurantFood> getAllRestaurantFoods();
	public void deleteRestaurantFood(Long id);
	public void deleteAllRestaurantFood();
	
	public Long count();
	
	public List<RestaurantFood> getRestaurantFoodsByRestaurantId(Long restaurantId);
}
