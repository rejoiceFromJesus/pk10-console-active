package com.pk10.active.console.entity;

import java.util.List;

import javax.persistence.Table;

@Table(name="lottery_history")
public class LotteryHistory extends BaseEntity {

	  private String gameCode = "bjpk10";
	  private String openDate;
	  private Integer period;
	  private String openNum;
	  private String oneTwoSum;
	  private String oddEven;
	  private String bigSmall;
	  private String tragonTiger;
	  private String openDateTime;
	  
	  public String[] getOpenNums(){
		  return openNum.split(",");
	  }
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getOpenNum() {
		return openNum;
	}
	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}
	public String getOneTwoSum() {
		return oneTwoSum;
	}
	public void setOneTwoSum(String oneTwoSum) {
		this.oneTwoSum = oneTwoSum;
	}
	public String getOddEven() {
		return oddEven;
	}
	public void setOddEven(String oddEven) {
		this.oddEven = oddEven;
	}
	public String getBigSmall() {
		return bigSmall;
	}
	public void setBigSmall(String bigSmall) {
		this.bigSmall = bigSmall;
	}
	public String getTragonTiger() {
		return tragonTiger;
	}
	public void setTragonTiger(String tragonTiger) {
		this.tragonTiger = tragonTiger;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}
	  
}
