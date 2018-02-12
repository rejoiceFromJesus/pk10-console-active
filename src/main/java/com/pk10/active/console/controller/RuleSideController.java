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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="两面反模块")
@RestController
@RequestMapping("/rule-side")
public class RuleSideController extends BaseController<RuleSide, RuleSideService> {

	@GetMapping("/client/list-by-rank")
	@ApiOperation(value="两面反赔率列表", notes="返回冠亚和+1~10名的大小、龙虎、单双，其中分别对应true和false")
	public Result<Map<Integer,List<RuleSide>>> listByRank(){
		Map<Integer,List<RuleSide>> data = new HashMap<>();
		RuleSide ruleSideCons = new RuleSide();
		for(int i = 0; i <= 10; i++) {
			ruleSideCons.setRank(i);
			data.put(i, this.getService().queryListByWhereOrderByClause(ruleSideCons," field(side_name,'大','小','单','双','龙','虎')"));
		}
		return Result.success(data);
	}
}
