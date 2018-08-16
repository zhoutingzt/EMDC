package com.briup.xml;

public class Employee {
	private int id;
	private String name;
	private int age;
	private String gender;
	private int salary;
	private String depName;
	private String email;
	public Employee() {
		
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, String name, int age, String gender, int salary,
			String depName, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.depName = depName;
		this.email = email;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + ", salary=" + salary + ", depName="
				+ depName + ", email=" + email + "]";
	}
	
	
	

}
