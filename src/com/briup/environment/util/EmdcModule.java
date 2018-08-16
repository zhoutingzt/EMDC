package com.briup.environment.util;

import java.util.Properties;
/*
 * 该接口是出配置模块以外的所有模块的父 接口（即其他接口要继承这个接口）
 * 用于模块接手初始化配置信息和注入配置模块
 * 将所需要的配置信息传递进该类，该类得到配置信息或进行初始化
 * 一个properties实例封装了初始化所需要的各种配置信息 
 */
public interface EmdcModule  {
	/*
	 * 将所有需要的配置信息从传递进该类，该类得到配置信息后进行初始化，建议在执行该类其他方法之前，先执行这个方法
	 */
	void init(Properties properties) throws Exception;
	/*
	 * 该方法用于配置模块
	 */
	public void setConfiguration(Configuration configuration);
}
