package com.briup.environment.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUP;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;


public class ServerztImpl implements Server{
	 //int port=10000;
	 int port;
	 ServerSocket serverSocket;
	 Socket socket;
	 InputStream is;
	 BufferedInputStream bis;
	 ObjectInputStream ois;
	 DBStoreImpl dbStoreImpl;
	 Collection<Environment> coll;
	 Log log=new LogImpl();
	 private static Configuration configuration;
	 private static BackUP back;
	@Override
	public void reciver() throws Exception {
		try {
			serverSocket=new ServerSocket(port);
			while(true){
				Socket client=serverSocket.accept();
				//new PictureThread(client).start();
				ServerThread server=new ServerThread(client,log,back,configuration);//这里把其他加进入
				server.start();
				log.info("线程名为："+server.getName());
				//System.out.println("线程名为："+server.getName());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			shutdown();
		}
		
	}

	@Override
	public void shutdown() throws Exception {
		if(ois!=null)ois.close();
		if (is!=null) is.close();
		if(bis!=null) bis.close();
		if(socket!=null) socket.close();
		if(serverSocket!=null) serverSocket.close();
		log.debug("网络连接错误，资源断开！！");
		//System.out.println("网络连接错误，资源断开！！");
		
		
	}
	public static void main(String[] args) throws Exception{
		configuration=new ConfigurationImpl();
		//ServerztImpl server=new ServerztImpl();
		ServerztImpl server=(ServerztImpl) configuration.getServer();
		try {
			server.reciver();
			//System.out.println("数据大小为："+collection.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(Properties properties) throws Exception {
		port=Integer.parseInt(properties.getProperty("port"));
		
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration=configuration;
		try {
			log=configuration.getLog();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
