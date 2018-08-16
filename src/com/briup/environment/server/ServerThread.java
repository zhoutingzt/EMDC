package com.briup.environment.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUP;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;

public class ServerThread extends Thread{

	
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private DBStoreImpl dbStoreImpl;
	private  ServerztImpl serverztImpl;
	
	//private BackUpImpl back=new BackUpImpl();
	
	private static Configuration configuration=new ConfigurationImpl();
	private static Log log;
	private BackUP back;
	public ServerThread(Socket socket,Log log,BackUP back,Configuration configuration) {
		this.socket = socket;
		this.log=log;
		this.back=back;
		this.configuration=configuration;
	}
	public ServerThread(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		try {
			log=configuration.getLog();
			back=configuration.getBackup();
			log.info("服务器已经开启，等待客户端连接");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 //System.out.println("服务器已经开启，等待客户端连接");
		Collection<Environment> coll=new ArrayList<Environment>();
		try {
			
			//serverSocket=new ServerSocket(port);
			//socket=serverSocket.accept();
			String address=socket.getInetAddress().getHostAddress();
			log.info("客户端和服务器连接成功，"+"服务器器准备接受数据....");
			//System.out.println("客户端和服务器连接成功，"+"服务器器准备接受数据....");
			log.info("连接的客户端ip:"+address);
			//System.out.println("连接的客户端ip:"+address);s
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
			log.debug("数据接受完成，一共接收："+coll.size());
			//System.out.println("数据接受完成，一共接收："+coll.size());
			log.info("网络连接资源关闭，开启数据库准备入库.....");
			//System.out.println("网络连接资源关闭，开启数据库准备入库.....");
			//dbStoreImpl=new DBStoreImpl();
			dbStoreImpl=(DBStoreImpl) configuration.getDbStore();

			dbStoreImpl.savetoDB(coll);
			log.info("通过服务器端数据入库成功！！！");
			//System.out.println("通过服务器端数据入库成功！！！");
			
			
		} catch (Exception e) {
			//serverztImpl=new ServerztImpl();
			
			try {
				serverztImpl=(ServerztImpl) configuration.getServer();
				log.error("发生错误，服务器正在备份数据");
				//把发送失败的数保留在serverback中，覆盖原来的数据
			    back.store("serverback",coll,true);
	            log.warn("备份数据成功");
				//serverztImpl.shutdown();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//socket.shutdown();
			e.printStackTrace();
		}finally{
			if(ois!=null)
				try {
					ois.close();
					if (is!=null) is.close();
					if(bis!=null) bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

	
}
