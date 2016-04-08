package cn.com.chenyixiao.rrs.service;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.Preference2d;

public interface Preference2dService {

	public void addPreference2d(Preference2d preference2d);
	public void updatePreference2d(Preference2d preference2d);
	public Preference2d getPreference2d(Long id);
	public List<Preference2d> getAllPreference2ds();
	public void deletePreference2d(Long id);
	public void deleteAllPreference2d();
	
	public Long count();
}
