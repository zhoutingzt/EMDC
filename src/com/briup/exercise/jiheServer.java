package com.briup.exercise;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class jiheServer {

	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(10000);
			Socket socket=serverSocket.accept();
			InputStream is=socket.getInputStream();
			ObjectInputStream ois=new ObjectInputStream(is);
			List<String> list=(List<String>) ois.readObject();
			System.out.println(list);
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
