package com.briup.environment.client;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * Client 接口是物联网数据中心项目网络模块客户端的规范
 * Client的作用是与服务器端进行通信，传递信息
 */
public interface Client extends EmdcModule{
  public  void send(Collection<Environment> c) throws Exception;
	
}
