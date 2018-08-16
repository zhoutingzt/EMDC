package com.briup.environment.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.client.GatherImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;

public class DBStoreImplzt implements DBStore{

	private static Log log=new LogImpl();
	@Override
	public void savetoDB(Collection<Environment> c) throws Exception {

		//数据入库
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="envir";
		String password="envir";
		Class.forName(driver);
		Connection connection=DriverManager.getConnection(url,user,password);
	    log.info("数据库连接成功");
		//System.out.println("数据库连接成功");
		String sql="insert into e_detail_1 values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre=connection.prepareStatement(sql);
		int count=0;
        for (Environment environment : c) {
			pre.setString(1, environment.getName());
			pre.setString(2, environment.getSrcId());
			pre.setString(3, environment.getDesId());
			pre.setString(4, environment.getSersorAddress());
			pre.setInt(5, environment.getCount());
			pre.setString(6, environment.getCmd());
			pre.setInt(7, environment.getStatus());
			pre.setFloat(8, environment.getData());
			pre.setTimestamp(9, environment.getGather_date());
			pre.addBatch();
			count++;
			if((count%1000)==0){
				pre.executeBatch();
				pre.clearBatch();
			}
			
		}
        pre.executeBatch();
	}
	@Override
	public void init(Properties properties) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setConfiguration(Configuration configuration) {
		// TODO Auto-generated method stub
		
	}
		
public static void main(String[] args) {
//	try {
//		Collection<Environment> collection=new GatherImpl().gather();
//		new DBStoreImpl().savetoDB(collection);
//		System.out.println("入库成功");
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}

}
