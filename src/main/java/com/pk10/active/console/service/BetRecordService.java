package com.pk10.active.console.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.entity.BetInfo;
import com.pk10.active.console.entity.BetRecord;
import com.pk10.active.console.entity.User;
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
	 * @param betVoList
	 * @param user
	 * void
	*/
	public void betList(List<BetVo> betVoList, User user) {
		/*String currentTime = DateTime.now().toString(Constant.DATE_FORMAT_PATTERN2));
		if(cacheService.get)
		//1、BetInfo
		for(BetVo betVo: betVoList){
			BetInfo betInfo = new BetInfo();
			betInfo.setBetTime(currentTime);
			betInfo.setLuckyNumber(betVo.getLuckyNumber());
			betInfo.setMobile(user.getMobile());
			betInfo.setPeriod();
		}*/
		//2、BetRecord
		
	}



}
