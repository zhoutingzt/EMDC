package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchTest2 {

	/*
	 * jdbc是自动事务提交的，因此，执行一条sql语句之后，sql语句可以自动将数据插入到数据库，并且事务自动提交。如果不希望sql事务自动提交，设置：
	 *	connection.setAutoCommint(false);
	 * 如此需要在用户手动的提交事务。connection.commit();
	 */
	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection();
		String sql="insert into student values(?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			for(int i=0;i<500;i++){
				ps.setInt(1,i);
				ps.setString(2,"briup"+i);
				ps.setString(3,"000");
				ps.addBatch();
			    System.out.println("插入："+i);
			    if(i%300==0){
			    	//缓存池数达到300条提交
			    	ps.executeBatch();
			    	ps.clearBatch();
			    }
			    
			}
			//缓存池数最后数据即使没有300条也要提交
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
