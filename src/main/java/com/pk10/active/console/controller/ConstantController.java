/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * ConstantController.java
 * 
 * 2018年3月16日-下午2:00:35
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.handler.InternalServerException;

/**
 *
 * ConstantController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年3月16日 下午2:00:35
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping
public class ConstantController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantController.class);
	
	private static final String CONSTANT_PACKAGE = "com.pk10.active.console.common.constant";
	
	@GetMapping("/enum/{className}")
	public List<Map<String, Object>> enumList(@PathVariable("className") String className) throws Exception{
		className = CONSTANT_PACKAGE+"."+RejoiceUtil.className(className, "-");
		List<Enum> enumList = EnumUtils.getEnumList((Class<Enum>) ClassUtils.getClass(className));
		return enumList.stream().map(new Function<Enum, Map<String,Object>>() {
			@Override
			public Map<String, Object> apply(Enum t) {
				Map<String,Object> map = new HashMap<String, Object>();
				try {
					map.put("label", PropertyUtils.getProperty(t, "label"));
					map.put("value", PropertyUtils.getProperty(t, "value"));
				} catch (Exception e) {
					LOGGER.error("get property failed",e);
					throw new InternalServerException("get property failed", e);
				}
				return map;
			}
		}).collect(Collectors.toList());
	}

}
