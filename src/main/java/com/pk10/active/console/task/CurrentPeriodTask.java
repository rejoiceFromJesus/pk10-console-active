package com.pk10.active.console.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pk10.active.console.service.CacheService;

@Component
public class CurrentPeriodTask {

	@Autowired
	CacheService cacheService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void execute() {
		cacheService.refreshCurrentPeriodLottery();
	}
}
