package com.pk10.active.console.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pk10.active.console.common.constant.SideNameEnum;

@Table(name="rule_side")
public class RuleSide extends BaseEntity implements Serializable{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 6762588769543180201L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer rank;
	private Integer result;
	
	private BigDecimal rate;
	
	public String getRankName() {
		if("0".equals(String.valueOf(this.rank))) {
			return "冠亚和";
		}else {
			return "第"+rank+"名";
		}
	}
	public String getResultName() {
		return SideNameEnum.label(this.result);
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
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}

	
}
