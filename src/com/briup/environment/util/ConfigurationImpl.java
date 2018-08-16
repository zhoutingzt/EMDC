package com.briup.environment.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.gui.Login;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;


public class ConfigurationImpl implements Configuration{
	/*
	 * 读取config.xml内容，将标签作为key，类的实例对象作为值存储到map集合中
	 */
   private Map<String, EmdcModule> mapEmdc=new HashMap<String, EmdcModule>();
   //通过new无参构造器 就会找到有参构造 对象的时候 
   public ConfigurationImpl(){
	   this("src/config.xml");
   }
	
	public ConfigurationImpl(String filePath){
	/*
	 * 使用dom解析config.xml配置文件
	 */
	    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder=factory.newDocumentBuilder();
			File file=new File(filePath);
			Document document=builder.parse(file);
			NodeList nodelist1=document.getElementsByTagName("emdc");
			//System.out.println("nodelist1大小："+nodelist1.getLength());
			for(int i=0;i<nodelist1.getLength();i++){
				NodeList nodelist2=nodelist1.item(i).getChildNodes();
				for(int j=0;j<nodelist2.getLength();j++){
					//System.out.println("nodelist2大小："+nodelist2.getLength());
					if(nodelist2.item(j).getNodeType()==Node.ELEMENT_NODE){
						System.out.println("节点名2"+nodelist2.item(j).getNodeName());
						Element element=(Element) nodelist2.item(j);
						String cname=element.getAttribute("class");
						System.out.println("类名："+cname);
						EmdcModule emdcModule=(EmdcModule) Class.forName(cname).newInstance();
						Properties properties=new Properties();
						NodeList nodelist3=nodelist2.item(j).getChildNodes();
					//	System.out.println("nodelist3的大小："+nodelist3.getLength());
						mapEmdc.put(element.getNodeName(),emdcModule);//////
						for(int m=0;m<nodelist3.getLength();m++){
							if(nodelist3.item(m).getNodeType()==Node.ELEMENT_NODE){
							//	System.out.println("1234");
							    Element element2=(Element) nodelist3.item(m);
								String pkey=element2.getNodeName();
								String pvalue=element2.getTextContent();
								properties.put(pkey, pvalue);
								System.out.println(pkey+"="+pvalue);
							//	System.out.println("1234");
							}
						}
						emdcModule.init(properties);//第一步
						//mapEmdc.put(element.getNodeName(),emdcModule);
					}	
				}
			}
			for(Object obj:mapEmdc.values()){//重点 第三步
				if(obj instanceof EmdcModule){//判断obj是不是EmdcModule的一个实例
					((EmdcModule) obj).setConfiguration(this);
				}
			}
	       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//第二步
	@Override
	public Log getLog() throws Exception {
		// TODO Auto-generated method stub
		return (Log) mapEmdc.get("log");
	}

	@Override
	public BackUP getBackup() throws Exception {
		// TODO Auto-generated method stub
		return (BackUP) mapEmdc.get("backup");
	}

	@Override
	public Gather getGather() throws Exception {
		// TODO Auto-generated method stub
		return (Gather) mapEmdc.get("gather");
	}

	@Override
	public Server getServer() throws Exception {
		// TODO Auto-generated method stub
		return (Server) mapEmdc.get("server");
	}

	@Override
	public Client getClient() throws Exception {
		// TODO Auto-generated method stub
		return (Client) mapEmdc.get("client");
	}

	@Override
	public DBStore getDbStore() throws Exception {
		// TODO Auto-generated method stub
		return (DBStore) mapEmdc.get("dbstore");
	}
	@Override
	public Login getLogin() throws Exception {
		// TODO Auto-generated method stub
		return (Login) mapEmdc.get("login");
	}
  public static void main(String[] args) throws Exception {
	  ConfigurationImpl c=new ConfigurationImpl();
		System.out.println(c.getServer());
		System.out.println(c.getDbStore());
		System.out.println(c.getClient());
		System.out.println(c.getGather());
		System.out.println(c.getLog());
		System.out.println(c.getBackup());
		System.out.println(c.getLogin());
}


	
}
