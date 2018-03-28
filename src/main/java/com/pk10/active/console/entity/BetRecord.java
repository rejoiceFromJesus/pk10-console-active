package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

@Table(name="bet_record")
public class BetRecord extends BaseEntity {
	
	//***************page fields start*******************//
	public String getStatusLabel() {
		return this.isOpen?"已开奖":"未开奖";
	}
	
	
	
	//***************page fields end*******************//
	private String mobile;
	private Integer period;
	private BigDecimal bonus;
	private String betTime;
	private String openTime;
	private BigDecimal betMoney;
	private Boolean isOpen;
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
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public String getBetTime() {
		return betTime;
	}
	public void setBetTime(String betTime) {
		this.betTime = betTime;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public BigDecimal getBetMoney() {
		return betMoney;
	}
	public void setBetMoney(BigDecimal betMoney) {
		this.betMoney = betMoney;
	}
	

	
}
