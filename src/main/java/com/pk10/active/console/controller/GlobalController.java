package com.pk10.active.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.service.GlobalService;
import com.pk10.active.console.vo.UpdateRateVo;

@RestController
@RequestMapping("/global")
public class GlobalController {
	
	@Autowired
	GlobalService globalService;
	
	@PutMapping("/update-rate")
	public Result<Boolean> updateRate(@RequestBody UpdateRateVo updateRateVo){
		globalService.updateRate(updateRateVo);
		return Result.success(true);
	}
	
}
