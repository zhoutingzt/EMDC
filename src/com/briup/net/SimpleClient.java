package com.briup.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class SimpleClient {
/*
 * ���������ȷ����� Ȼ��ͻ��˻ش�
 */
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("127.0.0.1",10000);
	        
			while(true){
			//���ܷ���������������
				InputStream is=socket.getInputStream();
				DataInputStream dis=new DataInputStream(is);
				String str=dis.readUTF();
				System.out.println("������˵��"+str);
				//������������� ���������ػ�
				InputStream ips=System.in;
				//�������������һ�㶼���ַ��� ��Ҫһ��һ�ж�
				//��Ҫ��bufferReader readLine()
				//InputStramReader�������ֽ������ַ���������
			    InputStreamReader isr=new InputStreamReader(ips);
			    BufferedReader br=new BufferedReader(isr);
			    //�����̵����ݶ�ȡ���ڴ����ٷ��͵�������
			    String msg=br.readLine();
			    System.out.println("�ͻ���˵��"+msg);
			    //��������� ���������˷�������
			    OutputStream os=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(os);
			    dos.writeUTF(msg);
			    dos.flush();
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
