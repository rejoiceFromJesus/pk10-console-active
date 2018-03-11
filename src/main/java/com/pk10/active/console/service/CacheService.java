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
import com.pk10.active.console.entity.RuleSide;


@Service
public class CacheService{

	@Autowired
	RuleSideService ruleSideService;
	
	@Cacheable("rule-side")
	public Map<String,List<RuleSide>> ruleSide(){
	
		return null;
	}
	
	@CachePut("rule-side")
	public Map<String,List<RuleSide>> refreshRuleSide() {
		Map<String,List<RuleSide>> result = new LinkedHashMap();
		RuleSide cons = new RuleSide();
		for(int i = 0; i <= 10; i++) {
			cons.setRank(i);
			List<RuleSide> list = ruleSideService.queryListByWhereAndOrder(cons, new String[]{"rank"}, new String[]{"asc"});
			result.put(Constant.RULE_TITLES[i], list);
		}
		
		return result;
	}

}
