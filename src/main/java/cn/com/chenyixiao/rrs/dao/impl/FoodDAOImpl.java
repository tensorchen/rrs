package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.FoodDAO;
import cn.com.chenyixiao.rrs.entity.Food;

@Repository
public class FoodDAOImpl implements FoodDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public void addFood(Food food) {
		if (findByName(food.getName()) == null) {
			getCurrentSession().save(food);
		}
	}

	
	public void updateFood(Food food) {
		
		getCurrentSession().update(food);
	}

	
	public Food getFood(Long id) {
		
		return (Food) getCurrentSession().get(Food.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Food> getAllFoods() {
		
		return getCurrentSession().
				createQuery("from Food").list();	
	}


	public void deleteFood(Long id) {
		Food food = getFood(id);
		if (food != null) {
			getCurrentSession().delete(food);
		}
	}
	
	@Override
	public void deleteAllFoods() {
		List<Food> foods = getAllFoods();
		for (Food food : foods) {
			deleteFood(food.getId());
		}
	}
	
	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from Food").uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Food findByName(String name) {
		String hql = "from Food as f where f.name = :name";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("name", name);
		List<Food> foods = query.list();
		if (foods.isEmpty()) {
			return null;
		} else {
			return foods.get(0);
		}
	}
}
