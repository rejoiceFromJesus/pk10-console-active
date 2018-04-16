package com.pk10.active.console.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.RuleTypeEnum;
import com.pk10.active.console.common.constant.SideNameEnum;

@Table(name="bet_info")
@ApiModel("单个投注")
public class BetInfo extends BaseEntity {

	@ApiModelProperty("排名")
	private Integer rank;
	@ApiModelProperty("结果")
	private Integer result;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("期数")
	private Integer period;
	@ApiModelProperty("投注时间")
	private String betTime;
	@ApiModelProperty("投注金额")
	private BigDecimal money;
	@ApiModelProperty("奖金")
	private BigDecimal bonus;
	@ApiModelProperty("开奖结果-value")
	private Integer luckyResult;
	@ApiModelProperty("投注类型-value")
	private Integer type;
	@ApiModelProperty("赔率")
	private BigDecimal rate;
	@ApiModelProperty("开奖结果-label")
	public String getLuckyResultLabel(){
		if(RuleTypeEnum.NUMBER.value().equals(type)){
			return luckyResult == null ? null : luckyResult.toString();
		}else{
			return SideNameEnum.label(luckyResult);
		}
	}
	@ApiModelProperty("结果-label")
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
