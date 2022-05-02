package com.dayamina.common.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	protected static Logger logger = LogManager.getLogger(Log.class.getName());
	
	public static void info(String info){
		logger.info(info);
	}
	
	public static void error(String error){
		logger.error(error);
	}
	
	public static void warn(String warning){
		logger.warn(warning);
	}

}
