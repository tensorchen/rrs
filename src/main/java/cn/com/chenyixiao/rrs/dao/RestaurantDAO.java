package cn.com.chenyixiao.rrs.dao;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.Restaurant;

public interface RestaurantDAO {

	public void addRestaurant(Restaurant restaurant);
	public void updateRestaurant(Restaurant restaurant);
	public Restaurant getRestaurant(Long id);
	public List<Restaurant> getAllRestaurants();
	public void deleteRestaurant(Long id);
	public void deleteAllRestaurants();
	
	public Long count();
}
