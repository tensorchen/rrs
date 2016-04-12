package cn.com.chenyixiao.rrs.service;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.RestaurantFood;

public interface RestaurantFoodService {

	public void addRestaurantFood(RestaurantFood restaurantFood);
	public void updateRestaurantFood(RestaurantFood restaurantFood);
	public RestaurantFood getRestaurantFood(Long id);
	public List<RestaurantFood> getAllRestaurantFoods();
	public void deleteRestaurantFood(Long id);
	public void deleteAllRestaurantFoods();
	
	public Long count();
}
