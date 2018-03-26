/**
 * 系统项目名称
 * com.pk10.active.console.service
 * UserService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.constant.Constant;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.RechargeRecord;
import com.pk10.active.console.entity.TradeRecord;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.mapper.UserMapper;


/**
 *
 * UserService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class UserService extends BaseService<User> {
	
	@Autowired
	RechargeRecordService rechargeRecordService;
	@Autowired
	TradeRecordService tradeRecordService;
	@Autowired
	UserMapper userMapper;

	
	public void updateByMobileSelective(User user) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("mobile", user.getMobile());
		user.setMobile(null);
		userMapper.updateByExampleSelective(user, example);
	}
	
	/* (non-Javadoc)
	 * @see com.pk10.active.console.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(User user) throws Exception {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		super.save(user);
	}
	

	public void recharge(String mobile, BigDecimal money) {
		User userCons = new User();
		userCons.setMobile(mobile);
		User user = userMapper.selectOne(userCons);
		User updateUser = new User();
		updateUser.setId(user.getId());
		updateUser.setBalance(user.getBalance().add(money));
		this.updateByIdSelective(updateUser);
		//add rechargeRecord
		RechargeRecord rechargeRecord = new RechargeRecord();
		rechargeRecord.setMobile(mobile);
		rechargeRecord.setMoney(money);
		rechargeRecord.setRemark("充值");
		rechargeRecord.setTradeTime(DateTime.now().toString(Constant.DATE_FORMAT_PATTERN2));
		rechargeRecordService.saveSelective(rechargeRecord);
		//add TradeRecord
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setMobile(mobile);
		tradeRecord.setMoney(money);
		tradeRecord.setRemark("充值");
		tradeRecord.setTradeTime(rechargeRecord.getTradeTime());
		tradeRecord.setType(TradeRecord.TYPE_RECHARGE);
		tradeRecord.setBalance(updateUser.getBalance());
		tradeRecordService.saveSelective(tradeRecord);
	} 
	 
}