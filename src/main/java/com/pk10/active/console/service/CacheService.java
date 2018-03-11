package com.pk10.active.console.service;

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
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.RuleNumber;
import com.pk10.active.console.entity.RuleSide;


@Service
public class CacheService{

	@Autowired
	RuleSideService ruleSideService;
	
	@Autowired
	RuleNumberService ruleNumberService;
	
	@Cacheable("rule-side")
	public Map<String,List<RuleSide>> getRuleSideList(){
	
		return null;
	}
	
	@CachePut("rule-side")
	public Map<String,List<RuleSide>> refreshRuleSideList() {
		Map<String,List<RuleSide>> result = new LinkedHashMap<String, List<RuleSide>>();
		RuleSide cons = new RuleSide();
		for(int i = 0; i <= 10; i++) {
			cons.setRank(i);
			List<RuleSide> list = ruleSideService.queryListByWhereAndOrder(cons, new String[]{"rank"}, new String[]{"asc"});
			result.put(Constant.RULE_TITLES[i], list);
		}
		
		return result;
	}
	
	@CachePut("rule-number")
	public Map<String,List<RuleNumber>> refreshRuleNumberList() {
		Map<String,List<RuleNumber>> result = new LinkedHashMap<String, List<RuleNumber>>();
		RuleNumber cons = new RuleNumber();
		for(int i = 0; i <= 10; i++) {
			cons.setRank(i);
			List<RuleNumber> list = ruleNumberService.queryListByWhereAndOrder(cons, new String[]{"rank"}, new String[]{"asc"});
			result.put(Constant.RULE_TITLES[i], list);
		}
		
		return result;
	}

	@Cacheable("rule-number")
	public Map<String, List<RuleSide>> getRuleNumberList() {
		return null;
	}

}
