/**
 * 系统项目名称
 * com.pk10.active.console.vo
 * BetVo.java
 * 
 * 2018年3月14日-上午9:46:26
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.vo;

import java.math.BigDecimal;

/**
 *
 * BetVo
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月14日 上午9:46:26
 * 
 * @version 1.0.0
 *
 */
public class BetVo {

	private Integer rank;
	private Integer luckyNumber;
	private BigDecimal money;
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getLuckyNumber() {
		return luckyNumber;
	}
	public void setLuckyNumber(Integer luckyNumber) {
		this.luckyNumber = luckyNumber;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
}
