package com.pk10.active.console.common.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pk10.active.console.common.json.DateDeserializer;
import com.pk10.active.console.common.json.DateSerializer;

/**
 * 
* @ClassName: JsonUtil 
* @Description: json工具类
* @author rejoice  948870341@qq.com 
* @date 2016年11月11日 下午12:16:59 
*
 */
@Component
@Lazy(false)
public final class JsonUtil implements InitializingBean{

	
	@Autowired(required=false)
	private ObjectMapper mapper;
	
	private static ObjectMapper mapperTemp = null;


	/**
	 * 获取jackson json lib的ObjectMapper对象
	 * 
	 * @return -- ObjectMapper对象
	 */
	public static ObjectMapper getMapper() {
		return mapperTemp;
	}


	/**
	 * 将json转成java bean
	 * 
	 * @param <T>
	 *            -- 多态类型
	 * @param json
	 *            -- json字符串
	 * @param clazz
	 *            -- java bean类型(Class)
	 * @return -- java bean对象
	 */
	public static <T> T toBean(String json, Class<T> clazz) {

		T rtv = null;
		try {
			rtv = mapperTemp.readValue(json, clazz);
		} catch (Exception ex) {
			throw new IllegalArgumentException("json字符串转成java bean异常", ex);
		}
		return rtv;
	}


	/**
	 * 将java bean转成json
	 * 
	 * @param bean
	 *            -- java bean
	 * @return -- json 字符串
	 */
	public static String toJson(Object bean) {

		String rtv = null;
		try {
			rtv = mapperTemp.writeValueAsString(bean);
		} catch (Exception ex) {
			throw new IllegalArgumentException("java bean转成json字符串异常", ex);
		}
		return rtv;
	}
	
	
	public static ObjectMapper buildObjectMapper(){
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Date.class, new DateDeserializer());
		simpleModule.addSerializer(Date.class, new DateSerializer());
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(simpleModule);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		//mapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		mapper.setTimeZone(timeZone);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return mapper;
	}


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(mapper == null){
			mapper = buildObjectMapper();
		}
		mapperTemp = mapper;
		
	}
}