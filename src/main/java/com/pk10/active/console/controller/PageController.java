/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * PageController.java
 * 
 * 2017年6月10日-下午5:13:13
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

/**
 *
 * PageController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月10日 下午5:13:13
 * 
 * @version 1.0.0
 *
 */
@RequestMapping
@Controller
public class PageController {

	@GetMapping("page/**/*.html")
	public String toPage(HttpServletRequest request){
		  // Don't repeat a pattern
	    String pattern = (String)  
	        request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);  

	    String searchTerm = new AntPathMatcher().extractPathWithinPattern(pattern, 
	        request.getServletPath());
		return searchTerm.substring(0,searchTerm.length()-5);
	}
	
	@GetMapping
	public String toHome(){
		return "redirect:/page/home.html";
	}
	
}
