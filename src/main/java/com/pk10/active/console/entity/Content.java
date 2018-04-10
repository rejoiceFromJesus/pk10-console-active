/**
 * 系统项目名称
 * com.pk10.active.console.entity
 * Content.java
 * 
 * 2018年4月9日-下午3:24:00
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.entity;

import javax.persistence.Table;

import com.pk10.active.console.common.constant.ContentTypeEnum;

/**
 *
 * Content
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月9日 下午3:24:00
 * 
 * @version 1.0.0
 *
 */
@Table(name="content")
public class Content extends BaseEntity {

	private String title;
	private String pic;
	private String content;
	private Integer type;
	public String getTypeLabel(){
		return ContentTypeEnum.label(this.type);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
