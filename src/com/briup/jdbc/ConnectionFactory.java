package com.briup.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/*
 * static:对象之间共享值 方便访问对象
 */
public class ConnectionFactory {
	//注意一定要导入ojdbc.jar包 不然是连接不到数据库的
	/*连接数据库的步骤：
	 * 1,加载驱动 class.forName(driver)
	 * 2,获取数据库的连接DriverManager.getConnection(url,username,password)
	 * （前提提供了url username password）
	 * 3,创建sql语句
	 * 4，执行sql语句
	 * 5，处理结果
	 * 6.关闭资源
	 */
	//定义为静态的话 就可以在加载类的时候自动运行
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
		  //load加载需要读取的文件
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
		System.out.println(getConnection());//静态方法需要在主方法中调用才能执行
	}
}
