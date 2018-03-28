package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

@Table(name="trade_record")
public class TradeRecord extends BaseEntity {

	private String mobile;
	private BigDecimal money;
	private Integer period;
	private BigDecimal balance;
	private String tradeTime;
	private Integer type;
	public static final Integer TYPE_RECHARGE = 1;
	public static final Integer TYPE_BET = 2;
	public static final Integer TYPE_SETTLE = 3;
	private String betTime;
	
	public String getTypeLabel() {
		switch(type) {
		case 1: 
			return "充值";
		case 2: 
			return "投注";
		case 3: 
			return "结算";
			
		}
		
		return "未知";
	}
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
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getBetTime() {
		return betTime;
	}
	public void setBetTime(String betTime) {
		this.betTime = betTime;
	}

	

}
