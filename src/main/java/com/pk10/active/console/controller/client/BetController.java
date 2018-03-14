/**
 * 系统项目名称
 * com.pk10.active.console.controller.client
 * BetController.java
 * 
 * 2018年3月14日-上午9:45:01
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller.client;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.User;
import com.pk10.active.console.service.BetRecordService;
import com.pk10.active.console.vo.BetVo;


/**
 *
 * BetController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月14日 上午9:45:01
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping
public class BetController {
	
	@Autowired
	BetRecordService betRecordService;
	
	@PostMapping("/client/bet")
	public Result<Boolean> bet(@RequestBody List<BetVo> betVoList, HttpSession session){
		//TODO get login user
		User user = new User();
		user.setMobile("123456");
		betRecordService.betList(betVoList,user);
		return Result.success(true);
	}
}
