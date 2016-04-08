package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.FoodDAO;
import cn.com.chenyixiao.rrs.entity.Food;
import cn.com.chenyixiao.rrs.service.FoodService;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodDAO foodDAO;
	
	
	public void addFood(Food food) {
		foodDAO.addFood(food);
	}

	public void updateFood(Food food) {
		foodDAO.updateFood(food);
	}

	public Food getFood(Long id) {
		return foodDAO.getFood(id);
	}

	@Override
	public List<Food> getAllFoods() {
		return foodDAO.getAllFoods();
	}

	public void deleteFood(Long id) {
		foodDAO.deleteFood(id);
	}
	
	@Override
	public void deleteAllFoods() {
		foodDAO.deleteAllFoods();
	}
	
	public Long count() {
		return foodDAO.count();
	}

	@Override
	public Food findByName(String name) {
		return foodDAO.findByName(name);
	}
}
