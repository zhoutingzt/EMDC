package com.briup.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest1 {

	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection();
		try {
			Statement statement=connection.createStatement();
			String sql1="insert into student values(3,'jack','123')";
			String sql2="update student set name='rose'";
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			//执行一次
			statement.executeBatch();
			System.out.println("执行完成");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
