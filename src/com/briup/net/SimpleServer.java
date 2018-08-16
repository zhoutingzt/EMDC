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
 * 服务器端先发送数据  然后客户端再回复数据
 */
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(10000);
			Socket s=ss.accept();
			while(true){
				InputStream is=System.in;//从终端读取数据  
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String str=br.readLine();
				
				System.out.println("服务器说："+str);
				//服务器将获取到键盘输入的内容发送给客户端
				OutputStream os=s.getOutputStream();
				DataOutputStream dos=new DataOutputStream(os);
				dos.writeUTF(str);
                dos.flush();
                
                //服务器接受客户端发送过来的数据
                InputStream  ips=s.getInputStream();
                DataInputStream dis=new DataInputStream(ips);
                String msg=dis.readUTF();
                System.out.println("客户端说："+msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
