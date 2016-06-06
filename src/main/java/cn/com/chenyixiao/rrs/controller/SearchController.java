package cn.com.chenyixiao.rrs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.Food;
import cn.com.chenyixiao.rrs.entity.Restaurant;
import cn.com.chenyixiao.rrs.service.FoodService;

import cn.com.chenyixiao.rrs.service.RestaurantService;
import cn.com.chenyixiao.rrs.util.Indexer;
import cn.com.chenyixiao.rrs.vo.SearchResult;

@Controller
public class SearchController {

	private String SUCCESS = "success";
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private FoodService foodService;
	
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() throws IOException {
		indexData();
		return SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET, 
				produces="application/json;charset=UTF-8")
	public String search(@PathVariable String key) throws IOException, 
			ParseException, InvalidTokenOffsetsException {
		return searchData(key);
	}
	
	private void indexData() throws IOException {
		Indexer indexer = new Indexer("./index");
		
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		
		for (Restaurant restaurant : restaurants) {
			Document doc = new Document();
			doc.add(new Field("id", String.valueOf(restaurant.getId()),
							  Field.Store.YES,
							  Field.Index.NOT_ANALYZED));
			doc.add(new Field("restaurant_name", restaurant.getName(),
						      Field.Store.YES,
						      Field.Index.ANALYZED));
			StringBuilder foodsStrBuilder = new StringBuilder();
			List<Food> foods =  foodService.getFoodsByRestaurantId(restaurant.getId());
			
			if (foods != null) {
				for ( Food food : foods) {
					foodsStrBuilder.append(food.getName());
				}
				
				doc.add(new Field("foods", foodsStrBuilder.toString(),
								  Field.Store.YES,
								  Field.Index.ANALYZED));
				
				indexer.write(doc);
			}

		}
		indexer.close();
		
	}
	
	private String searchData(String key) throws IOException, ParseException, InvalidTokenOffsetsException {
		Directory directory = FSDirectory.open(new File("./index"));
		IndexSearcher indexSearcher = new IndexSearcher(directory);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_31, "foods",
				new SmartChineseAnalyzer(Version.LUCENE_31, true));
		//queryParser.setDefaultOperator(Operator.AND);

		Query query = queryParser.parse(key);
		TopDocs docs = indexSearcher.search(query, 10);
		
		QueryScorer queryScorer = new QueryScorer(query, "foods");
		Highlighter highlighter = new Highlighter(queryScorer);
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(queryScorer));


		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		if (docs != null) {
			for (ScoreDoc scoreDoc : docs.scoreDocs) {
				Document doc = indexSearcher.doc(scoreDoc.doc);
				TokenStream tokenStream = TokenSources.getAnyTokenStream(
						indexSearcher.getIndexReader(), scoreDoc.doc, "foods", doc, 
						new SmartChineseAnalyzer(Version.LUCENE_31, true));
				SearchResult searchResult = new SearchResult();
				searchResult.setRestaurantId(Long.valueOf(doc.get("id")));
				searchResult.setRestaurantName(doc.get("restaurant_name"));
				searchResult.setKey(key);
				searchResult.setFoods(Arrays.asList(highlighter.
						getBestFragment(tokenStream, doc.get("foods")).split(" ")));
				searchResults.add(searchResult);
			}
		} else {
			searchResults = null;
		}
		
		indexSearcher.close();
		directory.close();
		
		return new Gson().toJson(searchResults);
	}
	
	
}
