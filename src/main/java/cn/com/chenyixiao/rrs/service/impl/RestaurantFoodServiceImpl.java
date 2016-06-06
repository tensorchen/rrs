package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.RestaurantFoodDAO;
import cn.com.chenyixiao.rrs.entity.RestaurantFood;
import cn.com.chenyixiao.rrs.service.RestaurantFoodService;


@Service
@Transactional
public class RestaurantFoodServiceImpl implements RestaurantFoodService {

	@Autowired
	private RestaurantFoodDAO restaurantFoodDAO;
	
	
	@Override
	public void addRestaurantFood(RestaurantFood restaurantFood) {
		restaurantFoodDAO.addRestaurantFood(restaurantFood);
	}

	@Override
	public void updateRestaurantFood(RestaurantFood restaurantFood) {
		restaurantFoodDAO.updateRestaurantFood(restaurantFood);
	}

	@Override
	public RestaurantFood getRestaurantFood(Long id) {
		return restaurantFoodDAO.getRestaurantFood(id);
	}

	@Override
	public List<RestaurantFood> getAllRestaurantFoods() {
		return restaurantFoodDAO.getAllRestaurantFoods();
	}

	@Override
	public void deleteRestaurantFood(Long id) {
		restaurantFoodDAO.deleteRestaurantFood(id);
	}

	@Override
	public void deleteAllRestaurantFoods() {
		restaurantFoodDAO.deleteAllRestaurantFood();
	}

	@Override
	public Long count() {
		return restaurantFoodDAO.count();
	}

	@Override
	public List<RestaurantFood> getRestaurantFoodsByRestaurantId(Long restaurantId) {
		return restaurantFoodDAO.getRestaurantFoodsByRestaurantId(restaurantId);
	}

}
