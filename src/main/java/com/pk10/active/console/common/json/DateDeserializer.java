package com.pk10.active.console.common.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pk10.active.console.handler.InternalServerException;

public class DateDeserializer extends JsonDeserializer<Date> {
	private Logger LOGGER = LoggerFactory.getLogger(DateDeserializer.class);
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {                        
			return DateUtils.parseDate(p.getValueAsString(), "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","yyyyMMss");
		} catch (ParseException e) {
			LOGGER.error("unable to descerialize String to Date:{}",p.getValueAsString(),e);
			throw new InternalServerException("invalid Date String:"+p.getValueAsString(),e);
		}
    }
}