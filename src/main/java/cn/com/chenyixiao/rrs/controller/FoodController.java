package cn.com.chenyixiao.rrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.Preference3d;
import cn.com.chenyixiao.rrs.service.Preference3dService;

@Controller
@RequestMapping(value = "/food", produces="application/json;charset=UTF-8", 
	consumes="application/json;charset=UTF-8")
public class FoodController {
	
	@Autowired
	private Preference3dService preference3dService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String addPreference3d(@RequestBody String preference3dStr) {
		System.out.println(preference3dStr);
		Preference3d preference3d = new Gson().fromJson(preference3dStr, Preference3d.class);
		System.out.println(preference3d.toString());
		preference3dService.addOrUpdatePreference3d(preference3d);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String getPreference3d() {
		
		

		return new Gson().toJson(preference3dService.getPreference3d(Long.valueOf(30794)));
	}
}
