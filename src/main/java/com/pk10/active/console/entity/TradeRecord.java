package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.TradeTypeEnum;

@Table(name="trade_record")
public class TradeRecord extends BaseEntity {

	private String mobile;
	private BigDecimal money;
	private Integer period;
	private BigDecimal balance;
	private String tradeTime;
	private Integer type;
	private String betTime;
	
	public String getTypeLabel() {
		return TradeTypeEnum.label(this.type);
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
