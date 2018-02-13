package com.pk10.active.console.common.json;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk10.active.console.common.util.JsonUtil;

@Configuration
public class JsonCOnfig {

	@Bean
	public ObjectMapper mapper(){
		System.err.println("1111111111111");
		  return JsonUtil.buildObjectMapper();
	}
}
