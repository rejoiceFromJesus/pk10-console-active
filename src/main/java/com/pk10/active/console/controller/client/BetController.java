/**
 * com.pk10.active.console.controller.client
 * 系统项目名称
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

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.CodeMsg;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.service.BetRecordService;
import com.pk10.active.console.service.CacheService;
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
@RequestMapping("/client/bet")
@Api(tags="投注模块")
public class BetController {
	
	@Autowired
	BetRecordService betRecordService;
	
	@Autowired
	CacheService cacheService;
	
	@ApiOperation(value = "投注", notes = "可以一次性投多注" )
	@PostMapping
	public Result<User> bet(@RequestBody BetVo betVo, HttpSession session){
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		if(betVo.getBetList().size() <= 0){
			return Result.paramError("投注的注数必须大于0");
		}
	
		CurrentPeriodLottery currentPeriodLottery = cacheService.getCurrentPeriodLottery();
		if(currentPeriodLottery == null){
			return Result.error(CodeMsg.SERVER_ERROR);
		}
		if(!currentPeriodLottery.getIssue().equals(betVo.getPeriod())){
			return Result.paramError("投注的期数不正确，请刷新重试，投注期数为："+betVo.getPeriod()+",当前期数为："+currentPeriodLottery.getIssue());
		}
		long now = DateTime.now().getMillis();
		if((new DateTime(currentPeriodLottery.getOpenDateTime()).getMillis() - now )/1000 < 10 ){
			return Result.error(CodeMsg.BET_CLOSED);
		}
		user = betRecordService.bet(betVo,user);
		return Result.success(user);
	}
	
	
}
