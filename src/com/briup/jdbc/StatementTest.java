package com.briup.jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

	public static void main(String[] args) {
		//�����ӹ����л�ȡ���Ӷ���
		Connection connection=ConnectionFactory.getConnection();
		try {
			//����statement��������sql��䷢�����ݿ�
			Statement statement= connection.createStatement();
			String name="tom";
			String pwd="234";
			//select * from student where name='name' and pwd='tom';
			String sql="select * from student where name='"+name+"'and pwd='"+pwd+"'";
			System.out.println(sql);
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String n=rs.getString(2);
				String p=rs.getString(3);
				System.out.println("id="+id+"name="+n+"  password="+p);
			}
			System.out.println("ִ�����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
