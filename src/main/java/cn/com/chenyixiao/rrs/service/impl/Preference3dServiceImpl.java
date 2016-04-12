package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.Preference3dDAO;
import cn.com.chenyixiao.rrs.entity.Preference3d;
import cn.com.chenyixiao.rrs.service.Preference3dService;

@Service
@Transactional
public class Preference3dServiceImpl implements Preference3dService {

	@Autowired
	private Preference3dDAO preference3dDAO;
	
	public void addPreference3d(Preference3d preference3d) {
		preference3dDAO.addPreference3d(preference3d);
	}

	
	public void updatePreference3d(Preference3d preference3d) {
		preference3dDAO.updatePreference3d(preference3d);
	}


	public Preference3d getPreference3d(Long id) {
		return preference3dDAO.getPreference3d(id);
	}

	
	public List<Preference3d> getAllPreference3ds() {
		return preference3dDAO.getAllPreference3ds();
	}

	public void deletePreference3d(Long id) {
		preference3dDAO.deletePreference3d(id);
	}


	@Override
	public void deleteAllPreference3d() {
		preference3dDAO.deleteAllPreference3d();
	}
	
	public Long count() {
		return preference3dDAO.count();
	}


	@Override
	public void addOrUpdatePreference3d(Preference3d preference3d) {
		Preference3d preference3dToAdd = preference3dDAO.getPreference3dByURF(preference3d.getFoodId(), 
				preference3d.getUserId(), preference3d.getRestaurantId());
		
		if (preference3dToAdd == null) {
			preference3dDAO.addPreference3d(preference3d);
		} else {
			preference3dToAdd.setContent(preference3d.getContent());
			preference3dToAdd.setScore(preference3d.getScore());
			preference3dDAO.updatePreference3d(preference3dToAdd);
		}
		
	}
}
