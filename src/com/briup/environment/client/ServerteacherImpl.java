package com.briup.environment.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import com.briup.environment.bean.Environment;
import com.briup.environment.server.DBStoreImpl;
import com.briup.environment.server.Server;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;
import com.briup.net.ClientTest;
import com.briup.net.Student;

public class ServerteacherImpl implements Server{
	//private int port=10000;
	private int port;
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private DBStoreImpl dbStoreImpl;
	private Collection<Environment> coll;
//	private static Log log=new LogImpl();
//	private BackUpImpl back=new BackUpImpl();
	private static Log log;
	private BackUpImpl back;
	private static Configuration configuration;
	@Override
	public void reciver() throws Exception {
//		Collection<Environment> collection=new ArrayList<Environment>();
//		serverSocket=new ServerSocket(10000);
//		System.out.println("服务器已经打开！");
//		socket=serverSocket.accept();
//		is=socket.getInputStream();
//		ois=new ObjectInputStream(is);
//		collection=(Collection<Environment>) ois.readObject();
//		return collection;
		
		//并没有实现多线程  一加while（true）就回陷入循环
		
		Handler handler=new ServerteacherImpl().new Handler();
		handler.start();
		
		//return handler.getColl();
		

	}
//关闭网络连接资源
	@Override
	public void shutdown() throws Exception {
		if(ois!=null)ois.close();
		if (is!=null) is.close();
		if(bis!=null) bis.close();
		//if(socket!=null) socket.close();
		//if(serverSocket!=null) serverSocket.close();
		//System.out.println("网络连接错误，资源断开！！");
		log.error("网络连接错误，资源断开！！");
		
	}
//内部类  生成api时内部类打包的东西别人是看不到的  隐藏性
	class Handler extends Thread{//服务器可能需要处理多个客户端 所以需要利用线程
		@Override
		public void run() {
			System.out.println("服务器已经开启，等待客户端连接");
			try {
				serverSocket=new ServerSocket(10000);
				socket=serverSocket.accept();
				System.out.println("客户端和服务器连接成功，"+"服务器器准备接受数据....");
				is=socket.getInputStream();
				bis=new BufferedInputStream(is);
				ois=new ObjectInputStream(bis);
				coll= (Collection<Environment>) ois.readObject();
				Collection<Environment> obj= (Collection<Environment>) back.load("serverback",true);
				 if(obj!=null){
					 log.info("服务器正在加载备份数据！");
					 coll.addAll(obj);
					 log.info("服务器加载备份数据完成");
				 }
			//	List<Student> list=(List<Student>) ois.readObject();
				 log.debug("数据接受完成，一共接收："+coll.size());;
				//System.out.println("数据接受完成，一共接收："+coll.size());
				shutdown();
				log.info("网络连接资源关闭，开启数据库准备入库.....");
				//System.out.println("网络连接资源关闭，开启数据库准备入库.....");
				//dbStoreImpl=new DBStoreImpl();
				dbStoreImpl.savetoDB(coll);
				log.info("通过服务器端数据入库成功！！！");
				//System.out.println("通过服务器端数据入库成功！！！");
				
				
				
			} catch (Exception e) {
				try {
					log.error("发生错误，服务器正在备份数据");
					//把发送失败的数保留在serverback中，覆盖原来的数据
				    back.store("serverback",coll,true);
		            log.warn("备份数据成功");
				} catch (Exception e1) {
					try {
						log.error("备份数据失败");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
	            
	          
	           
			}
			
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
			dbStoreImpl=(DBStoreImpl) configuration.getDbStore();
			log=(LogImpl)configuration.getLog();
			back=(BackUpImpl) configuration.getBackup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception{
		configuration=new ConfigurationImpl();
		Server server=configuration.getServer();
		//Server server=new ServerImpl();
		try {
			server.reciver();
			//System.out.println("数据大小为："+collection.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
