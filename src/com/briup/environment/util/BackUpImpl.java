package com.briup.environment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class BackUpImpl implements BackUP{

	private  static Configuration configuration;
	private String backfile="back_temp";
	/*
	 * 该方法用于备份数据
	 */
	@Override
	public void store(String filePath, Object obj, boolean append)
			throws Exception {
		File file=new File(backfile);
		if(!file.exists()){
			file.mkdir();//直接在项目的下面创建back_temp目录
		}
		File file2=new File(file,filePath);//在back_temp目录创建要备份的文件
		FileOutputStream fos=new FileOutputStream(file2,append);// 创建一个向指定file2 对象表示的文件中写入数据的文件输出流。
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		fos.close();
	}

	@Override
	public Object load(String filePath, boolean del) throws Exception {
		//先看看有没有备份的目录
		File file=new File(backfile);
		if(!file.exists()){
			return null;
		}
		//在file所在的目录下是否含filePath文件
		File file2=new File(file,filePath);
		if(!file2.exists()){
			return null;
			
		}
		//含file2文件进行 读取
		Object data=null;
		FileInputStream fis=new FileInputStream(file2);
		ObjectInputStream ois=new ObjectInputStream(fis);
		data=ois.readObject();
		ois.close();
		fis.close();
		if(del){
			file2.delete();
		}
		return data;
	}

	@Override
	public void init(Properties properties) throws Exception {
		backfile=properties.getProperty("backup-file");
		
	}
	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration=configuration;

	}
	public static void main(String[] args) throws Exception{
		configuration=new ConfigurationImpl();
		BackUpImpl backup=(BackUpImpl) configuration.getBackup();
		//BackUpImpl backup=new BackUpImpl();
		backup.store("a.txt","1234", true);
        Object obj=backup.load("a.txt",false);
        System.out.println(obj);
	}

	
}
