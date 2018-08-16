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
 * �ڲ���Ĵ��� Ҫ�ȴ����ⲿ�� �ٴ����ڲ���
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
				System.out.println("��Դ�Ѿ��ͷ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
	public static void main(String[] args) {
		//�ڲ���Ĵ��� Ҫ�ȴ����ⲿ�� �ٴ����ڲ���
		ServerTest st=new ServerTest();
		Handler handler=st.new Handler();
	    handler.start();

	}
class Handler extends Thread{//������������Ҫ�������ͻ��� ������Ҫ�����߳�

	@Override
	public void run() {
		
		System.out.println("�������Ѿ��������ȴ��ͻ�������");
		try {
			serverSocket=new ServerSocket(10000);
			socket=serverSocket.accept();
			System.out.println("�ͻ��˺ͷ��������ӳɹ���"+"��������׼����������....");
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);
			Set<Student> set=(Set<Student>) ois.readObject();
		//	List<Student> list=(List<Student>) ois.readObject();
			System.out.println("���ݽ����꣬��ʼ��ӡ���");
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
