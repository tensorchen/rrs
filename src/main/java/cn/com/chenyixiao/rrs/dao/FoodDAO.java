package cn.com.chenyixiao.rrs.dao;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.Food;

public interface FoodDAO {

	public void addFood(Food food);
	public void updateFood(Food food);
	public Food getFood(Long id);
	public List<Food> getAllFoods();
	public void deleteFood(Long id);
	public void deleteAllFoods();
	
	public Long count();
	
	public Food findByName(String name);
}
