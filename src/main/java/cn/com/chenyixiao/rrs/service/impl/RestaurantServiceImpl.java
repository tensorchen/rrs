package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.RestaurantDAO;
import cn.com.chenyixiao.rrs.entity.Restaurant;
import cn.com.chenyixiao.rrs.service.RestaurantService;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantDAO restaurantDAO;
	
	public void addRestaurant(Restaurant restaurant) {
		restaurantDAO.addRestaurant(restaurant);
	}

	
	public void updateRestaurant(Restaurant restaurant) {
		restaurantDAO.updateRestaurant(restaurant);
	}

	
	public Restaurant getRestaurant(Long id) {
		return restaurantDAO.getRestaurant(id);
	}

	
	public List<Restaurant> getAllRestaurants() {
		return restaurantDAO.getAllRestaurants();
	}

	public void deleteRestaurant(Long id) {
		restaurantDAO.deleteRestaurant(id);
	}


	@Override
	public void deleteAllRestaurants() {
		restaurantDAO.deleteAllRestaurants();
	}
	
	public Long count() {
		return restaurantDAO.count();
	}
}
