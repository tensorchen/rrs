package cn.com.chenyixiao.rrs.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.Food;
import cn.com.chenyixiao.rrs.entity.Preference2d;
import cn.com.chenyixiao.rrs.entity.Preference3d;
import cn.com.chenyixiao.rrs.entity.Restaurant;
import cn.com.chenyixiao.rrs.entity.RestaurantFood;
import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.FoodService;
import cn.com.chenyixiao.rrs.service.Preference2dService;
import cn.com.chenyixiao.rrs.service.Preference3dService;
import cn.com.chenyixiao.rrs.service.RestaurantFoodService;
import cn.com.chenyixiao.rrs.service.RestaurantService;
import cn.com.chenyixiao.rrs.service.UserService;
import cn.com.chenyixiao.rrs.vo.Counter;
import cn.com.chenyixiao.rrs.vo.FoodVO;
import cn.com.chenyixiao.rrs.vo.PreferenceVO;
import cn.com.chenyixiao.rrs.vo.Rrs;

@Controller
@RequestMapping(value="/data")
public class DataController {
	
	private String dataFile = "./data/dianping.json";
	
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private Preference2dService preference2dService;
	@Autowired
	private Preference3dService preference3dService;
	@Autowired
	private RestaurantFoodService restaurantFoodService;

	@ResponseBody
	//@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load() throws IOException {
		loadData();
		return countData();
	}
	
	@ResponseBody
	//@RequestMapping(value = "/clean", method = RequestMethod.GET)
	public String clean() {
		cleanData();
		return countData();
	}
	
	@ResponseBody
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public String count() {
		return countData();
	}
	
	private String countData() {
		Counter counter = new Counter();
		counter.setFoodNum(foodService.count());
		counter.setUserNum(userService.count());
		counter.setRestaurantNum(restaurantService.count());
		counter.setPreference2d(preference2dService.count());
		counter.setPreference3d(preference3dService.count());
		counter.setRestaurantFood(restaurantFoodService.count());
		
		Gson gson = new Gson();
		return gson.toJson(counter);
	}
	
	private void loadData() throws IOException {
		
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
	
	private void cleanData() {
		restaurantService.deleteAllRestaurants();
		foodService.deleteAllFoods();
		userService.deleteAllUsers();
		preference2dService.deleteAllPreference2d();
		preference3dService.deleteAllPreference3d();
		restaurantFoodService.deleteAllRestaurantFoods();
	}
	
	private void save(Rrs rrs) {
		saveRestaurant(rrs);
		saveFoods(rrs);
		savePreferences(rrs);
	}
	
	private void saveRestaurant(Rrs rrs) {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(rrs.getRestaurantId());
		restaurant.setName(rrs.getRestaurantName());
		restaurant.setCategory(rrs.getRestaurantCategory());
		restaurant.setRegion(rrs.getRestaurantRegion());
		restaurantService.addRestaurant(restaurant);
	}
	
	private void saveFoods(Rrs rrs) {
		List<FoodVO> foodVOs = rrs.getFoods();
		for (FoodVO foodVO : foodVOs) {
			Food food = new Food();
			food.setName(foodVO.getFoodName());
			foodService.addFood(food);
			
			RestaurantFood restaurantFood = new RestaurantFood();
			restaurantFood.setRestaurantId(rrs.getRestaurantId());
			restaurantFood.setFoodId(foodService.findByName(foodVO.getFoodName()).getId());
			restaurantFoodService.addRestaurantFood(restaurantFood);
		}
	}
	
	private void savePreferences(Rrs rrs) {
		List<PreferenceVO> preferenceVOs = rrs.getPreferences();
		for (PreferenceVO preferenceVO : preferenceVOs) {
			User user = new User();
			user.setId(preferenceVO.getUserId());
			user.setName(preferenceVO.getUserName());
			userService.addUser(user);
			
			Preference2d preference2d = new Preference2d();
			preference2d.setRestaurantId(rrs.getRestaurantId());
			preference2d.setUserId(preferenceVO.getUserId());
			preference2d.setScore(computeAverage(preferenceVO.getScores()));
			preference2dService.addPreference2d(preference2d);
			
			List<String> foodNames = preferenceVO.getRecommendFoods();
			for (String foodName : foodNames) {
				Food food = foodService.findByName(foodName);
				if (food != null) {
					Preference3d preference3d = new Preference3d();
					preference3d.setFoodId(food.getId());
					preference3d.setRestaurantId(rrs.getRestaurantId());
					preference3d.setUserId(preferenceVO.getUserId());
					preference3dService.addPreference3d(preference3d);
				}
				
			}
		}
	}
	
	

	
	private Double computeAverage(List<Double> list) {
		
		if (list.size() == 0) {
			return (double) 0;
		} else {
			Double scoreTotal = (double) 0;
			for (Double score: list) {
				scoreTotal += score;
			}
			return scoreTotal / list.size();
		}
	}
}
