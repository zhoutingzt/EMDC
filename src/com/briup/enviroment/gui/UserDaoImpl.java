package com.briup.enviroment.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDaoImpl implements UserDao {

	private static Connection connection;
	static{
		connection=ConnectionFactory1.getConnection();
	}
	@Override
	public void save(User user) throws Exception {
		String sql="insert into u values(?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setInt(1, user.getId());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPwd());
		ps.setString(4, user.getGender());
		ps.setString(5, user.getInfo());
		ps.executeUpdate();
	}
	@Override
	public User find(String name) throws Exception {
		String sql="select * from u where username=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		User user=null;
		while(rs.next()){
			user=new User();
			user.setUsername(rs.getString("username"));
			user.setPwd(rs.getString("pwd"));
			user.setGender(rs.getString("gender"));
			user.setInfo(rs.getString("info"));;
		}
		return user;
	}
}
