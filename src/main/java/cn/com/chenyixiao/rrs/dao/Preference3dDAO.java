package cn.com.chenyixiao.rrs.dao;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.Preference3d;

public interface Preference3dDAO {

	public void addPreference3d(Preference3d preference3d);
	public void updatePreference3d(Preference3d preference3d);
	public Preference3d getPreference3d(Long id);
	public List<Preference3d> getAllPreference3ds();
	public void deletePreference3d(Long id);
	public void deleteAllPreference3d();
	
	public Long count();
}
