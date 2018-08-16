package com.briup.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DiskSax {

	public static void main(String[] args) {
	
		//������������
		SAXParserFactory factory=SAXParserFactory.newInstance();//ctrl+1���try/catch
		//����������
		try {
			SAXParser parser=factory.newSAXParser();
			//File file=new File("src/com/briup/xml/disk.xml");//���·����src��ʼ  ����·�� ����ļ�
		    File file=new File("D:/������ѵ/Project/HDJD-EMDC/src/com/briup/xml/disk.xml");
			/**
			 * ����parse�������н���
			 * ��һ������ʾ��Ҫ�������ļ�
			 * �ڶ���������ʾ���������ļ���ִ�ж���
			 */
		    parser.parse(file,new MyHandler());
		  //  System.out.println(file.exists());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				

	}

}


class MyHandler extends DefaultHandler{
   //alt+shift+s ��д���� ����set get
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//����������ʼ��ǩ
		//��һ������ url ��Ϊ��������ַ �ڶ����������ص�ַ  ��������������ʼ��ǩ��    ���ĸ����� ����ǩ���������
		System.out.print("<"+qName);
		//��������
		for(int i=0;i<attributes.getLength();i++){
			//getQName()��ȡ������
			String name=attributes.getQName(i);
			//getValue()��ȡ����ֵ
			String value=attributes.getValue(name);
			System.out.print(" "+name+"='"+value+"'");
			
		}
		System.out.print(">");
		
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//��������������ǩ
		System.out.print("</"+qName+">");
		
	}

	//characters������ǩ�м��������ӿո�
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str=new String(ch,start,length);//����һ���µ� String��������ȡ���ַ��������һ����������ַ���
		//��һ��������ʾ�ַ����� �ڶ����� 
		//�ڶ���������ʾ ��ʼ��ȡ�ַ������λ��
		//������������ʾ �ӿ�ʼ��ȡ��λ�ö�ȡ�ĸ���
		System.out.print(str);
		
	}
	
}