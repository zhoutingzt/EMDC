package com.briup.environment.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


//数据库连接工具
public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Properties prop;
    static {
        try {
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop = new Properties();
            prop.load(is);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url,username,password);
    }

}
