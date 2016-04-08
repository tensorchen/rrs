package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

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
}
