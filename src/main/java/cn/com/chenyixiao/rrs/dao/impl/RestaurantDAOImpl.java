package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.RestaurantDAO;
import cn.com.chenyixiao.rrs.entity.Restaurant;

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public void addRestaurant(Restaurant restaurant) {
		Restaurant restaurantToAdd = getRestaurant(restaurant.getId());
		if (restaurantToAdd == null) {
			getCurrentSession().save(restaurant);
		}
	}

	
	public void updateRestaurant(Restaurant restaurant) {
		getCurrentSession().update(restaurant);
	}

	
	public Restaurant getRestaurant(Long id) {
		return (Restaurant) getCurrentSession().get(Restaurant.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Restaurant> getAllRestaurants() {
		return getCurrentSession().
				createQuery("from Restaurant").list();
	}


	public void deleteRestaurant(Long id) {
		Restaurant restaurant = getRestaurant(id);
		if (restaurant != null) {
			getCurrentSession().delete(restaurant);
		}
	}


	@Override
	public void deleteAllRestaurants() {
		List<Restaurant> restaurants = getAllRestaurants();
		for (Restaurant restaurant : restaurants) {
			deleteRestaurant(restaurant.getId());
		}
		
	}

	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from Restaurant").uniqueResult();
	}
}
