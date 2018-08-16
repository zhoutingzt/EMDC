package com.briup.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchTest2 {

	/*
	 * jdbc���Զ������ύ�ģ���ˣ�ִ��һ��sql���֮��sql�������Զ������ݲ��뵽���ݿ⣬���������Զ��ύ�������ϣ��sql�����Զ��ύ�����ã�
	 *	connection.setAutoCommint(false);
	 * �����Ҫ���û��ֶ����ύ����connection.commit();
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
			    System.out.println("���룺"+i);
			    if(i%300==0){
			    	//��������ﵽ300���ύ
			    	ps.executeBatch();
			    	ps.clearBatch();
			    }
			    
			}
			//�������������ݼ�ʹû��300��ҲҪ�ύ
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
