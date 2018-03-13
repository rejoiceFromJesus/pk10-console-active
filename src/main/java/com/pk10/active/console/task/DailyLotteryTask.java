package com.pk10.active.console.task;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.service.LotteryHistoryService;
import com.pk10.active.console.vo.IssueLotteryVo;

@Component
public class DailyLotteryTask {
	
	@Autowired
	LotteryHistoryService lotteryHistoryService;
	@Autowired
	public RestTemplate restTemplate;

	@Scheduled(cron="0/10 * 0-23 * * ?")
	public void execute() {
		String url = "https://www.cp111678.com/data/bjpk10/lotteryList/2018-03-13.json?a="+DateTime.now().getMillis();
		String result = restTemplate.getForObject(url, String.class);
		IssueLotteryVo[] issueLotteryVos = JsonUtil.toBean(result, IssueLotteryVo[].class);
		for(int i = issueLotteryVos.length-1; i >= 0; i--) {
			LotteryHistory lotteryHistory = new LotteryHistory();
			IssueLotteryVo issueLotteryVo = issueLotteryVos[i];
			String openNum = RejoiceUtil.toString(RejoiceUtil.toString(issueLotteryVo.getOpenNum()));
			lotteryHistory.setOpenNum(openNum);
			lotteryHistory.setPeriod(issueLotteryVo.getIssue());
			lotteryHistory.setOpenDateTime(issueLotteryVo.getOpenDateTime());
			lotteryHistory.setOpenDate(issueLotteryVo.getOpenDateTime().substring(0, 10));
			String[] openNums = openNum.split(",");
			StringBuilder bigSmall = new StringBuilder();
			StringBuilder oddEven = new StringBuilder();
			StringBuilder dragonTiger = new StringBuilder();
			for(int j = 0; j < 10; j++) {
				bigSmall.append(Integer.parseInt(openNums[j]) >= 6 ? "1":"0").append(",");
				oddEven.append(Integer.parseInt(openNums[j]) % 2 == 0? "0": "1").append(",");
				dragonTiger.append(Integer.parseInt(openNums[j]) > Integer.parseInt(openNums[9-j])? "1":"0").append(",");
			}
			String bigSmallStr = bigSmall.substring(0, bigSmall.length()-1);
			String oddEvenStr = oddEven.substring(0, oddEven.length()-1);
			String dragonTigerStr = dragonTiger.substring(0, dragonTiger.length()-1);
			lotteryHistory.setBigSmall(bigSmallStr);
			lotteryHistory.setOddEven(oddEvenStr);
			int oneTwoSum = Integer.parseInt(openNums[0])+Integer.parseInt(openNums[1]);
			lotteryHistory.setOneTwoSum(oneTwoSum+","+(oneTwoSum % 2 == 0? "0": "1")+","+(oneTwoSum > 11 ? "1" : "0"));
			lotteryHistory.setTragonTiger(dragonTigerStr);
			lotteryHistoryService.saveSelective(lotteryHistory);
		}
	}
}
