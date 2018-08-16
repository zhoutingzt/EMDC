package com.briup.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
/*
 * ���������ȷ�������  Ȼ��ͻ����ٻظ�����
 */
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(10000);
			Socket s=ss.accept();
			while(true){
				InputStream is=System.in;//���ն˶�ȡ����  
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String str=br.readLine();
				
				System.out.println("������˵��"+str);
				//����������ȡ��������������ݷ��͸��ͻ���
				OutputStream os=s.getOutputStream();
				DataOutputStream dos=new DataOutputStream(os);
				dos.writeUTF(str);
                dos.flush();
                
                //���������ܿͻ��˷��͹���������
                InputStream  ips=s.getInputStream();
                DataInputStream dis=new DataInputStream(ips);
                String msg=dis.readUTF();
                System.out.println("�ͻ���˵��"+msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
