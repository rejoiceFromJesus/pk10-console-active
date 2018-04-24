package com.pk10.active.console.task;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.service.LotteryHistoryService;
import com.pk10.active.console.vo.IssueLotteryVo;

@Component
public class DailyLotteryTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DailyLotteryTask.class);
	
	@Autowired
	LotteryHistoryService lotteryHistoryService;
	@Autowired
	public RestTemplate restTemplate;

	//@Scheduled(cron="0/2 * * * * ?")
	@Scheduled(cron="0 0 1 * * ?")
	public void execute() {
		int retryCount = 10;
		LOGGER.info("DailyLotteryTask starts.....");
		Long startTime = System.currentTimeMillis();
		String lastDay = DateTime.now().minusDays(1).toString(Constant.DATE_FORMAT_PATTERN1);
		while(true) {
			if(retryCount <= 0) {
				LOGGER.error("try {} times, no scceed,end loop",retryCount);
				break;
			}
			try {
				String url = "https://www.cp333789.com/data/bjpk10/lotteryList/"+lastDay+".json?"+DateTime.now().getMillis();
				String result = restTemplate.getForObject(url, String.class);
				IssueLotteryVo[] issueLotteryVos = JsonUtil.toBean(result, IssueLotteryVo[].class);
				List<LotteryHistory> list = new ArrayList<LotteryHistory>();
				for(int i = issueLotteryVos.length-1; i >= 0; i--) {
					LotteryHistory lotteryHistory = new LotteryHistory();
					IssueLotteryVo issueLotteryVo = issueLotteryVos[i];
					String openNum = RejoiceUtil.toString(issueLotteryVo.getOpenNum());
					lotteryHistory.setOpenNum(openNum);
					lotteryHistory.setPeriod(issueLotteryVo.getIssue());
					lotteryHistory.setOpenDateTime(issueLotteryVo.getOpenDateTime());
					lotteryHistory.setOpenDate(issueLotteryVo.getOpenDateTime().substring(0, 10));
					String[] openNums = openNum.split(",");
					StringBuilder bigSmall = new StringBuilder();
					StringBuilder oddEven = new StringBuilder();
					StringBuilder dragonTiger = new StringBuilder();
					for(int j = 0; j < 10; j++) {
						oddEven.append(Integer.parseInt(openNums[j]) % 2 == 0? "2": "1").append(",");
						bigSmall.append(Integer.parseInt(openNums[j]) >= 6 ? "3":"4").append(",");
						dragonTiger.append(Integer.parseInt(openNums[j]) > Integer.parseInt(openNums[9-j])? "5":"6").append(",");
					}
					String bigSmallStr = bigSmall.substring(0, bigSmall.length()-1);
					String oddEvenStr = oddEven.substring(0, oddEven.length()-1);
					String dragonTigerStr = dragonTiger.substring(0, dragonTiger.length()-1);
					lotteryHistory.setBigSmall(bigSmallStr);
					lotteryHistory.setOddEven(oddEvenStr);
					int oneTwoSum = Integer.parseInt(openNums[0])+Integer.parseInt(openNums[1]);
					lotteryHistory.setOneTwoSum(oneTwoSum+","+(oneTwoSum % 2 == 0? "2": "1")+","+(oneTwoSum > 11 ? "3" : "4"));
					lotteryHistory.setTragonTiger(dragonTigerStr);
					list.add(lotteryHistory);
				}
				lotteryHistoryService.synchronizeLastDay(lastDay, list);
			
			} catch (Exception e) {
				LOGGER.warn("DailyLotteryTask fialed",e);
				LOGGER.warn("will try again....");
				retryCount--;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					LOGGER.error("sleep thread interrupted:",e);
				}
				continue;
			}
			break;
		}
		long costTime = System.currentTimeMillis()-startTime;
		String costTimeStr = costTime > 1000 ? costTime/1000.0+"秒":costTime+"毫秒";
		LOGGER.info("DailyLotteryTask cost time:{}", costTimeStr);
		LOGGER.info("DailyLotteryTask ends.....");
		
	}
}
