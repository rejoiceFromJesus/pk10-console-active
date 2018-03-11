package com.pk10.active.console.controller.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.entity.RuleSide;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.RuleSideService;

@RestController
@RequestMapping("/client/rate")
public class RateController {

	
	@Autowired
	CacheService cacheService;
	
	@GetMapping("/rule-side")
	public Map<String,List<RuleSide>> ruleSideList(){
		return cacheService.ruleSide();
	}
	
	@GetMapping("/refresh-rule-side")
	public Map<String,List<RuleSide>> refreshRuleSideList(){
		 return cacheService.refreshRuleSide();
	}
}
