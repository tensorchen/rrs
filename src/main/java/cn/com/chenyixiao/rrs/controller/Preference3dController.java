package cn.com.chenyixiao.rrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.Preference3d;
import cn.com.chenyixiao.rrs.service.Preference3dService;

@Controller
@RequestMapping(produces="application/json;charset=UTF-8", 
		consumes="application/json;charset=UTF-8")
public class Preference3dController {
	
	@Autowired
	private Preference3dService preference3dService;
	
	@ResponseBody
	@RequestMapping(value = "/preference3d", method = RequestMethod.POST)
	public String addPreference3d(@RequestBody List<Preference3d> preference3dList) {
		for (Preference3d preference3d : preference3dList) {
			preference3dService.addPreference3d(preference3d);
		}
		return new Gson().toJson("success");
	}
	
	@ResponseBody
	@RequestMapping(value = "/preference3d", method = RequestMethod.GET)
	public String getPreference3dList() {
		
		List<Preference3d> preference3dList = preference3dService.getAllPreference3ds();

		return new Gson().toJson(preference3dList);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/preference3d/user/{userId}", method = RequestMethod.GET)
	public String getPreference3dByUserList(@PathVariable Long userId) {
		
		List<Preference3d> preference3dByUserList = preference3dService.getPreference3dListByUser(userId);

		return new Gson().toJson(preference3dByUserList);
	}
}
