package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

@Table(name="recharge_record")
public class RechargeRecord extends BaseEntity{
	
	private String mobile;
	private BigDecimal money;
	private String tradeTime;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	
	
}
