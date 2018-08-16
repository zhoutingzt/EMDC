package com.briup.environment.util;

/*
 * 该接口提供了日志模块的规范，日志模块将日志信息划分为五个级别，不同级别的日志的记录格式、记录方式不尽相同
 */
public interface Log extends EmdcModule{
	//记录debug级别的日志
    public void debug(String msg) throws Exception;
    //记录info级别日志
    public void info(String msg) throws Exception;
    //记录warn级别日志
    public void warn(String msg) throws Exception;
    //记录error级别日志
    public void error(String msg) throws Exception;
    //记录fatal日志
    public void fatal(String msg) throws Exception;
}
