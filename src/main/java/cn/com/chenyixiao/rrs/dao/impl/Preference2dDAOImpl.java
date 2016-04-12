package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.Preference2dDAO;
import cn.com.chenyixiao.rrs.entity.Preference2d;

@Repository
public class Preference2dDAOImpl implements Preference2dDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public void addPreference2d(Preference2d preference2d) {
		getCurrentSession().save(preference2d);
	}

	
	public void updatePreference2d(Preference2d preference2d) {
		getCurrentSession().update(preference2d);
	}

	
	public Preference2d getPreference2d(Long id) {
		return (Preference2d) getCurrentSession().
				get(Preference2d.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Preference2d> getAllPreference2ds() {
		
		return getCurrentSession().
				createQuery("from Preference2d").list();
	}


	public void deletePreference2d(Long id) {
		Preference2d preference2d = getPreference2d(id);
		if (preference2d != null) {
			getCurrentSession().delete(preference2d);
		}
	}


	@Override
	public void deleteAllPreference2d() {
		List<Preference2d> preference2ds = getAllPreference2ds();
		for (Preference2d preference2d : preference2ds) {
			deletePreference2d(preference2d.getId());
		}
		
	}

	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from Preference2d").uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Preference2d> getPreferencesByUserId(Long userId) {

		String hql = "from Preference2d as p2 where p2.userId = :userId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		List<Preference2d> preference2ds = query.list();
		if (preference2ds.isEmpty()) {
			return null;
		} else {
			return preference2ds;
		}
	}
}
