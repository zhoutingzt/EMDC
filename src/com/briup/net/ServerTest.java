package com.briup.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;



public class ServerTest {
/*
 * 内部类的创建 要先创建外部类 再创建内部类
 */
	private ServerSocket serverSocket;
	private Socket socket;
    private InputStream is;
    private ObjectInputStream ois;
    public void shutdown(){
    	
			try {
				if(ois!=null) ois.close();
				if(is!=null) is.close();
				if(socket!=null) socket.close();
				if(serverSocket!=null) serverSocket.close();
				System.out.println("资源已经释放");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
	public static void main(String[] args) {
		//内部类的创建 要先创建外部类 再创建内部类
		ServerTest st=new ServerTest();
		Handler handler=st.new Handler();
	    handler.start();

	}
class Handler extends Thread{//服务器可能需要处理多个客户端 所以需要利用线程

	@Override
	public void run() {
		
		System.out.println("服务器已经开启，等待客户端连接");
		try {
			serverSocket=new ServerSocket(10000);
			socket=serverSocket.accept();
			System.out.println("客户端和服务器连接成功，"+"服务器器准备接受数据....");
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
			Set<Student> set=(Set<Student>) ois.readObject();
		//	List<Student> list=(List<Student>) ois.readObject();
			System.out.println("数据接受完，开始打印输出");
			for (Student student : set) {
				System.out.println(student);
			}
			shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
}
