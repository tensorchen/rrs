package cn.com.chenyixiao.rrs.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.UserService;
import cn.com.chenyixiao.rrs.vo.PreferenceVO;
import cn.com.chenyixiao.rrs.vo.Rrs;

public class DataLoader {

	private static final String dataFile = "D://data/dianping.json";
	
	@Autowired
	private static UserService userService;
	
	@Resource
	private static Environment env;

	public static void load() throws IOException {
		
		FileInputStream fis = new FileInputStream(dataFile); 
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
	
		String data = null;
		
		while ((data = br.readLine()) != null) {
			Gson gson = new Gson();
			Rrs rrs = gson.fromJson(data, Rrs.class);
			save(rrs);
		}
		
		br.close();
		isr.close();
		fis.close();
	}
	
	private static void save(Rrs rrs) {
		List<PreferenceVO> preferences = rrs.getPreferences();
		
		for (PreferenceVO preference : preferences) {
			User user = new User();
			user.setId(preference.getUserId());
			user.setName(preference.getUserName());
			System.out.println("Mark:::::::::::::::");
			System.out.println(user.getName());
			//logger.debug("Mark:::::::::::::::::::");
			//logger.debug(user.getName());
			userService.addUser(user);
		}
	}
}
