package com.briup.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/*
 * static:����֮�乲��ֵ ������ʶ���
 */
public class ConnectionFactory {
	//ע��һ��Ҫ����ojdbc.jar�� ��Ȼ�����Ӳ������ݿ��
	/*�������ݿ�Ĳ��裺
	 * 1,�������� class.forName(driver)
	 * 2,��ȡ���ݿ������DriverManager.getConnection(url,username,password)
	 * ��ǰ���ṩ��url username password��
	 * 3,����sql���
	 * 4��ִ��sql���
	 * 5��������
	 * 6.�ر���Դ
	 */
	//����Ϊ��̬�Ļ� �Ϳ����ڼ������ʱ���Զ�����
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Properties properties;
	private static Connection connection;

	static{
		properties=new Properties();
		try {
			InputStream is=new FileInputStream(new File("src/jdbc.properties"));
		  //load������Ҫ��ȡ���ļ�
			properties.load(is);
			driver=properties.getProperty("driver");
			url=properties.getProperty("url");
			username=properties.getProperty("username");
			password=properties.getProperty("password");
			System.out.println(driver+"="+url+"="+username+"="+password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection(){
		try {
			
			Class.forName(driver);
			connection=DriverManager.getConnection(url,username,password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());//��̬������Ҫ���������е��ò���ִ��
	}
}
