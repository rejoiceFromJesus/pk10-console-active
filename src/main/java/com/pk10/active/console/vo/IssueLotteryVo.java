package com.pk10.active.console.vo;

import java.util.List;

public class IssueLotteryVo {

	private String openDateTime;
	private Integer issue;
	private List<Integer> openNum;
	public String getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}
	public Integer getIssue() {
		return issue;
	}
	public void setIssue(Integer issue) {
		this.issue = issue;
	}
	public List<Integer> getOpenNum() {
		return openNum;
	}
	public void setOpenNum(List<Integer> openNum) {
		this.openNum = openNum;
	}
	
}
