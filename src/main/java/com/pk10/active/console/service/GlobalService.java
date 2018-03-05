package com.pk10.active.console.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pk10.active.console.vo.UpdateRateVo;

@Service
@Transactional
public class GlobalService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public void updateRate(UpdateRateVo updateRateVo) {
		switch(updateRateVo.getType()) {
		case 1:
			//1~10排名
			jdbcTemplate.update("update rule_number set rate="+updateRateVo.getRate().doubleValue() +" where rank!=0");
			break;
		case 2:
			//1~10两面反
			jdbcTemplate.update("update rule_side set rate="+updateRateVo.getRate().doubleValue() +" where rank!=0");
			break;
		case 3:
			//冠亚和结果
			jdbcTemplate.update("update rule_number set rate="+updateRateVo.getRate().doubleValue() +" where rank=0");
			break;
		case 4:
			//冠亚和两面反
			jdbcTemplate.update("update rule_side set rate="+updateRateVo.getRate().doubleValue() +" where rank=0");
			break;
		}
		
	}
}
