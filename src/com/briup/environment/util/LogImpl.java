package com.briup.environment.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogImpl implements Log {

   private String path;
   private static Logger logger;
   private static Configuration configuration;
//   static{//加载的时候自动加载
//	   logger=Logger.getRootLogger();
//	   PropertyConfigurator.configure("src/log4j.properties");
//   }
	@Override
	public void debug(String msg) throws Exception {
		logger.debug(msg);
	}

	@Override
	public void info(String msg) throws Exception {
		logger.info(msg);
		
	}

	@Override
	public void warn(String msg) throws Exception {
		logger.warn(msg);
		
	}

	@Override
	public void error(String msg) throws Exception {
		logger.error(msg);
	}

	@Override
	public void fatal(String msg) throws Exception {
		logger.fatal(msg);
	}

	public static void main(String[] args) {
		new LogImpl();
	}

	@Override
	public void init(Properties properties) throws Exception {
		path=properties.getProperty("log-file");
		logger=Logger.getRootLogger();
		PropertyConfigurator.configure(path);
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration=configuration;
		
	}
	
}
