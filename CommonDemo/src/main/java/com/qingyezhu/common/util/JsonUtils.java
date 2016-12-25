package com.qingyezhu.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	public static String objectToString(Object obj){
		String ret = null;
		if(obj != null){
			ObjectMapper objectMapper =  new ObjectMapper();
			try {
				ret = objectMapper.writeValueAsString(obj);
			} catch (Exception e) {
				logger.error("catch error object=" + obj, e);
			}
		}
		return ret;
	}
	
	public static <T> T stringToObject(String str, TypeReference<T> clazz){
		T t = null;
		if(StringUtils.isNotBlank(str) && clazz != null){
			ObjectMapper objectMapper =  new ObjectMapper();
			try {
				t = objectMapper.readValue(str, clazz);
			} catch (Exception e) {
				logger.error("catch error string=" + str, e);
			}
		}
		return t;
	}

}
