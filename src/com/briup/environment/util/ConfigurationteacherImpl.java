package com.briup.environment.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;
import com.briup.environment.gui.Login;

public class ConfigurationteacherImpl implements Configuration{

	private Map<String, EmdcModule> mapEmd=new HashMap<String,EmdcModule>();
	public ConfigurationteacherImpl() throws Exception{
		this("src/config.xml");
	}
	public ConfigurationteacherImpl(String filePath) throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		File file=new File(filePath);
		Document document=builder.parse(file);
		/*
		 * 1.获取根节点
		 * 2.获取根节点之后所有的子节点Nodelist（含文空白节点和属性节点）
		 * 3.遍历子节点，根据子节点类型获取元素节点
		 * 4.遍历元素节点，获取节点名称和属性节点的值
		 * 5.将class属性节点的值进行实例化（通过反射）
		 * 6.map按照元素节点名称为key,实例化对象为value 进行保存
		 */
		Element e=document.getDocumentElement();
		NodeList nl=e.getChildNodes();
		for(int i=0;i<nl.getLength();i++){
			if(nl.item(i).getNodeType()==1){
				Element e2=(Element) nl.item(i);
				String tagName=e2.getNodeName();
				String attValue=e2.getAttribute("class");
				System.out.println(tagName+" "+attValue);
				EmdcModule module=(EmdcModule) Class.forName(attValue).newInstance();
				mapEmd.put(tagName, module);
				/*
				 * 1.获取当前节点的子节点，构建properti对象
				 * 2.根据节点类型判断获取元素节点
				 * 3.获取元素节点的名称为key,文本节点值为value
				 * 4.properties按照节点名称为key和文本节点为value进行保存
				 * 5.EmdcModule调用init方法保存properties对象
				 */
				NodeList nl2=nl.item(i).getChildNodes();
				Properties properties=new Properties();
				for(int j=0;j<nl2.getLength();j++){
					if(nl2.item(j).getNodeType()==1){
						Element e3=(Element) nl2.item(j);
						String tagName2=e3.getNodeName();
						String tagValue2=e3.getTextContent();
						System.out.println(tagName2+"="+tagValue2);
						properties.put(tagName2, tagValue2);
						
					}
				}
				module.init(properties);
			}
		}
		for(Object obj:mapEmd.values()){//重点 第三步
			//if(obj instanceof EmdcModule){
				((EmdcModule) obj).setConfiguration(this);
			//}
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
		// TODO Auto-generated method stub
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
	@Override
	public Login getLogin() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
  public static void main(String[] args) throws Exception{
	ConfigurationteacherImpl c=new ConfigurationteacherImpl();
	System.out.println(c.getGather());
	System.out.println(c.getClient());
	System.out.println(c.getBackup());
	System.out.println(c.getDbStore());
	System.out.println(c.getLog());
	System.out.println(c.getServer());
}

}
