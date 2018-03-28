package com.pk10.active.console.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.constant.RuleTypeEnum;
import com.pk10.active.console.common.constant.TradeTypeEnum;
import com.pk10.active.console.common.util.Arith;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.BetInfo;
import com.pk10.active.console.entity.BetRecord;
import com.pk10.active.console.entity.LotteryHistory;
import com.pk10.active.console.entity.TradeRecord;
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
		BigDecimal balance = user.getBalance().subtract(moneySum);
		//2、BetRecord
		BetRecord betRecord = new BetRecord();
		betRecord.setBetTime(currentTime);
		betRecord.setIsOpen(false);
		betRecord.setMobile(user.getMobile());
		betRecord.setBetMoney(moneySum);
		betRecord.setOpenTime(RejoiceUtil.getDateStr3(cacheService.getCurrentPeriodLottery().getOpenDateTime()));
		betRecord.setPeriod(betVo.getPeriod());
		betRecordService.saveSelective(betRecord);
		//3、tradeRecord
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setBalance(balance);
		tradeRecord.setBetTime(betRecord.getBetTime());
		tradeRecord.setMobile(betRecord.getMobile());
		tradeRecord.setMoney(betRecord.getBetMoney().multiply(new BigDecimal(-1)));
		tradeRecord.setPeriod(betRecord.getPeriod());
		tradeRecord.setTradeTime(tradeRecord.getBetTime());
		tradeRecord.setType(TradeTypeEnum.BET.value());
		tradeRecord.setRemark(TradeTypeEnum.BET.label());
		tradeRecordService.saveSelective(tradeRecord);
		//4、update user
		User newUser = new User();
		newUser.setMobile(user.getMobile());
		newUser.setBalance(balance);
		userService.updateByMobileSelective(newUser);
	}


	/**
	 * settle(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param betRecord
	 * void
	 * @param lotteryHistory 
	*/
	public void settle(BetRecord betRecord, LotteryHistory lotteryHistory) {
		User userCons = new User();
		userCons.setMobile(betRecord.getMobile());
		User user = userService.queryOne(userCons);
		//1、betInfo
		settleBetInfos(betRecord, lotteryHistory);
		//2、betRecord
		betRecord.setUpdateTime(null);
		betRecord.setIsOpen(true);
		betRecordService.updateByIdSelective(betRecord);
		user.setBalance(user.getBalance().add(betRecord.getBonus()));
		//3、tradeRecord
		settleTradeRecord(betRecord,user);
		//4、User
		User newUser = new User();
		newUser.setMobile(user.getMobile());
		newUser.setBalance(user.getBalance());
		userService.updateByMobileSelective(newUser);
		
	}


	/**
	 * settleTradeRecord(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param betRecord
	 * void
	*/
	protected void settleTradeRecord(BetRecord betRecord,User user) {
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setBalance(user.getBalance());
		tradeRecord.setMobile(betRecord.getMobile());
		tradeRecord.setMoney(betRecord.getBonus());
		tradeRecord.setBetTime(betRecord.getBetTime());
		tradeRecord.setTradeTime(DateTime.now().toString(Constant.DATE_FORMAT_PATTERN2));
		tradeRecord.setPeriod(betRecord.getPeriod());
		tradeRecord.setType(TradeTypeEnum.SETTLE.value());
		tradeRecordService.saveSelective(tradeRecord);
	}


	/**
	 * settleBetInfos(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param betRecord
	 * @param lotteryHistory
	 * void
	*/
	protected void settleBetInfos(BetRecord betRecord,
			LotteryHistory lotteryHistory) {
		BigDecimal bonusSum = new BigDecimal(0);
		BetInfo betInfoCons = new BetInfo();
		betInfoCons.setPeriod(betRecord.getPeriod());
		betInfoCons.setMobile(betRecord.getMobile());
		int [] oneToSum = RejoiceUtil.parseString2IntArray(lotteryHistory.getOneTwoSum().split(","));
		int [] openNum = RejoiceUtil.parseString2IntArray(lotteryHistory.getOpenNum().split(","));
		int [] oddEvent = RejoiceUtil.parseString2IntArray(lotteryHistory.getOddEven().split(","));
		int [] bigSmall = RejoiceUtil.parseString2IntArray(lotteryHistory.getBigSmall().split(","));
		int [] tragonTiger = RejoiceUtil.parseString2IntArray(lotteryHistory.getTragonTiger().split(","));
		List<BetInfo> betInfoList = betInfoService.queryListByWhere(betInfoCons);
		for (BetInfo betInfo : betInfoList) {
			betInfo.setUpdateTime(null);
			betInfo.setBonus(new BigDecimal(0));
			if(RuleTypeEnum.NUMBER.value().equals(betInfo.getType())){
				//排名
				if(betInfo.getRank() == 0){
					//冠亚和
					betInfo.setLuckyResult(oneToSum[0]);
					if(betInfo.getResult().equals(betInfo.getLuckyResult())){
						//中奖
						betInfo.setBonus(betInfo.getMoney().multiply(betInfo.getRate()));
						bonusSum = bonusSum.add(betInfo.getBonus());
					}
				}else{
					//排名
					betInfo.setLuckyResult(openNum[betInfo.getRank()-1]);
					if(betInfo.getResult().equals(betInfo.getLuckyResult())){
						betInfo.setBonus(betInfo.getMoney().multiply(betInfo.getRate()));
						bonusSum = bonusSum.add(betInfo.getBonus());
					}
				}
			}else{
				//两面反
				if(betInfo.getRank() == 0){
					//冠亚和
					if(betInfo.getResult() >= 1 && betInfo.getResult() <= 2){
						betInfo.setLuckyResult(oneToSum[1]);
					}else{
						betInfo.setLuckyResult(oneToSum[2]);
					}
					if(betInfo.getResult().equals(betInfo.getLuckyResult())){
						//中奖
						betInfo.setBonus(betInfo.getMoney().multiply(betInfo.getRate()));
						bonusSum = bonusSum.add(betInfo.getBonus());
					}
				}else{
					//排名
					if(betInfo.getResult() >= 1 && betInfo.getResult() <= 2){
						betInfo.setLuckyResult(oddEvent[betInfo.getRank()-1]);
					}else if(betInfo.getResult() >= 3 && betInfo.getResult() <= 4){
						betInfo.setLuckyResult(bigSmall[betInfo.getRank()-1]);
					} else{
						betInfo.setLuckyResult(tragonTiger[betInfo.getRank()-1]);
					}
					betInfo.setLuckyResult(openNum[betInfo.getRank()-1]);
					if(betInfo.getResult().equals(betInfo.getLuckyResult())){
						betInfo.setBonus(betInfo.getMoney().multiply(betInfo.getRate()));
						bonusSum = bonusSum.add(betInfo.getBonus());
					}
				}
			}
			betInfoService.updateByIdSelective(betInfo);
		}
		
		betRecord.setBonus(bonusSum);
	}



}
