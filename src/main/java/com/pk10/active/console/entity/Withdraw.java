/**
 * 系统项目名称
 * com.pk10.active.console.entity
 * WithdrawApply.java
 * 
 * 2018年4月8日-上午9:16:34
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.WithdrawStatusEnum;

/**
 *
 * WithdrawApply
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月8日 上午9:16:34
 * 
 * @version 1.0.0
 *
 */
@Table(name="withdraw")
public class Withdraw extends BaseEntity {
	
	private String mobile;
	private BigDecimal money;
	private Integer status;
	private String applyTime;
	
	public String getStatusLabel(){
		return WithdrawStatusEnum.label(this.status);
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	
	
}
