/**
 * 系统项目名称
 * com.pk10.active.console.entity
 * User.java
 * 
 * 2017年6月9日-下午2:19:10
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * User
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:19:10
 * 
 * @version 1.0.0
 *
 */
@Table(name="user")
public class User extends BaseEntity {
	
	@Transient
	private String likes;
	
	
	@Transient
	private String oldPassword;
	
	public String getOldPassword() {
		return oldPassword == null ? password:oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private BigDecimal balance;
	private String password;
	private String mobile;
	private Integer isAdmin;
	
	
	public String getAdmin() {
		return isAdmin == 1 ? "是":"否";
	}
	

	
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	
}
