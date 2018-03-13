package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pk10.active.console.common.constant.SideNameEnum;

@Table(name="rule_side")
public class RuleSide extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer rank;
	private Integer side;
	private BigDecimal rate;
	
	public String getRankName() {
		if("0".equals(String.valueOf(this.rank))) {
			return "冠亚和";
		}else {
			return "第"+rank+"名";
		}
	}
	
	public String getSideName() {
		return SideNameEnum.label(this.side);
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

	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getSide() {
		return side;
	}

	public void setSide(Integer side) {
		this.side = side;
	}
	
}
