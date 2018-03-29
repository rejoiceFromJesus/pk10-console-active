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
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.TradeTypeEnum;
import com.pk10.active.console.entity.TradeRecord;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.service.BetRecordService;
import com.pk10.active.console.service.CacheService;
import com.pk10.active.console.service.TradeRecordService;
import com.pk10.active.console.vo.BetVo;
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
@RequestMapping("/client/trade")
@Api(tags="交易模块")
public class TradeController {
	
	@Autowired
	TradeRecordService tradeRecordService;
	
	@Autowired
	CacheService cacheService;
	
	@ApiOperation(value = "交易记录", notes = "只返回最新50条")
	@GetMapping("/recent")
	public Result<List<TradeRecord>> recentList(HttpSession session){
		TradeRecord cons = new TradeRecord();
		cons.setMobile("1234");
		PageInfo<TradeRecord>  tradePageInfo = tradeRecordService.queryListByPageAndOrder(cons, 1, 50, "trade_time desc");
		return Result.success(tradePageInfo.getList());
	}
	
	@ApiOperation(value = "提现记录", notes = "只返回最新50条")
	@GetMapping("/withdraw/recent")
	public Result<List<TradeRecord>> recentWithdrawList(HttpSession session){
		TradeRecord cons = new TradeRecord();
		cons.setMobile("1234");
		cons.setType(TradeTypeEnum.WITHDRAW.value());
		PageInfo<TradeRecord>  tradePageInfo = tradeRecordService.queryListByPageAndOrder(cons, 1, 50, "trade_time desc");
		return Result.success(tradePageInfo.getList());
	}
	
	
}
