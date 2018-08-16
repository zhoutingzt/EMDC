package com.briup.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EmployeeDom {
	private static List<Employee> list=new ArrayList<Employee>();
	private static Employee employee; 
	public static void main(String[] args) {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder=factory.newDocumentBuilder();
			File file=new File("src/com/briup/xml/employee.xml");
			Document document=builder.parse(file);
			NodeList nl=document.getElementsByTagName("employee");
			for(int i=0;i<nl.getLength();i++){
				employee=new Employee();
				//��ΪNode�ӿ�ʵ�ֵ��ӽӿ���element ���Կ���ת����element
				Element element=(Element) nl.item(i);//���ظ� NodeList �� index λ�ô��Ľڵ㣻�����������Ч�������򷵻� null��
				int id=Integer.parseInt(element.getAttribute("id"));
				String depName=element.getAttribute("depName");
				employee.setId(id);
				employee.setDepName(depName);
				//System.out.println(employee);
				NodeList nl2=nl.item(i).getChildNodes();//��ȡemployee���ӽڵ� ��Ϊ�հ׺�Ԫ�ؽڵ�
				for(int j=0;j<nl2.getLength();j++){
					if(nl2.item(j).getNodeType()==Node.ELEMENT_NODE){//Node.ELEMENT_NODE=1�ж��Ƿ�ΪԪ�ؽڵ�
						String tagName=nl2.item(j).getNodeName();
						String str=nl2.item(j).getTextContent();
						//System.out.println(str);
						if("name".equals(tagName)){
							System.out.println(str);
							employee.setName(str);
							//tagName=null;
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
				}
				list.add(employee);
				
			}
			System.out.println(list.size());
			
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
