package com.briup.xml;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeSax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SAXParserFactory factory=SAXParserFactory.newInstance();
		try {
			SAXParser parser=factory.newSAXParser();
		    File file=new File("src/com/briup/xml/employee.xml");
		    Handler handler=new Handler();
		    parser.parse(file, handler);
		    List<Employee> list=handler.getList();
		    for (Employee employee : list) {
				System.out.println(employee);
			}
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
class Handler extends DefaultHandler{
    private List<Employee> list;//由于是private修饰 所以要提供一个get set 方法
    private Employee employee;
    private String tagName;//要被俩个方法调用就需要构建一个成员变量 tagName只需要获取name,age,gender,salary,email这几个开始标签 
	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	@Override
	public void startDocument() throws SAXException {
		//构建list集合  一开始读的时候就要创建集合
		list=new ArrayList<Employee>();
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//System.out.println();
		if("employee".equals(qName)){//遇到结束标签放入集合
			list.add(employee);
		}
		//读到结束标签就让tagName为空 阻止二次给变量赋值
		tagName=null;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str=new String(ch,start,length);
		//System.out.println(str);
		if("name".equals(tagName)){
			employee.setName(str);
		}else if("age".equals(tagName)){
			employee.setAge(Integer.parseInt(str));
		}else if("gender".equals(tagName)){
			employee.setGender(str);
		}else if("email".equals(tagName)){
			employee.setEmail(str);
		}else if("salary".equals(tagName)){
			employee.setSalary(Integer.parseInt(str));
		}
	
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//
		if("employee".equals(qName)){//如果标签为employee的话就创建employee对象
			employee=new Employee();
			int id=Integer.parseInt(attributes.getValue("id"));//getValue(int index)   通过索引查找属性的值。
			String depName=attributes.getValue("depName");
			employee.setId(id);
		    employee.setDepName(depName);
		}else{//如果不是属性的话 就让tagName记录其他标签	<name>tom</name>
		   
			tagName=qName;
			
		}
	}
	
	
	   
}