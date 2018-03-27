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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pk10.active.console.service.CacheService;

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
public class SettleBetRecordTask {
	
	@Autowired
	CacheService cacheService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void execute() {
		cacheService.refreshCurrentPeriodLottery();
	}

}
