package com.briup.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DiskSax {

	public static void main(String[] args) {
	
		//构建解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();//ctrl+1添加try/catch
		//构建解析器
		try {
			SAXParser parser=factory.newSAXParser();
			//File file=new File("src/com/briup/xml/disk.xml");//相对路径从src开始  绝对路径 点击文件
		    File file=new File("D:/杰普培训/Project/HDJD-EMDC/src/com/briup/xml/disk.xml");
			/**
			 * 调用parse方法进行解析
			 * 第一参数表示需要解析的文件
			 * 第二个参数表示真正解析文件的执行对象
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
   //alt+shift+s 重写方法 生成set get
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//用来解析开始标签
		//第一个参数 url 即为服务器地址 第二参数：本地地址  第三个参数：开始标签名    第四个参数 ：标签里面的属性
		System.out.print("<"+qName);
		//处理属性
		for(int i=0;i<attributes.getLength();i++){
			//getQName()获取属性名
			String name=attributes.getQName(i);
			//getValue()获取属性值
			String value=attributes.getValue(name);
			System.out.print(" "+name+"='"+value+"'");
			
		}
		System.out.print(">");
		
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//用来解析结束标签
		System.out.print("</"+qName+">");
		
	}

	//characters解析标签中间的内容外加空格
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str=new String(ch,start,length);//分配一个新的 String，它包含取自字符数组参数一个子数组的字符。
		//第一个参数表示字符数组 第二参数 
		//第二个参数表示 开始读取字符数组的位置
		//第三个参数表示 从开始读取的位置读取的个数
		System.out.print(str);
		
	}
	
}