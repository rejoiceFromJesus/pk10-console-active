/**
 * 系统项目名称
 * com.pk10.active.console.vo
 * CurrentPeriodLottery.java
 * 
 * 2018年3月14日-下午5:28:52
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 *
 * CurrentPeriodLottery
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月14日 下午5:28:52
 * 
 * @version 1.0.0
 *
 */
public class CurrentPeriodLottery {
	private String gameCode="bjpk10";
	private Integer preIssue;
	private List<Integer>openNum;
	private List<String> dragonTiger;
	private List<Object> oneTwoSum;
	private Integer issue;
	private Date currentOpenDateTime;
	private Date openDateTime;
	private Date serverTime;
	private Integer openedCount;
	private Integer dailyTotal;
	
	public List<String> getDragonTiger() {
		return dragonTiger;
	}
	public void setDragonTiger(List<String> dragonTiger) {
		this.dragonTiger = dragonTiger;
	}
	public List<Object> getOneTwoSum() {
		return oneTwoSum;
	}
	public void setOneTwoSum(List<Object> oneTwoSum) {
		this.oneTwoSum = oneTwoSum;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public Integer getPreIssue() {
		return preIssue;
	}
	public void setPreIssue(Integer preIssue) {
		this.preIssue = preIssue;
	}
	public List<Integer> getOpenNum() {
		return openNum;
	}
	public void setOpenNum(List<Integer> openNum) {
		this.openNum = openNum;
	}
	public Integer getIssue() {
		return issue;
	}
	public void setIssue(Integer issue) {
		this.issue = issue;
	}
	public Date getCurrentOpenDateTime() {
		return currentOpenDateTime;
	}
	public void setCurrentOpenDateTime(Date currentOpenDateTime) {
		this.currentOpenDateTime = currentOpenDateTime;
	}
	public Date getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(Date openDateTime) {
		this.openDateTime = openDateTime;
	}
	public Date getServerTime() {
		return serverTime;
	}
	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}
	public Integer getOpenedCount() {
		return openedCount;
	}
	public void setOpenedCount(Integer openedCount) {
		this.openedCount = openedCount;
	}
	public Integer getDailyTotal() {
		return dailyTotal;
	}
	public void setDailyTotal(Integer dailyTotal) {
		this.dailyTotal = dailyTotal;
	}
	
}
