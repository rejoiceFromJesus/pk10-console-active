/**
 * 系统项目名称
 * com.pk10.active.console.controller.client
 * ContentController.java
 * 
 * 2018年4月10日-上午10:05:03
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.Content;
import com.pk10.active.console.service.ContentService;

/**
 *
 * ContentController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月10日 上午10:05:03
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/client/content")
@Api(tags="内容模块")
public class ClientContentController {

	@Autowired
	ContentService contentService;
	
	@ApiOperation(value = "查询单个内容", notes = "查询指定类型的单个内容,1-游戏规则，2-管理员二维码")
	@GetMapping("/type/{type}")
	public Result<List<Content>> findOneByType(@PathVariable("type")Integer type){
		Content content = new Content();
		content.setType(type);
		List<Content> list = contentService.queryListByWhere(content);
		return Result.success(list);
	}
}
