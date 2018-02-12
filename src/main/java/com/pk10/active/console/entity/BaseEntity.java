/**
 * 系统项目名称
 * com.pk10.active.console.entity
 * BaseEntity.java
 * 
 * 2017年6月9日-下午2:17:40
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * BaseEntity
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:17:40
 * 
 * @version 1.0.0
 *
 */
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date createTime;
	private Date updateTime;
	private String remark;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
