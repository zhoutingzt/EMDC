package com.briup.net;

import java.io.Serializable;
import java.util.Comparator;
/*
 * 序列化：把对象转换为字节序列的过程称为对象的序列化。
 *反序列化：把字节序列恢复为对象的过程称为对象的反序列化。
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
//		//表示升序  如果将1改为-1 -1改为1的话为降序
//		if(s1.getScore()>s2.getScore()){
//			return 1;
//		}else if(s1.getScore()<s2.getScore()) {
//			return -1;
//		}else{
//			return 0;
//		}

	//}
	
    
	
}
