package cn.com.chenyixiao.rrs.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.Preference2d;
import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.Preference2dService;
import cn.com.chenyixiao.rrs.service.UserService;
import cn.com.chenyixiao.rrs.vo.RecommendResult;
import cn.com.chenyixiao.rrs.vo.RecommendResultVO;

@Controller
@RequestMapping(produces="application/json;charset=UTF-8")
public class RecommendController {

	@Autowired
	private Preference2dService preference2dService;
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value = "/recommend/{userId}", method = RequestMethod.GET)
	public String recommend(@PathVariable Long userId
			) throws TasteException, IOException {
		
		DataModel dataModel = getFileDataModel();
		
		
		UserSimilarity userSimilarity =
//				new PearsonCorrelationSimilarity(dataModel); //皮尔逊相关系数
//				new EuclideanDistanceSimilarity(dataModel); //欧氏距离
//				new TanimotoCoefficientSimilarity(dataModel); //谷本系数
				new LogLikelihoodSimilarity(dataModel); //对数似然比
		
//		userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(dataModel));
		
		UserNeighborhood userNeighborhood = 
				new NearestNUserNeighborhood(10, userSimilarity, dataModel);

		
		Recommender recommender = 
				//new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
				new SVDRecommender(dataModel, new ALSWRFactorizer(dataModel, 10, 0.05, 10));
		
		List<RecommendedItem> recommendedItems =
				recommender.recommend(userId, 10);
		
		Gson gson = new Gson();
		
		List<RecommendResultVO> recommendResultVOs =
				new ArrayList<RecommendResultVO>(recommendedItems.size());
		
		for (int i = 0; i < recommendedItems.size(); i++) {
			RecommendResultVO recommendResultVO = new RecommendResultVO();
			recommendResultVO.setRestaurantId(recommendedItems.get(i).getItemID());
			recommendResultVO.setScore(recommendedItems.get(i).getValue());
			
			recommendResultVOs.add(i, recommendResultVO);
		}
		
		return gson.toJson(recommendResultVOs);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public String recommendAll() throws TasteException, IOException {
		
		DataModel dataModel = getFileDataModel();
		
		UserSimilarity userSimilarity =
				new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood userNeighborhood = 
				new NearestNUserNeighborhood(2, userSimilarity, dataModel);
		
		Recommender recommender = 
//				new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
				new SVDRecommender(dataModel, new ALSWRFactorizer(dataModel, 10, 0.05, 10));
		
		List<User> users = userService.getAllUsers();
		List<RecommendResult> recommendResults = new ArrayList<RecommendResult>();
		
		for (int i = 0; i < users.size(); i++) {
			
			List<RecommendedItem> recommendedItems = 
					recommender.recommend(users.get(i).getId(), 10);

			if (!recommendedItems.isEmpty()) {
				RecommendResult recommendResult = new RecommendResult();
				recommendResult.setUserId(users.get(i).getId());
				
				List<RecommendResultVO> recommendResultVOs = new ArrayList<RecommendResultVO>();
				for (int j = 0; j < recommendedItems.size(); j++) {
					RecommendResultVO recommendResultVO = new RecommendResultVO();
					recommendResultVO.setRestaurantId(recommendedItems.get(j).getItemID());
					recommendResultVO.setScore(recommendedItems.get(j).getValue());
					
					recommendResultVOs.add(j, recommendResultVO);
				}
				recommendResult.setRecommendResultVOs(recommendResultVOs);
				recommendResults.add(recommendResult);
			}
			
		}
		
		Gson gson = new Gson();

		return gson.toJson(recommendResults);
	}
	
	private DataModel getGenericDataModel() {
		FastByIDMap<PreferenceArray> preferences = 
				new FastByIDMap<PreferenceArray>();
		
		List<User> users = userService.getAllUsers();
		
		for (int j = 0; j < users.size(); j++) {
			
			List<Preference2d> preference2ds = 
					preference2dService.getPreferencesByUserId(users.get(j).getId());
			PreferenceArray prefsForUser = 
					new GenericUserPreferenceArray(preference2ds.size());
			prefsForUser.setUserID(j, users.get(j).getId());
			
			for (int i = 0; i < preference2ds.size(); i++) {
				prefsForUser.setItemID(i, preference2ds.get(i).getRestaurantId());
				prefsForUser.setValue(i, preference2ds.get(i).getScore().floatValue());
			}
			
			preferences.put(j, prefsForUser);
		}
		
		return new GenericDataModel(preferences);
	}
	
	private DataModel getFileDataModel() throws IOException {
		return new FileDataModel(new File("D://data/dianping_user-restaurant.csv"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/data/csv", method = RequestMethod.GET)
	public void generateDataCSV() throws IOException {
		
		File file = new File("D://data/dianping_user-restaurant.csv");
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		
		List<User> users = userService.getAllUsers();
		for (int j = 0; j < users.size(); j++) {
			
			List<Preference2d> preference2dsForUser = 
					preference2dService.getPreferencesByUserId(users.get(j).getId());
			
			if (preference2dsForUser.size() != 0) {
				for (int i = 0; i < preference2dsForUser.size(); i++) {
					bw.write(String.valueOf(users.get(j).getId()));
					bw.write(",");
					bw.write(preference2dsForUser.get(i).getRestaurantId().toString());
					bw.write(",");
					bw.write(preference2dsForUser.get(i).getScore().toString());
					bw.newLine();
				}
			}
		}
		bw.flush();
		bw.close();
		fw.close();
	}
}
