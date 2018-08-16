package com.briup.enviroment.gui;
/*
 * 该接口的实现类，可以持久化user对象和按照名称查找该用户是否存在
 */
public interface UserDao {

	//对user对象持久化操作
	public void save(User user) throws Exception;
	//根据名称查找user对象
	public User find(String name) throws Exception;
}
