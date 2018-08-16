package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PrepareStatementTest {

	public static void main(String[] args) {
		Connection connection=ConnectionFactory.getConnection();
		String sql="select * from student where name=? and pwd=?";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);//������sql
			ps.setString(1,"tom");//ͨ����1��ʼ
			ps.setString(2, "234");
			ResultSet rs=ps.executeQuery();
			//System.out.println("12324");
			while(rs.next()){
				System.out.println("id:"+rs.getInt("id"));
			}
			System.out.println("ִ�����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	

   }
}