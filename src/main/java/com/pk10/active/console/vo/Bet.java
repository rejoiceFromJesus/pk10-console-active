/**
 * 系统项目名称
 * com.pk10.active.console.vo
 * Bet.java
 * 
 * 2018年3月23日-下午2:05:08
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 *
 * Bet
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月23日 下午2:05:08
 * 
 * @version 1.0.0
 *
 */
@ApiModel(value="单个投注记录")
public class Bet {
	@ApiModelProperty(value="排名")
	private Integer rank;
	@ApiModelProperty(value="结果")
	private Integer result;
	@ApiModelProperty(value="金额")
	private BigDecimal money;
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
}
