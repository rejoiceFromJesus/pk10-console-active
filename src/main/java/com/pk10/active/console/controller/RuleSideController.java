package com.pk10.active.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.RuleSide;
import com.pk10.active.console.service.RuleSideService;

@RestController
@RequestMapping("/rule-side")
public class RuleSideController extends BaseController<RuleSide, RuleSideService> {

	@GetMapping("/client/list-by-rank")
	public Result<Map<Integer,List<RuleSide>>> listByRank(){
		Map<Integer,List<RuleSide>> data = new HashMap<>();
		RuleSide ruleSideCons = new RuleSide();
		for(int i = 0; i <= 10; i++) {
			ruleSideCons.setRank(i);
			data.put(i, this.getService().queryListByWhereOrderByClause(ruleSideCons,"side"));
		}
		return Result.success(data);
	}
}
