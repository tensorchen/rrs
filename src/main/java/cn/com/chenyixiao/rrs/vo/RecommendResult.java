package cn.com.chenyixiao.rrs.vo;

import java.util.List;

public class RecommendResult {

	private Long userId;
	private List<RecommendResultVO> recommendResultVOs;
	
	
	
	
	public RecommendResult() {
		super();
	}

	public RecommendResult(Long userId, List<RecommendResultVO> recommendResultVOs) {
		super();
		this.userId = userId;
		this.recommendResultVOs = recommendResultVOs;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<RecommendResultVO> getRecommendResultVOs() {
		return recommendResultVOs;
	}
	public void setRecommendResultVOs(List<RecommendResultVO> recommendResultVOs) {
		this.recommendResultVOs = recommendResultVOs;
	}
	
	
}
