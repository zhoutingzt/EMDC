package com.briup.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4j {

	public static void main(String[] args) {
		//1.��ȡSASReader����
		SAXReader reader=new SAXReader();
		List<Student> list=null;
		try {
			//2.��ȡ�ĵ�����
			Document document= reader.read("src/com/briup/xml/student.xml");
			//3.��ȡ���ڵ�
		    Element root=document.getRootElement();
		     //4.��ȡ���ڵ��µ����е��ӽڵ�
		      Iterator<Element> it=root.elementIterator();
		      //������ȡ���е��ӽڵ�
		      list=new ArrayList<Student>();
		    
		      while(it.hasNext()){//ʹ��hasNext()����������Ƿ���Ԫ�ء�
		    	  //���ڵ�����Ķ����ڵ����
		    	  Element next=it.next();//ʹ��next()��������е���һ��Ԫ�ء�
		    	  //��һ�ε���Iterator��next()����ʱ�����������еĵ�һ��Ԫ��
		    	//����һ���յ�student����
		    	  Student student=new Student();
		    	  int id=Integer.parseInt(next.attributeValue("id"));//������Ҳ����һ���˶����ӽڵ�����
		    	  String name=next.elementText("name");
		    	  String gender=next.elementText("gender");
		    	  int age=Integer.parseInt(next.elementText("age"));
		    	  student.setId(id);
                  student.setAge(age);
		    	  student.setGender(gender); 
		    	  student.setName(name);
		    	  list.add(student);
		      }
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Student student : list) {
			System.out.println(student);
		}

	}

}
