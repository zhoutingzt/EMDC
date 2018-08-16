package com.briup.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;


public class ClientServer {//管道可以替代网络
 public static void main(String[] args) {
	PipedInputStream pis=new PipedInputStream();
	try {
		PipedOutputStream pos=new PipedOutputStream(pis);//创建连接到指定管道输入流的管道输出流。
		new Sender(pos, "发送方").start();
		new Fecher(pis, "接收方").start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
}
//发送方
class Sender extends Thread{
	PipedOutputStream pos=null;
	public Sender(PipedOutputStream pos,String name){//创建对象的时候 就拥有管道 
		this.pos=pos;
		setName(name);
	}
	@Override
	public void run() {
		try {
			FileReader fr=new FileReader("src/teacher.txt");
			BufferedReader br=new BufferedReader(fr);
			String str=null;
			String s[]=null;
			Teacher teacher=null;
			//List<Teacher> list=new ArrayList<Teacher>();
			Set<Teacher> set=new TreeSet<Teacher>();
			while((str=br.readLine())!=null){
				s=str.split("[:]");
				teacher=new Teacher(s[0],Integer.parseInt(s[1]),s[2]);
				set.add(teacher);
				//list.add(teacher);
			}
			ObjectOutputStream oos=new ObjectOutputStream(pos);
			oos.writeObject(set);
			oos.flush();
			System.out.println(getName()+"发送完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
//接收方
class Fecher extends Thread{
	PipedInputStream pis=null;
	public Fecher(PipedInputStream pis,String name){
		this.pis=pis;
		setName(name);
	}
	@Override
	public void run() {
	      try {
			ObjectInputStream ois=new ObjectInputStream(pis);
			Set<Teacher> set=(Set<Teacher>) ois.readObject();
			//List<Teacher> list=(List<Teacher>) ois.readObject();
			System.out.println(getName()+"数据接收完毕，开始打印");
			for (Teacher teacher : set) {
				System.out.println(teacher);
			}
			//if(ois!=null) ois.close();
			//if(pis!=null) pis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class Teacher implements Serializable,Comparable{

	private String name;
	private int age;
	private String gender;
	
	public Teacher() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Teacher(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", age=" + age + ", gender=" + gender
				+ "]";
	}

	//自然排序 当前对象比较传入对象升序
	//传入对象比较当前对象降序
	@Override
	public int compareTo(Object o) {
		Teacher teacher=(Teacher) o;
		//return this.age-teacher.age;
		//升序
		return this.age>=teacher.age?1:-1;
	}
	
}