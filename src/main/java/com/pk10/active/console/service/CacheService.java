package com.pk10.active.console.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.RuleNumber;
import com.pk10.active.console.entity.RuleSide;


@Component
public class CacheService{

	@Autowired
	RuleSideService ruleSideService;
	
	@Autowired
	RuleNumberService ruleNumberService;
	
	@Cacheable("rule-side")
	public List<Map<String,Object>> getRuleSideList(){
	
		return null;
	}
	
	@CachePut("rule-side")
	public List<Map<String, Object>> refreshRuleSideList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		RuleSide cons = new RuleSide();
		Map<String,Object> singleRank = null;
		for(int i = 0; i <= 10; i++) {
			cons.setRank(i);
			List<RuleSide> list = ruleSideService.queryListByWhereAndOrder(cons, new String[]{"rank"}, new String[]{"asc"});
			singleRank =  new HashMap<String, Object>();
			singleRank.put("rankName", Constant.RULE_TITLES[i]);
			singleRank.put("data", list);
			result.add(singleRank);
		}
		
		return result;
	}
	
	@CachePut("rule-number")
	public List<Map<String,Object>> refreshRuleNumberList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		RuleNumber cons = new RuleNumber();
		Map<String,Object> singleRank = null;
		for(int i = 0; i <= 10; i++) {
			cons.setRank(i);
			List<RuleNumber> list = ruleNumberService.queryListByWhereAndOrder(cons, new String[]{"rank"}, new String[]{"asc"});
			singleRank =  new HashMap<String, Object>();
			singleRank.put("rankName", Constant.RULE_TITLES[i]);
			singleRank.put("data", list);
			result.add(singleRank);
		}
		
		return result;
	}

	@Cacheable("rule-number")
	public List<Map<String,Object>> getRuleNumberList() {
		return null;
	}

}
