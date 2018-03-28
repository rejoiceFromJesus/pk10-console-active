package com.pk10.active.console.service;

import java.math.BigDecimal;
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
import com.pk10.active.console.common.constant.RuleTypeEnum;
import com.pk10.active.console.common.constant.SideNameEnum;
import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.entity.RuleNumber;
import com.pk10.active.console.entity.RuleSide;
import com.pk10.active.console.task.SettleBetRecordTask;
import com.pk10.active.console.vo.CurrentPeriodLottery;
import com.pk10.active.console.vo.IssueLotteryVo;


@Component
public class CacheService{

	@Autowired
	RuleSideService ruleSideService;
	
	@Autowired
	RuleNumberService ruleNumberService;
	
	@Autowired
	LotteryHistoryService lotteryHistoryService;
	
	@Autowired
	SettleBetRecordTask settleBetRecordTask;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Cacheable("rule-side")
	public List<Map<String,Object>> getRuleSideList(){
		return refreshRuleSideList();
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
	
		return refreshCurrentPeriodLottery();
	}
	
	@CachePut("current-period-lottery")
	public CurrentPeriodLottery refreshCurrentPeriodLottery() {
		String url = "https://www.cp333789.com/getLotteryBase.do?gameCode=bjpk10";
		String result = restTemplate.getForObject(url, String.class);
		System.err.println(result);
		CurrentPeriodLottery lottery = JsonUtil.toBean(result, CurrentPeriodLottery.class);
		List<Integer> openNum = lottery.getOpenNum();
		List<String> dragonTiger = new ArrayList<String>();
		for(int i = 0; i < 5; i++){
			dragonTiger.add(SideNameEnum.label(openNum.get(i) > openNum.get(9-i) ? 5:6));
		}
		lottery.setDragonTiger(dragonTiger);
		List<Object> oneTwoSum = new ArrayList<Object>();
		Integer oneTowSumInt = openNum.get(0)+openNum.get(1);
		oneTwoSum.add(openNum.get(0)+openNum.get(1));
		oneTwoSum.add(oneTowSumInt % 2 == 0 ? SideNameEnum.EVEN.label() : SideNameEnum.ODD.label());
		oneTwoSum.add(oneTowSumInt > 11 ? SideNameEnum.BIG.label() : SideNameEnum.SMALL.label());
		lottery.setOneTwoSum(oneTwoSum);
		
		//add lotteryHistory
		LotteryHistory cons = new LotteryHistory();
		cons.setPeriod(lottery.getPreIssue());
		cons.setOpenDate(RejoiceUtil.getDateStr2(lottery.getCurrentOpenDateTime()));
		LotteryHistory exists = lotteryHistoryService.queryOne(cons);
		if(null == exists){
			LotteryHistory lotteryHistory = new LotteryHistory();
			String openNumStr = RejoiceUtil.toString(lottery.getOpenNum());
			lotteryHistory.setOpenNum(openNumStr);
			lotteryHistory.setPeriod(lottery.getPreIssue());
			lotteryHistory.setOpenDateTime(RejoiceUtil.getDateStr3(lottery.getCurrentOpenDateTime()));
			lotteryHistory.setOpenDate(lotteryHistory.getOpenDateTime().substring(0, 10));
			String[] openNums = openNumStr.split(",");
			StringBuilder bigSmall = new StringBuilder();
			StringBuilder oddEven = new StringBuilder();
			StringBuilder dragonTigerSB = new StringBuilder();
			for(int j = 0; j < 10; j++) {
				oddEven.append(Integer.parseInt(openNums[j]) % 2 == 0? "2": "1").append(",");
				bigSmall.append(Integer.parseInt(openNums[j]) >= 6 ? "3":"4").append(",");
				dragonTigerSB.append(Integer.parseInt(openNums[j]) > Integer.parseInt(openNums[9-j])? "5":"6").append(",");
			}
			String bigSmallStr = bigSmall.substring(0, bigSmall.length()-1);
			String oddEvenStr = oddEven.substring(0, oddEven.length()-1);
			String dragonTigerStr = dragonTigerSB.substring(0, dragonTigerSB.length()-1);
			lotteryHistory.setBigSmall(bigSmallStr);
			lotteryHistory.setOddEven(oddEvenStr);
			oneTowSumInt = Integer.parseInt(openNums[0])+Integer.parseInt(openNums[1]);
			lotteryHistory.setOneTwoSum(oneTowSumInt+","+(oneTowSumInt % 2 == 0? "2": "1")+","+(oneTowSumInt > 11 ? "3" : "4"));
			lotteryHistory.setTragonTiger(dragonTigerStr);
			lotteryHistoryService.saveSelective(lotteryHistory);
			
			//do one settle
			settleBetRecordTask.execute();
		}
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
		return refreshRuleNumberList();
	}
	
	
	@Cacheable("rates-map")
	public Map<String,BigDecimal> getRatesMap() {
		return refreshRatesMap();
	}
	
	@CachePut("rates-map")
	public Map<String,BigDecimal> refreshRatesMap() {
		Map<String,BigDecimal> ratesMap = new HashMap<>();
		List<Map<String, Object>> ruleNumberList = this.getRuleNumberList();
		ruleNumberList.forEach(item -> {
			List<RuleNumber> ruleNumbers = (List<RuleNumber>) item.get("data");
			ruleNumbers.forEach(ruleNumber -> {
				ratesMap.put(RuleTypeEnum.NUMBER.value()+"-"+ruleNumber.getRank()+"-"+ruleNumber.getResult(), ruleNumber.getRate());
			});
		});
		List<Map<String, Object>> ruleSideList = this.getRuleSideList();
		ruleSideList.forEach(item -> {
			List<RuleSide> ruleSides = (List<RuleSide>) item.get("data");
			ruleSides.forEach(ruleSide -> {
				ratesMap.put(RuleTypeEnum.SIDE.value()+"-"+ruleSide.getRank()+"-"+ruleSide.getResult(), ruleSide.getRate());
			});
		});
		return ratesMap;
	}



}
