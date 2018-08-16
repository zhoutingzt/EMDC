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


public class ClientServer {//�ܵ������������
 public static void main(String[] args) {
	PipedInputStream pis=new PipedInputStream();
	try {
		PipedOutputStream pos=new PipedOutputStream(pis);//�������ӵ�ָ���ܵ��������Ĺܵ��������
		new Sender(pos, "���ͷ�").start();
		new Fecher(pis, "���շ�").start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
}
//���ͷ�
class Sender extends Thread{
	PipedOutputStream pos=null;
	public Sender(PipedOutputStream pos,String name){//���������ʱ�� ��ӵ�йܵ� 
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
			System.out.println(getName()+"�������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
//���շ�
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
			System.out.println(getName()+"���ݽ�����ϣ���ʼ��ӡ");
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

	//��Ȼ���� ��ǰ����Ƚϴ����������
	//�������Ƚϵ�ǰ������
	@Override
	public int compareTo(Object o) {
		Teacher teacher=(Teacher) o;
		//return this.age-teacher.age;
		//����
		return this.age>=teacher.age?1:-1;
	}
	
}