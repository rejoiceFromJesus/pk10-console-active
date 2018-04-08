/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * WithdrawController.java
 * 
 * 2018年4月8日-下午2:38:43
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.Withdraw;
import com.pk10.active.console.service.WithdrawService;

/**
 *
 * WithdrawController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月8日 下午2:38:43
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController<Withdraw, WithdrawService> {

	
	@PutMapping("/confirm/{id}")
	public Result<Void> confirm(@PathVariable("id") Integer id){
		this.getService().confirm(id);
		return Result.success(null);
	}
}
