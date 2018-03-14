package com.pk10.active.console.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pk10.active.console.entity.LotteryHistory;

@Service
public class LotteryHistoryService extends BaseService<LotteryHistory> {

	
	public void synchronizeLastDay(String lastDay,List<LotteryHistory> list){
		//1、delete last day
		LotteryHistory deleteCons = new LotteryHistory();
		deleteCons.setOpenDate(lastDay);
		this.getMapper().delete(deleteCons);
		//2、save list
		this.saveBatchSelective(list);
	}
}
