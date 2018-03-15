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
import org.springframework.web.client.RestTemplate;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.constant.SideNameEnum;
import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.entity.RuleNumber;
import com.pk10.active.console.entity.RuleSide;
import com.pk10.active.console.vo.CurrentPeriodLottery;


@Component
public class CacheService{

	@Autowired
	RuleSideService ruleSideService;
	
	@Autowired
	RuleNumberService ruleNumberService;
	
	@Autowired
	RestTemplate restTemplate;
	
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
	
	@Cacheable("current-period-lottery")
	public CurrentPeriodLottery getCurrentPeriodLottery(){
	
		return null;
	}
	
	@CachePut("current-period-lottery")
	public CurrentPeriodLottery refreshCurrentPeriodLottery() {
		String url = "https://www.cp111678.com/getLotteryBase.do?gameCode=bjpk10";
		String result = restTemplate.getForObject(url, String.class);
		CurrentPeriodLottery lottery = JsonUtil.toBean(result, CurrentPeriodLottery.class);
		List<Integer> openNum = lottery.getOpenNum();
		List<String> dragonTiger = new ArrayList<String>();
		for(int i = 0; i < 5; i++){
			dragonTiger.add(SideNameEnum.label(openNum.get(i) > openNum.get(9-i) ? 5:6));
		}
		lottery.setDragonTiger(dragonTiger);
		List<Object> oneTwoSum = new ArrayList<Object>();
		Integer ownTowSumInt = openNum.get(0)+openNum.get(1);
		oneTwoSum.add(openNum.get(0)+openNum.get(1));
		oneTwoSum.add(ownTowSumInt % 2 == 0 ? SideNameEnum.EVEN.label() : SideNameEnum.ODD.label());
		oneTwoSum.add(ownTowSumInt > 11 ? SideNameEnum.BIG.label() : SideNameEnum.SMALL.label());
		lottery.setOneTwoSum(oneTwoSum);
		return lottery;
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
