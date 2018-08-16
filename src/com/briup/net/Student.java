package com.briup.net;

import java.io.Serializable;
import java.util.Comparator;
/*
 * ���л����Ѷ���ת��Ϊ�ֽ����еĹ��̳�Ϊ��������л���
 *�����л������ֽ����лָ�Ϊ����Ĺ��̳�Ϊ����ķ����л���
 */
public class Student implements Serializable{//,Comparator
    private int id;
    private String name;
    private int age;
    private String gender;
    private int score;
	public Student() {
	}
	public Student(int id, String name, int age, String gender, int score) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + ", score=" + score + "]";
	}
	///////
//	@Override
//	public int compare(Object o1, Object o2) {
//		Student s1=(Student) o1;
//		Student s2=(Student) o2;
//		//��ʾ����  �����1��Ϊ-1 -1��Ϊ1�Ļ�Ϊ����
//		if(s1.getScore()>s2.getScore()){
//			return 1;
//		}else if(s1.getScore()<s2.getScore()) {
//			return -1;
//		}else{
//			return 0;
//		}

	//}
	
    
	
}
