package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.RestaurantFoodDAO;
import cn.com.chenyixiao.rrs.entity.RestaurantFood;

@Repository
public class RestaurantFoodImpl implements RestaurantFoodDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	@Override
	public void addRestaurantFood(RestaurantFood restaurantFood) {
		getCurrentSession().save(restaurantFood);
	}

	@Override
	public void updateRestaurantFood(RestaurantFood restaurantFood) {
	
		getCurrentSession().update(restaurantFood);
	}

	@Override
	public RestaurantFood getRestaurantFood(Long id) {
		return (RestaurantFood) getCurrentSession()
				.get(RestaurantFood.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RestaurantFood> getAllRestaurantFoods() {
		return getCurrentSession()
				.createQuery("from RestaurantFood").list();
	}

	
	@Override
	public void deleteRestaurantFood(Long id) {
		RestaurantFood restaurantFood = getRestaurantFood(id);
		if (restaurantFood != null) {
			getCurrentSession().delete(restaurantFood);
		}
	}

	@Override
	public void deleteAllRestaurantFood() {
		List<RestaurantFood> restaurantFoods = getAllRestaurantFoods();
		for (RestaurantFood restaurantFood : restaurantFoods) {
			deleteRestaurantFood(restaurantFood.getId());
		}
	}


	@Override
	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from RestaurantFood").uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RestaurantFood> getRestaurantFoodsByRestaurantId(Long restaurantId) {
		String hql = "from RestaurantFood as rf where rf.restaurantId = :restaurantId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("restaurantId", restaurantId);
		List<RestaurantFood> restaurantFoods = query.list();
		if (restaurantFoods.isEmpty()) {
			return null;
		} else {
			return restaurantFoods;
		}
	}

}
