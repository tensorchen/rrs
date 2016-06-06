package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.Preference3dDAO;
import cn.com.chenyixiao.rrs.entity.Preference3d;

@Repository
public class Preference3dDAOImpl implements Preference3dDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public void addPreference3d(Preference3d preference3d) {
		getCurrentSession().save(preference3d);
	}

	
	public void updatePreference3d(Preference3d preference3d) {
		getCurrentSession().update(preference3d);
	}

	
	public Preference3d getPreference3d(Long id) {
		return (Preference3d) getCurrentSession().
				get(Preference3d.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Preference3d> getAllPreference3ds() {
		
		return getCurrentSession().
				createQuery("from Preference3d").list();
	}


	public void deletePreference3d(Long id) {
		Preference3d preference3d = getPreference3d(id);
		if (preference3d != null) {
			getCurrentSession().delete(preference3d);
		}
	}


	@Override
	public void deleteAllPreference3d() {
		List<Preference3d> preference3ds = getAllPreference3ds();
		for (Preference3d preference3d : preference3ds) {
			deletePreference3d(preference3d.getId());
		}
		
	}

	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from Preference3d").uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Preference3d getPreference3dByURF(Long foodId, Long userId, Long restaurantId) {
		String hql = "from Preference3d as p3 where p3.foodId = :foodId and p3.userId = :userId and p3.restaurantId = :restaurantId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("foodId", foodId);
		List<Preference3d> preference3ds = query.list();
		
		if (preference3ds == null) {
			return null;
		} else {
			return preference3ds.get(0);
		}
 	}


	@Override
	public List<Preference3d> getPreference3dListByUser(Long userId) {

		String hql = "from Preference3d as p3 where p3.userId = :userId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		List<Preference3d> preference3ds = query.list();
		
		if (preference3ds == null) {
			return null;
		} else {
			return preference3ds;
		}
	}
}
