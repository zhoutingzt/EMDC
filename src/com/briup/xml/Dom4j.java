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
		//1.获取SASReader解析
		SAXReader reader=new SAXReader();
		List<Student> list=null;
		try {
			//2.获取文档对象
			Document document= reader.read("src/com/briup/xml/student.xml");
			//3.获取根节点
		    Element root=document.getRootElement();
		     //4.获取根节点下的所有的子节点
		      Iterator<Element> it=root.elementIterator();
		      //迭代获取所有的子节点
		      list=new ArrayList<Student>();
		    
		      while(it.hasNext()){//使用hasNext()检查序列中是否还有元素。
		    	  //根节点下面的二级节点对象
		    	  Element next=it.next();//使用next()获得序列中的下一个元素。
		    	  //第一次调用Iterator的next()方法时，它返回序列的第一个元素
		    	//构建一个空的student对象
		    	  Student student=new Student();
		    	  int id=Integer.parseInt(next.attributeValue("id"));//把属性也当成一个了二级子节点了吗？
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
