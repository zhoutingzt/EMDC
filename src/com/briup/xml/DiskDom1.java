package com.briup.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DiskDom1 {

	public static void main(String[] args) {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			//����������
			DocumentBuilder builder=factory.newDocumentBuilder();
			File file =new File("src/com/briup/xml/disk.xml");
			Document document=builder.parse(file);
		
			NodeList nlList=document.getElementsByTagName("disk");
			//disks�������ֽڵ�
			for(int i=0;i<nlList.getLength();i++){
				Node node=nlList.item(i);//����һ��node ������һ���ڵ�
	//��	   // Node node2=node.getFirstChild();
			//	System.out.println(node2);
//				System.out.println(node2.getNodeName());
//				System.out.println(node2.getNodeValue());
//				System.out.println(node2.getNodeType());
		
				//�ڻ�ȡ���� ʹ����ԭʼ�Ķ��� ��api��� disk
				NamedNodeMap nnm=node.getAttributes();
				for(int j=0;j<nnm.getLength();j++){
					 Node node2=nnm.item(j);
				        System.out.println(node2);
				        System.out.println(node2.getNodeName());
				        System.out.println(node2.getNodeValue());
				        System.out.println(node2.getNodeType());
				}
		       
				///�� disks
//				Node node2=node.getFirstChild().getNextSibling();//<disk>
//				System.out.println(node2);
//				Node node3=node2.getFirstChild().getNextSibling();//<size>
//				System.out.println(node3);
//				String value=node3.getFirstChild().getNodeValue();
//				String name=node3.getNodeName();
//				String value1=node3.getTextContent();//getTextContent()ֻ�����ڸýڵ����ֻ���ı����� ��<size>10G</size>
//				//getTextContent()   �����Է��ش˽ڵ㼰�������ı����ݡ�
//				System.out.println(name+"="+value);
//				System.out.println(value1);
				
				//��disk
				//����Node ���ӽӿ���Element
//				Element e=(Element) nlList.item(i);
//				String name=e.getAttribute("name");
//				System.out.println(name);
//				String size=e.getElementsByTagName("size").item(0).getTextContent();//e���Ը�Ϊdocument �ڰ�0�ĳ�i
//				//�õ�document�൱���õ��������ĵ�
//				String directory=e.getElementsByTagName("directory").item(0).getTextContent();
//				String f=e.getElementsByTagName("file").item(0).getTextContent();
//				
//				System.out.println(name+"size:"+size+" directory:"+directory+" file:"+f);
			
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
