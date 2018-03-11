package com.pk10.active.console.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import com.pk10.active.console.service.CacheService;

@Component
public class ApplicationStartup 
implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	CacheService cacheService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.err.println("init cache.....");
		cacheService.refreshRuleSide();
	}

 
 
} 