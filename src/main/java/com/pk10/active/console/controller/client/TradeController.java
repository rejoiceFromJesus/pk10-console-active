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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.BetInfo;
import com.pk10.active.console.entity.TradeRecord;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.service.BetInfoService;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.TradeRecordService;


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
@RequestMapping("/client/trade")
@Api(tags="交易模块")
public class TradeController { 
	
	@Autowired
	TradeRecordService tradeRecordService;
	
	@Autowired
	CacheService cacheService;
	
	@Autowired
	BetInfoService betInfoService;
	
	@ApiOperation(value = "交易记录", notes = "只返回最新50条")
	@GetMapping("/recent")
	public Result<List<TradeRecord>> recentList(HttpSession session){
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		TradeRecord cons = new TradeRecord();
		cons.setMobile(user.getMobile());
		PageInfo<TradeRecord>  tradePageInfo = tradeRecordService.queryListByPageAndOrder(cons, 1, 50, "trade_time desc");
		return Result.success(tradePageInfo.getList());
	}
	
	@ApiOperation(value = "投注详情", notes = "参数为：period、betTime", responseContainer = "List")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="period",value="期数",dataType="int",paramType="query"),
			@ApiImplicitParam(name="betTime",value="投注时间",paramType="query")})
	@GetMapping("/bet-info")
	public Result<List<BetInfo>> betInfoList(HttpSession session,@RequestParam("period") Integer period, @RequestParam("betTime") String betTime){
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		BetInfo cons = new BetInfo();
		cons.setBetTime(betTime);
		cons.setPeriod(period);
		cons.setMobile(user.getMobile());
		List<BetInfo> betInfoList = betInfoService.queryListByWhere(cons);
		return Result.success(betInfoList);
	}
	
	
	
}
