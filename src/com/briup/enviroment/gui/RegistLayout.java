/*package com.briup.enviroment.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.EmdcModule;
import com.briup.environment.gui.Login;

public class RegistLayout extends JFrame implements ActionListener{
	//注册触发事件名
	 private static final String ADD= "ADD";
	 //取消触发事件名
	 private static final String CANCEL= "CANCEL";
	 private JLabel name_lab;//名字文本
	 private JLabel pwd_lab;//密码文本
	 private JLabel sex_lab;//性别文本
	 private JRadioButton man;//男
	 private JRadioButton woman;//女
	 private JLabel jLabel0;//简历文本
	 private JTextArea info_ta;//简历多行区域
	 private JScrollPane jScrollPane0;//绑定一个滚动条
	 private JTextField name_jf;//名字输入框
	 private JTextField pwd_jf;//密码输入框
	 private JButton  add;//注册按钮
	 private JButton  cancel;//取消按钮
	 private ButtonGroup bg=new ButtonGroup();//按钮组
	 private LoginImpl loginImpl;
	 
	 public static void main(String[] args){
		 new RegistLayout();
	 }
	 
	 //初始化布局
	 private void initComponents() {
		  setTitle("注册");
		  setLayout(null);
		  add(getName_lab());
		  add(getPwd_lab());
		  add(getSex_lab());
		  add(getMan());
		  add(getWoman());
		  add(getJLabel0());
		  add(getJScrollPane0());
		  add(getName_jf());
		  add(getPwd_jf());
		  add(getAdd());
		  add(getCancel());
		  setSize(316, 320);
		  setLocation(300, 200);
		  setVisible(true);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setResizable(false);
	 }
	 //初始化取消按钮
	 private JButton getCancel(){
		 if (cancel == null){
		   cancel = new JButton();
		   cancel.setText("取消");
		   cancel.setActionCommand(CANCEL);
		   cancel.setBounds(177, 203, 81, 28);
		   cancel.addActionListener(this);
		 }
		 return cancel;
	 }
	 //初始化注册按钮
	 private JButton getAdd(){
		 if (add == null){
		   add = new JButton();
		   add.setText("注册");
		   add.setActionCommand(ADD);
		   add.setBounds(69, 203, 81, 28);
		   add.addActionListener(this);
		 }
		 return add;
	 }
	 //初始化密码输入框
	 private JTextField getPwd_jf(){
		 if (pwd_jf == null){
		   pwd_jf = new JTextField();
		   pwd_jf.setText("");
		   pwd_jf.setBounds(61, 41, 178, 22);
		 }
		 return pwd_jf;
	 }
	 //初始化名字输入框
	 private JTextField getName_jf(){
		 if (name_jf == null){
		   name_jf = new JTextField();
		   name_jf.setText("");
		   name_jf.setBounds(62, 10, 178, 22);
		}
		 return name_jf;
	 }
	 //初始化滚动条
	 private JScrollPane getJScrollPane0(){
		 if (jScrollPane0 == null){
			 jScrollPane0 = new JScrollPane();
			 jScrollPane0.setBounds(63, 103, 210, 80);
			 //将滚动条添加到多行区域
			 jScrollPane0.setViewportView(getInfo_ta());
		 }
		 return jScrollPane0;
	 }
	 //初始化多行区域
	 private JTextArea getInfo_ta(){
		  if (info_ta == null){
		   info_ta = new JTextArea();
		   info_ta.setText("");
		  }
		  return info_ta;
	 }
	 //初始化简历文本
	 private JLabel getJLabel0(){
		  if (jLabel0 == null){
		   jLabel0 = new JLabel();
		   jLabel0.setText("简历");
		   jLabel0.setBounds(17, 99, 41, 18);
		  }
		  return jLabel0;
	 }
	 //初始化女按钮,并且添加到按钮组中
	 private JRadioButton getWoman() {
		  if (woman == null) {
		   woman = new JRadioButton();
		   bg.add(woman);
		   woman.setSelected(true);
		   woman.setText("女");
		   woman.setBounds(105, 65, 45, 26);
		  }
		  return woman;
	 }
	 //初始化男按钮,并且添加到按钮组中
	 private JRadioButton getMan(){
		  if (man == null){
		   man = new JRadioButton();
		   bg.add(man);
		   man.setText("男");
		   man.setBounds(58, 66, 47, 26);
		  }
		  return man;
	 }
	 //初始化性别文本
	 private JLabel getSex_lab(){
		  if (sex_lab == null){
		   sex_lab = new JLabel();
		   sex_lab.setText("性别");
		   sex_lab.setBounds(16, 70, 41, 18);
		  }
		  return sex_lab;
	 }
	 //初始化密码文本
	 private JLabel getPwd_lab(){
		  if (pwd_lab == null){
		   pwd_lab = new JLabel();
		   pwd_lab.setText("密码");
		   pwd_lab.setBounds(14, 42, 41, 20);
		  }
		  return pwd_lab;
	 }
	 //初始化名字文本
	 private JLabel getName_lab(){
		  if (name_lab == null){
		   name_lab = new JLabel();
		   name_lab.setText("姓名");
		   name_lab.setBounds(15, 11, 41, 20);
		  }
		  return name_lab;
	 }
	 public RegistLayout(){
		 initComponents();
	 }
	 *//**
	  * 用于接收操作事件的侦听器接口。对处理操作事件感
	  * 兴趣的类可以实现此接口，而使用该类创建的对象可
	  * 使用组件的 addActionListener 方法向该组件注册。在
	  * 发生操作事件时，调用该对象的 actionPerformed 方法。
	  *//*
	 public void actionPerformed(ActionEvent e){
		  String action = e.getActionCommand();
//		  System.out.println(action);
		  //注册按钮触发事件
		  if (action != null && ADD.equals(action)){
			  UserDao dao = new UserDaoImpl();
			  User u;
			  try {
				  u = dao.find(name_jf.getText());
				  if(u==null) {
				   		User user = new User();
				   		user.setUsername(name_jf.getText());
				   		user.setPwd(pwd_jf.getText());
				   		if(woman.isSelected()){
				   			user.setGender("女");
				   		}else{
				   			user.setGender("男");
				   		}
				   		user.setInfo(info_ta.getText());
				   		dao.save(user);
				   		JOptionPane.showMessageDialog(null, "用户注册成功", "信息提示！", JOptionPane.INFORMATION_MESSAGE);
				   		//需要通过configuration对象构建login页面,该页面中有配置信息
				   		Configuration configuation = new ConfigurationImpl();
				   		loginImpl =(LoginImpl) configuation.getLogin();
				   		//loginImpl.init01();
				   		loginImpl=new LoginImpl();
				   		loginImpl.login();
				   		dispose();
				 }else {
					 JOptionPane.showMessageDialog(null, "用户已经存在,请重新注册", "信息提示！", JOptionPane.ERROR_MESSAGE);
				 }
			  } catch (Exception e2) {
				  e2.printStackTrace();
			  }
			 //取消按钮触发事件
		  }else if (action != null && action.equals(CANCEL)){
			   name_jf.setText("");
			   pwd_jf.setText("");
			   woman.setSelected(true);
			   man.setSelected(false);
			   info_ta.setText("");
		  }
	 }
}*/