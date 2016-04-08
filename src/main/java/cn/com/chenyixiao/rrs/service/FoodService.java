package cn.com.chenyixiao.rrs.service;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.Food;

public interface FoodService {

	public void addFood(Food food);
	public void updateFood(Food food);
	public Food getFood(Long id);
	public List<Food> getAllFoods();
	public void deleteFood(Long id);
	public void deleteAllFoods();
	
	public Long count();
	
	public Food findByName(String name);
}
