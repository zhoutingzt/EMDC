package com.briup.environment.util;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	public static void main(String[] args) {
		/*
		 * 级别：debug（调试）<info（信息）<warn(警告)<error(错误)<fatal(崩溃)
		 * 只能输出级别高的和同级别的 如果写info就不能输出debug信息
		 */
		Logger logger=Logger.getRootLogger();
		PropertyConfigurator.configure("src/log4j.properties");
		logger.debug("this is debug");//因为log4j文件是行
	    logger.info("this is info");;
	}

}
