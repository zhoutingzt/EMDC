package com.briup.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.activation.FileDataSource;

public class ReflectTest {

	public static void main(String[] args){
		try {
			Class cls=Class.forName("com.briup.reflect.Person");//���class���󼴾��� ����ͨ�����ַ�ʽ��� :�ٶ���.getClass����Class.forName������.class
			String name=cls.getName();//��þ��������
			System.out.println(name);
			
			String modifiler=Modifier.toString(cls.getModifiers());//cls.getModifiers(����õ������η������ֱ�ʾ��ʽ
			
			System.out.println(modifiler+"class "+name+"{");
			getFiled(cls);//�������
			getConstruct(cls);//��ù��캯��
			getMethod(cls);//�����ͨ����
			Person person=(Person) cls.newInstance();//ͨ��������һ��ʵ��
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
			String modName=Modifier.toString(fields[i].getModifiers());//fields[i].getModifiers()���ص������η���������ʾ
			String typeName=fields[i].getType().getName();//getType()  ����һ�� Class ��������ʶ�˴� Field ��������ʾ�ֶε��������͡�
			//System.out.println("DFAAFA"+fields[i].getType());//����Ľ��Ϊ int
			String fieldName=fields[i].getName();
			System.out.println("\t"+modName+""+typeName+" "+fieldName+";");
		}
	}
	public static void getConstruct(Class cls){
		Constructor constructors[]=cls.getConstructors();
		for(int i=0;i<constructors.length;i++){
			String modName=Modifier.toString(constructors[i].getModifiers());//ֻ��Modifier�е�toString()�������ܽ�ת�����η�
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
			String modName=Modifier.toString(methods[i].getModifiers());//���η�
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
	public String toString() {//ָ�������ʽ ��Ȼ�����accsic��
		return "Person [name=" + name + ", age=" + age + ", score=" + score
				+ "]";
	}
	
	
}
