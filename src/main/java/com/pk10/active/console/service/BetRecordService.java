package com.pk10.active.console.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.BetInfo;
import com.pk10.active.console.entity.BetRecord;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.vo.Bet;
import com.pk10.active.console.vo.BetVo;

@Service
public class BetRecordService extends BaseService<BetRecord> {
	@Autowired
	BetInfoService betInfoService;
	@Autowired
	TradeRecordService tradeRecordService;
	@Autowired
	UserService userService;
	@Autowired
	CacheService cacheService;
	

	/**
	 * betList(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param list
	 * @param user
	 * void
	*/
	public void bet(BetVo betVo, User user) {
		String currentTime = DateTime.now().toString(Constant.DATE_FORMAT_PATTERN2);
		//1、BetInfo
		for(Bet bet: betVo.getBetList()){
			BetInfo betInfo = new BetInfo();
			betInfo.setBetTime(currentTime);
			betInfo.setResult(bet.getResult());
			betInfo.setMobile(user.getMobile());
			betInfo.setPeriod(betVo.getPeriod());
		}
		//2、BetRecord
		
	}



}
