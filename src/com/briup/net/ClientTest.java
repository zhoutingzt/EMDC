package com.briup.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ClientTest {

	public static void main(String[] args) {
        Socket socket=null;
        OutputStream os=null;
        ObjectOutputStream oos=null;//发送的对象 必须支持 java.io.Serializable 或 java.io.Externalizable 接口的对象才能从流读取
        File file=new File("src/test.txt");
        FileInputStream fis;
        
		try {
			System.out.println("客户端开始读取文件....");
			fis = new FileInputStream(file);
			InputStreamReader isr=new InputStreamReader(fis);//将字节流转成字符流
		    BufferedReader br=new BufferedReader(isr);
		    String str=null;
		    String s[]=null;
		    Student student=null;
		    StudentCom sc=new StudentCom();
		  //  Student sc=new Student();
		    Set<Student> set=new TreeSet<Student>(sc);
		   //List<Student> list=new ArrayList<Student>();
		    while((str=br.readLine())!=null){
		    	student=new Student();
		    	s=str.split("[:]");
		    	//方法①
//		    	student.setId(Integer.parseInt(s[0]));
//		    	student.setName(s[1]);
//		    	student.setAge(Integer.parseInt(s[2]));
//		    	student.setGender(s[3]);
//		    	student.setScore(Integer.parseInt(s[4]));
		    	student=new Student(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),s[3],Integer.parseInt(s[4]));
		    	set.add(student);
		      //  System.out.println(student);
		    }
		    
		    System.out.println("客户端数据采集完成");
		    System.out.println("客户端和服务器端正在建立连接");
		    socket=new Socket("127.0.0.1",10000);
		    System.out.println("连接成功");
		    os=socket.getOutputStream();
		    oos=new ObjectOutputStream(os);
		    oos.writeObject(set);//发送的对象需要实现Serializable
		    oos.flush();
		    System.out.println("客户端发送完成");
//		    for (Student student2 : list) {
//				System.out.println(student2);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {//后开的先关
			if(oos!=null)
					oos.close();
			if(os!=null)
				os.close();
			if(socket!=null)
				socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
		}
		}
	}
}
//自定义排序
class StudentCom implements Serializable,Comparator{//这样写避免了类之间都耦合 同时代码可读性比较高
	/*
	 * 排序分为：自然排序
     * 自定义排序：需要实现java.util.Comparator,重写compare方法，该方法返回int类型，-1表示顺序不变，1表示交换位置 0表示不改变
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Student s1=(Student) o1;
		Student s2=(Student) o2;
		//1表示交换位置 -1表示不交换 0表示相等
		//表示升序  如果将1改为-1 -1改为1的话为降序
		if(s1.getScore()>s2.getScore()){
			return 1;
		}else if(s1.getScore()<s2.getScore()) {
			return -1;
		}else{
			return 0;
		}
		
	}

}
