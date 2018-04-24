/**
                                                                                                                                                                                                                                                                         0000000000
                                                                                                                                                                                                                                                                         00000000 * 系统项目名称
 * com.pk10.active.console.task
 * SettleBetRecordTask.java
 * 
 * 2018年3月27日-下午12:38:24
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.BetRecord;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.service.BetRecordService;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.LotteryHistoryService;

/**
 *
 * SettleBetRecordTask
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月27日 下午12:38:24
 * 
 * @version 1.0.0
 *
 */
@Component
@EnableAsync
public class SettleBetRecordTask {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(SettleBetRecordTask.class);

	public static final Integer pageSize = 200;

	@Autowired
	CacheService cacheService;

	@Autowired
	BetRecordService betRecordService;

	@Autowired
	LotteryHistoryService lotteryHistoryService;

	//@Scheduled(fixedRate=2000)
	@Scheduled(cron = "0 30 1 * * ?")
	public void execute() {
		LOGGER.info("SettleBetRecordTask starts =======================");
		long startTime = System.currentTimeMillis();
		Map<String, LotteryHistory> lotteryHistoryMap = new HashMap<String, LotteryHistory>();
		int pageNum = 1;
		BetRecord betRecordCons = new BetRecord();
		betRecordCons.setIsOpen(false);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			PageInfo<BetRecord> betRecordPageInfo = betRecordService
					.queryListByPageAndOrder(betRecordCons, pageNum, pageSize,
							null);
			for (BetRecord betRecord : betRecordPageInfo.getList()) {
				try {

					if (lotteryHistoryMap.get(betRecord.getPeriod().toString()) == null) {
						LotteryHistory lotteryHistoryCons = new LotteryHistory();
						lotteryHistoryCons.setPeriod(betRecord.getPeriod());
						LotteryHistory lotteryHistory = lotteryHistoryService
								.queryOne(lotteryHistoryCons);
						if (lotteryHistory == null) {
							continue;
						}
						lotteryHistoryMap.put(betRecord.getPeriod().toString(), lotteryHistory);
					}
					betRecordService.settle(betRecord,lotteryHistoryMap.get(betRecord.getPeriod().toString()));
				} catch (Exception e) {
					LOGGER.warn("settle betRecord failed :",e);
				}
			}
			if (betRecordPageInfo.getSize() < pageSize) {
				break;
			}
			pageNum++;
		}
		long costTime = System.currentTimeMillis()-startTime;
		String costTimeStr = costTime > 1000 ? costTime/1000.0+"秒":costTime+"毫秒";
		LOGGER.info("SettleBetRecordTask costs time:"+costTimeStr+"=======================");
		LOGGER.info("SettleBetRecordTask ends =======================");
	}

}
