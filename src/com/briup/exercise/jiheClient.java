package com.briup.exercise;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class jiheClient {

	public static void main(String[] args) {
		List<String> list1=new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("e");
		List<String> list2=new ArrayList<String>();
		list2.add("d");
		list2.add("e");
		list2.add("f");
		list2.add("g");
		list2.add("h");
		//�ϲ����ϲ�ȥ���ظ��� ����1
		list2.removeAll(list1);//��list2���Ƴ�����list1�е�Ԫ��
		list1.addAll(list2);//��list1���������list2��Ԫ��
		//for (String string : list2) {
			System.out.println(list1);
		//}
			
			//������
//			for(Iterator it1=list1.iterator();it1.hasNext();){
//				if(list2.contains(it1.next())){
//					list2.remove(it1.next());
//				}
//			}
//			list1.addAll(list2);
//			System.out.println(list1);
		try {
			Socket socket=new Socket("127.0.0.1",10000);
			OutputStream os=socket.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			oos.writeObject(list1);
			oos.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
