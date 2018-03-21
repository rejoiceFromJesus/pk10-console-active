package com.pk10.active.console.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.zxing.common.StringUtils;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.entity.RuleNumber;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.RuleNumberService;
import com.pk10.active.console.service.RuleSideService;

@Aspect
@Component
public class RefreshCacheAspect {
	
	public final static String[] CLASS_NAMES = {
		RuleSideService.class.getSimpleName(),
		RuleNumberService.class.getSimpleName()};

	@Autowired
	CacheService cacheService;
	
	
	
	
	/* @AfterReturning("@annotation(com.pk10.active.console.annotation.RefreshCache)")  
    public void refreshCache(JoinPoint joinPoint) {
    	System.err.println("11111111111");
    	cacheService.refreshRuleSideList();
    	cacheService.refreshRuleNumberList();
    }*/
	 

	@AfterReturning("execution(* com.pk10.active.console.service.BaseService+.*(..))")  
    public void refreshCache2(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		if(RejoiceUtil.contains(CLASS_NAMES, className)) {
			if(!method.startsWith("query")) {
				if(className.equals(CLASS_NAMES[0])) {
					cacheService.refreshRuleSideList();
				}else if(className.equals(CLASS_NAMES[1])) {
					cacheService.refreshRuleNumberList();
				}
				
			}
		}
    	
    }

}