package com.briup.environment.server;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * Server用于启动这个server服务器，开始接受客户端发送的信息并且调用DBstore将接受到的数据持久化
 */
public interface Server extends EmdcModule{
//Collection<Environment>
	public void reciver() throws Exception;
	//该方法用于是Server安全的停止运行
	public void shutdown() throws Exception;
}
