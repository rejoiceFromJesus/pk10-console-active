package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

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

@Api(tags="赔率模块")
@RestController
@RequestMapping("/client/rate")
public class RateController {

	
	@Autowired
	CacheService cacheService;
	@ApiOperation(value="两面反赔率列表", notes="返回冠亚和+第1~10名的单、双、大、小、龙、虎（sideName，页面展示），分别对应1,2,3,4,5,6（side，传给后端）")
	@GetMapping("/rule-side")
	public List<Map<String,Object>> ruleSideList(){
		return cacheService.getRuleSideList();
	}
	
	@GetMapping("/rule-number")
	@ApiOperation(value="排名赔率列表", notes="返回冠亚和+第1~10名的结果或车道号码（number）")
	public List<Map<String,Object>> ruleNumberList(){
		return cacheService.getRuleNumberList();
	}
	
}
