package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.RuleTypeEnum;
import com.pk10.active.console.common.constant.SideNameEnum;

@Table(name="bet_info")
public class BetInfo extends BaseEntity {

	private Integer rank;
	private Integer result;
	private String mobile;
	private Integer period;
	private String betTime;
	private BigDecimal money;
	private BigDecimal bonus;
	private Integer luckyResult;
	private Integer type;
	private BigDecimal rate;
	
	public String getLuckyResultLabel(){
		if(RuleTypeEnum.NUMBER.value().equals(type)){
			return luckyResult == null ? null : luckyResult.toString();
		}else{
			return SideNameEnum.label(luckyResult);
		}
	}
	public String getResultLabel() {
		if(RuleTypeEnum.SIDE.value().equals(type)){
			return SideNameEnum.label(this.result);
		}
		return this.result.toString();
	}
	public String getRankName() {
		if("0".equals(String.valueOf(this.rank))) {
			return "冠亚和";
		}else {
			return "第"+rank+"名";
		}
	}
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
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public Integer getLuckyResult() {
		return luckyResult;
	}
	public void setLuckyResult(Integer luckyResult) {
		this.luckyResult = luckyResult;
	}
	
	
}
