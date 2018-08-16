package com.briup.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.activation.FileDataSource;

public class ReflectTest {

	public static void main(String[] args){
		try {
			Class cls=Class.forName("com.briup.reflect.Person");//获得class对象即镜像 可以通过三种方式获得 :①对象.getClass、②Class.forName、③类.class
			String name=cls.getName();//获得镜像的名称
			System.out.println(name);
			
			String modifiler=Modifier.toString(cls.getModifiers());//cls.getModifiers(）获得的是修饰符的数字表示形式
			
			System.out.println(modifiler+"class "+name+"{");
			getFiled(cls);//获得属性
			getConstruct(cls);//获得构造函数
			getMethod(cls);//获得普通方法
			Person person=(Person) cls.newInstance();//通过镜像获得一个实例
			person.name="tom";
			person.age=20;
			person.setScore(59);
			System.out.println(person);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void getFiled(Class cls){
		Field fields[]=cls.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			String modName=Modifier.toString(fields[i].getModifiers());//fields[i].getModifiers()返回的是修饰符的整数表示
			String typeName=fields[i].getType().getName();//getType()  返回一个 Class 对象，它标识了此 Field 对象所表示字段的声明类型。
			//System.out.println("DFAAFA"+fields[i].getType());//输出的结果为 int
			String fieldName=fields[i].getName();
			System.out.println("\t"+modName+""+typeName+" "+fieldName+";");
		}
	}
	public static void getConstruct(Class cls){
		Constructor constructors[]=cls.getConstructors();
		for(int i=0;i<constructors.length;i++){
			String modName=Modifier.toString(constructors[i].getModifiers());//只有Modifier中的toString()方法才能将转换修饰符
			String conName=constructors[i].getName();
			System.out.print("\t"+modName+" "+conName+"(");
			Class c[]=constructors[i].getParameterTypes();
			for(int j=0;j<c.length;j++){
				String paramName=c[j].getName();
				if(j>0)//{
					System.out.print(",");
				//}else{
				System.out.print(paramName);
				//}
			//	System.out.println(paramName+",");
				
			}
			System.out.println("){}");
			
		}
	}
	public static void getMethod(Class cls){
		Method methods[]=cls.getDeclaredMethods();
		for(int i=0;i<methods.length;i++){
			String modName=Modifier.toString(methods[i].getModifiers());//修饰符
			String typeName=methods[i].getReturnType().getName();
			String methodName=methods[i].getName();
			System.out.print("\t"+modName+" "+typeName+" "+methodName+"(");
			//System.out.println("){....}");
			Class c[]=methods[i].getParameterTypes();
			for(int j=0;j<c.length;j++){
				String paraName=c[j].getName();
			    if(j>0)
			    	System.out.print(",");
			    System.out.print(paraName);
			}
			System.out.println("){...}");
		}
	}
}
class Person{
	public String name;
	public int age;
	public int score;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Person() {
	
	}
	public Person(String name, int age, int score) {
		
		this.name = name;
		this.age = age;
		this.score = score;
	}
	@Override
	public String toString() {//指定输出格式 不然输出的accsic码
		return "Person [name=" + name + ", age=" + age + ", score=" + score
				+ "]";
	}
	
	
}
