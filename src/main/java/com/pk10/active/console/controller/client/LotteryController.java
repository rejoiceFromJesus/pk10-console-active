/**
 * 系统项目名称
 * com.pk10.active.console.controller.client
 * BetController.java
 * 
 * 2018年3月14日-上午9:45:01
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.LotteryHistoryService;
import com.pk10.active.console.vo.CurrentPeriodLottery;


/**
 *
 * BetController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月14日 上午9:45:01
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/client/lottery")
@Api(tags="开奖模块")
public class LotteryController {
	
	@Autowired
	CacheService cacheService;
	
	@Autowired
	LotteryHistoryService lotteryHistoryService;
	
	@ApiOperation(value="最新一期开奖结果", notes="返回最新一期的开奖结果信息")
	@GetMapping("/current-period")
	public Result<CurrentPeriodLottery> currentPeriod(){
		CurrentPeriodLottery currentPeriodLottery = cacheService.getCurrentPeriodLottery();
		if(currentPeriodLottery != null){
			currentPeriodLottery.setServerTime(new Date());
		}
		return Result.success(currentPeriodLottery);
	}
	@ApiOperation(value="开奖历史", notes="返回指定日期所有开奖历史")
	@GetMapping("/history/{currentDay}")
	public Result<List<LotteryHistory>> currentDayHistory(@PathVariable("currentDay") String currentDay){
		LotteryHistory cons = new LotteryHistory();
		cons.setOpenDate(currentDay);
		List<LotteryHistory> list = lotteryHistoryService.queryListByWhereAndOrder(cons, new String[]{"period"}, new String[]{"desc"});
		return Result.success(list);
	}
}
