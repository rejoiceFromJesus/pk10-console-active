package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.SideNameEnum;

@Table(name="bet_info")
public class BetInfo extends BaseEntity {

	private Integer rank;
	private Integer result;
	private String mobile;
	private Integer period;
	private String betTime;
	private BigDecimal money;
	private Integer type;
	private BigDecimal rate;
	public Integer getRank() {
		return rank;
	}
	
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getBetTime() {
		return betTime;
	}
	public void setBetTime(String betTime) {
		this.betTime = betTime;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getResultLabel() {
		if(this.type==1) {
			return SideNameEnum.label(this.result);
		}
		return this.result.toString();
	}
	
	
}
