package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="rule_side")
public class RuleSide extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer rank;
	private Boolean side;
	private BigDecimal rate;
	private String sideName;
	
	public String getSideName() {
		return sideName;
	}
	public void setSideName(String sideName) {
		this.sideName = sideName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Boolean getSide() {
		return side;
	}
	public void setSide(Boolean side) {
		this.side = side;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
}
