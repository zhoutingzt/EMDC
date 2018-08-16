package com.briup.environment.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;
import com.briup.environment.gui.Login;

public class ConfigurationztImpl implements Configuration{
	private Map<String, EmdcModule> mapEmd=new HashMap<String,EmdcModule>();
	public ConfigurationztImpl() throws Exception{
		this("src/config.xml");
	}
/*
 * 使用dom4j进行解析
 */
	public ConfigurationztImpl(String filePath)throws Exception {
		SAXReader reader=new SAXReader();
		try {
			//获取文档节点，获取document对象
			Document document=reader.read(filePath);
			//获取根节点
			Element root=document.getRootElement();
			System.out.println("根节点为："+root.getName());
			//遍历根节点下面的一级子节点
			Iterator<Element> it=root.elementIterator();
			while(it.hasNext()){
				Element element=it.next();
				String cname=element.attributeValue("class");
				EmdcModule eModule=(EmdcModule) Class.forName(cname).newInstance();
				Properties properties=new Properties();
				Iterator<Element> it2=element.elementIterator();
				while(it2.hasNext()){
					Element element2=it2.next();
					String pkey=element2.getName();
					String pvalue=element2.getText();
					properties.put(pkey, pvalue);
					System.out.println(element2.getName()+"="+element2.getStringValue());
				}
				eModule.init(properties);
				mapEmd.put(element.getName(),eModule);
				for(Object obj:mapEmd.values()){
					if(obj instanceof EmdcModule){
						( (EmdcModule) obj).setConfiguration(this);
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Log getLog() throws Exception {
		// TODO Auto-generated method stub
		return (Log) mapEmd.get("log");
	}

	@Override
	public BackUP getBackup() throws Exception {
		// TODO Auto-generated method stub
		return (BackUP) mapEmd.get("backup");
	}

	@Override
	public Gather getGather() throws Exception {
		
		return (Gather) mapEmd.get("gather");
	}

	@Override
	public Server getServer() throws Exception {
		// TODO Auto-generated method stub
		return (Server) mapEmd.get("server");
	}

	@Override
	public Client getClient() throws Exception {
		// TODO Auto-generated method stub
		return (Client) mapEmd.get("client");
	}

	@Override
	public DBStore getDbStore() throws Exception {
		// TODO Auto-generated method stub
		return (DBStore) mapEmd.get("dbstore");
	} 
	
	  public static void main(String[] args)throws Exception {
		ConfigurationztImpl c=new ConfigurationztImpl();
		System.out.println(c.getServer());
		System.out.println(c.getDbStore());
		System.out.println(c.getClient());
		System.out.println(c.getGather());
		System.out.println(c.getLog());
		System.out.println(c.getBackup());
		System.out.println(c.getLogin());
	}

	@Override
	public Login getLogin() throws Exception {
		// TODO Auto-generated method stub
		return (Login) mapEmd.get("login");
	}
}
