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
 * 服务器端先发数据 然后客户端回答
 */
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("127.0.0.1",10000);
	        
			while(true){
			//接受服务器发来的数据
				InputStream is=socket.getInputStream();
				DataInputStream dis=new DataInputStream(is);
				String str=dis.readUTF();
				System.out.println("服务器说："+str);
				//键盘输入的内容 给服务器回话
				InputStream ips=System.in;
				//键盘输入的内容一般都是字符串 需要一行一行读
				//需要用bufferReader readLine()
				//InputStramReader是连接字节流和字符流的桥梁
			    InputStreamReader isr=new InputStreamReader(ips);
			    BufferedReader br=new BufferedReader(isr);
			    //将键盘的内容读取到内存中再发送到服务器
			    String msg=br.readLine();
			    System.out.println("客户端说："+msg);
			    //构建输出流 给服务器端发送数据
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
