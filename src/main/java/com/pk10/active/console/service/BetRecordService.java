package com.pk10.active.console.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.util.Arith;
import com.pk10.active.console.common.util.RejoiceUtil;
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
	BetRecordService betRecordService;
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
		Map<String,BigDecimal> ratesMap = cacheService.getRatesMap();
		BigDecimal moneySum = new BigDecimal(0);
		//1、BetInfo
		List<BetInfo> betInfoList = new ArrayList<>();
		for(Bet bet: betVo.getBetList()){
			BetInfo betInfo = new BetInfo();
			betInfo.setBetTime(currentTime);
			betInfo.setResult(bet.getResult());
			betInfo.setMobile(user.getMobile());
			betInfo.setPeriod(betVo.getPeriod());
			betInfo.setType(betVo.getType());
			betInfo.setRank(bet.getRank());
			betInfo.setRate(ratesMap.get(betVo.getType()+"-"+bet.getRank()+"-"+bet.getResult()));
			betInfo.setMoney(bet.getMoney());
			betInfoList.add(betInfo);
			moneySum = Arith.addBigDecimal(moneySum, betInfo.getMoney());
		}
		betInfoService.saveBatchSelective(betInfoList);
		//2、BetRecord
		BetRecord betRecord = new BetRecord();
		betRecord.setBalance(user.getBalance().subtract(moneySum));
		betRecord.setBetTime(currentTime);
		betRecord.setIsOpen(false);
		betRecord.setMobile(user.getMobile());
		betRecord.setMoney(user.getBalance());
		betRecord.setBetMoney(moneySum);
		betRecord.setOpenTime(RejoiceUtil.getDateStr3(cacheService.getCurrentPeriodLottery().getOpenDateTime()));
		betRecord.setPeriod(betVo.getPeriod());
		betRecordService.saveSelective(betRecord);
		
	}



}
