package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.service.CacheService;

@Api(tags="赔率模块")
@RestController
@RequestMapping("/client/rate")
public class RateController {

	
	@Autowired
	CacheService cacheService;
	
	@ApiOperation(value="两面反赔率列表", notes="返回冠亚和+第1~10名的单、双、大、小、龙、虎，rankName=排名，data[0].resultName=结果,data[0].rate=赔率)")
	@GetMapping("/rule-side")
	public List<Map<String,Object>> ruleSideList(){
		return cacheService.getRuleSideList();
	}
	
	@GetMapping("/rule-number")
	@ApiOperation(value="排名赔率列表", notes="返回冠亚和+第1~10名的结果或车道号码，rankName=排名,data[0].resultName=结果,data[0].rate=赔率")
	public List<Map<String,Object>> ruleNumberList(){
		return cacheService.getRuleNumberList();
	}
	
}
