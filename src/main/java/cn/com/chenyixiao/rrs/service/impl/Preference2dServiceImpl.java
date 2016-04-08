package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.Preference2dDAO;
import cn.com.chenyixiao.rrs.entity.Preference2d;
import cn.com.chenyixiao.rrs.service.Preference2dService;

@Service
@Transactional
public class Preference2dServiceImpl implements Preference2dService {

	@Autowired
	private Preference2dDAO preference2dDAO;
	
	public void addPreference2d(Preference2d preference2d) {
		preference2dDAO.addPreference2d(preference2d);
	}

	
	public void updatePreference2d(Preference2d preference2d) {
		preference2dDAO.updatePreference2d(preference2d);
	}


	public Preference2d getPreference2d(Long id) {
		return preference2dDAO.getPreference2d(id);
	}

	
	public List<Preference2d> getAllPreference2ds() {
		return preference2dDAO.getAllPreference2ds();
	}

	public void deletePreference2d(Long id) {
		preference2dDAO.deletePreference2d(id);
	}


	@Override
	public void deleteAllPreference2d() {
		preference2dDAO.deleteAllPreference2d();
	}
	
	public Long count() {
		return preference2dDAO.count();
	}
}
