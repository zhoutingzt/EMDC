package com.briup.xml;

import java.io.File;




import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       SAXParserFactory factory=SAXParserFactory.newInstance();
       try {
		SAXParser parser=factory.newSAXParser();
		File file=new File("src/com/briup/xml/parsertest.xml");
		//System.out.println(file.exists());
		
		parser.parse(file, new MyHander());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
		
	}

}
class MyHander extends DefaultHandler{

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		System.out.print(qName+"=");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String str=new String(ch,start,length);
		System.out.print(str);
		
	}
	
}