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
    private List<Employee> list;//������private���� ����Ҫ�ṩһ��get set ����
    private Employee employee;
    private String tagName;//Ҫ�������������þ���Ҫ����һ����Ա���� tagNameֻ��Ҫ��ȡname,age,gender,salary,email�⼸����ʼ��ǩ 
	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	@Override
	public void startDocument() throws SAXException {
		//����list����  һ��ʼ����ʱ���Ҫ��������
		list=new ArrayList<Employee>();
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//System.out.println();
		if("employee".equals(qName)){//����������ǩ���뼯��
			list.add(employee);
		}
		//����������ǩ����tagNameΪ�� ��ֹ���θ�������ֵ
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
		if("employee".equals(qName)){//�����ǩΪemployee�Ļ��ʹ���employee����
			employee=new Employee();
			int id=Integer.parseInt(attributes.getValue("id"));//getValue(int index)   ͨ�������������Ե�ֵ��
			String depName=attributes.getValue("depName");
			employee.setId(id);
		    employee.setDepName(depName);
		}else{//����������ԵĻ� ����tagName��¼������ǩ	<name>tom</name>
		   
			tagName=qName;
			
		}
	}
	
	
	   
}