package com.briup.environment.client;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.server.Server;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.ConfigurationteacherImpl;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;

public class ClientImpl implements Client{

//	private String ip="127.0.0.1";
//	private int port=10000;
//	private static Log log=new LogImpl();
//	private BackUpImpl back=new BackUpImpl();
//	private static Configuration configuration=new ConfigurationteacherImpl();
	private String ip;
	private int port;
	private static Log log;
	private BackUpImpl back;
	private static Configuration configuration;
	@Override
	public void send(Collection<Environment> c) throws Exception {
		try {
			log.info("正在与服务器建立连接........");
			//System.out.println("正在努力与服务器建立连接.......");
			Socket socket=new Socket(ip,port);
			log.info("客户端连接服务器，开始发送数据：");
			//System.out.println("客户端连接服务器，开始发送数据：");
			/*
			 * 客户端发送数 通过socket创建流
			 * 由于发送采集模块收集好的数据environment集合对象
			 * 所有封装成Object输出流
			 */
			
			Object data=back.load("clientback",false);
			Collection<Environment> collectionback=(Collection<Environment>) data;
			if(data!=null){
				log.warn("在客户端找到备份");
				c.addAll(collectionback);
		        log.warn("在客户端加载备份");
			}
			
			OutputStream os=socket.getOutputStream();
			BufferedOutputStream bos=new BufferedOutputStream(os);
			ObjectOutputStream oos=new ObjectOutputStream(bos);
			oos.writeObject(c);
			log.info("客户端发送数据完成");
			//System.out.println("客户端发送数据完成");
	        oos.flush();
		} catch (Exception e) {
			try {
				back.store("clientback", c, false);//不追加即为覆盖
				 log.warn("备份终止");
			} catch (Exception e1) {
				 log.error("客户端数据备份失败");
				//e1.printStackTrace();
			}
			
		}
	
	}

	@Override
	public void init(Properties properties) throws Exception {
		ip=properties.getProperty("ip");
		port=Integer.parseInt(properties.getProperty("port"));
		
	}

	public static void main(String[] args) throws Exception{
		configuration=new ConfigurationImpl();
		GatherImpl gather=(GatherImpl) configuration.getGather();
		Collection<Environment> c=gather.gather();
		log.info("客户端采集的数据："+c.size());
		//System.out.println("客户端采集的数据："+c.size());
		ClientImpl client= (ClientImpl) configuration.getClient();
		client.send(c);
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration=configuration;
		try {
			back=(BackUpImpl) configuration.getBackup();
			log=configuration.getLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
