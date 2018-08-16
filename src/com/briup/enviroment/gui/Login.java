package com.briup.enviroment.gui;
/*
 * Login接口规定了登录模块所应有的方法，当Login执行login方法时，出现登录界面客户端
 * 管理员信息验证通过可以进入物联网数据中心项目后台操作界面
 */
public interface Login {
	/*
	 * 管理员信息验证通过进入物联网数据中心
	 * 后台操作界面，按照时间和环境种类查询和统计采集数据
	 */
   public void login() throws Exception;
	
}
