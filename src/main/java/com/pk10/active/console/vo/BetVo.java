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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
@ApiModel(value="投注实体",subTypes=Bet.class)
public class BetVo {

	@ApiModelProperty(required=true,dataType="整数" ,value="当前期数" )
	private Integer period;
	@ApiModelProperty(required=true,value="投注列表",dataType="Bet")
	List<Bet> betList = new ArrayList<Bet>();
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public List<Bet> getBetList() {
		return betList;
	}
	public void setBetList(List<Bet> betList) {
		this.betList = betList;
	}
	
	
	
	
}
