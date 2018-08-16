package com.briup.environment.ztgui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

public class denglu extends JFrame {
	
    //设置一个含有背景图片的面板
    private MyPanel panel;
    private JLabel lab_zhanghao = new JLabel("账号:");
    private JTextField name_text = new JTextField();
    Font namefont = new Font("楷体",1,25);//1表示倾斜度 30是大小
    private JLabel lat_password = new JLabel("密码:");
    Font pwFont  = new Font("楷体",1,25);
    private JPasswordField pwd_text = new JPasswordField();
    private JButton btn_register = new JButton("注册");
    private JButton btn_land = new JButton("登陆");
    Font btn = new Font("黑体",2,20);
    
    JLabel title=new JLabel("欢迎登录互联网环境监测数据中心");
    Font titleFont = new Font("楷体",1,30);
   
    private Container contentPane = this.getContentPane();
 
    static Container container;
    final JOptionPane a2=new JOptionPane();
    
    
    String dbURL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";	// 连接字符串
    String userName = "envir";	
	String userPwd = "envir";
    public denglu () {
        this.setSize(600,450);//400,267
        //用户单击窗口的关闭按钮时程序执行的操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("登陆");
        this.setResizable(false);
        btn_register.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    		    Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
    			   // System.out.println("连接数据库成功");
    			Connection dbconn=null;//
    			ResultSet rs=null;
    			Statement stmt = dbConn.createStatement();
    			String sql="select *from login where name='"+name_text.getText()+"'";
    			rs=stmt.executeQuery(sql);
    			if(rs.next()==true)
    			  {
    			    String S1="添加的用户已存在";
    				String T1="错误操作！";
    			    a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);	
    			    }else
    			    {
    			    	String sql2="insert into login(name,password)"+
    						    "values('"+name_text.getText()+"','"+pwd_text.getText()+"' )";
    			    	//String sql2="insert into login(name,password)"+
    			     // "values('" +name_text.getText()+"','" +pwd_text.getText()+"')";
    			    	stmt.executeUpdate(sql2);	//向表中添加记录
    			    	String S1="添加成功！";
    				    String T1="添加成功！！";
    				   a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);
    			    	
    			    }
    			    name_text.setText("");
    			    pwd_text.setText("");
    			   
    			}catch(SQLException ex)
    			{
    				System.out.println("/****SQL操作异常*****/");
    				while(ex!=null)
    				{
    					System.out.println("异常信息:"+ex.getMessage());
    					ex=ex.getNextException();
    				}
    			}catch(Exception ex)
    			{
    				ex.printStackTrace();
    			
    			}
    		}
    	});
        btn_land.addActionListener(new ActionListener()
		 {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginButton_actionPerformed(e);
				new menu();
				menu.ff();
			}

			private void LoginButton_actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
					    Connection dbconn = DriverManager.getConnection(dbURL, userName, userPwd);
					    System.out.println("连接数据库成功");
					    Statement stat = dbconn.createStatement();
					    String sql="select * from login where name='" +name_text.getText()+"' and password='"+ pwd_text.getText()+"'";
						ResultSet rs=stat.executeQuery(sql);
						int k=0;  
						while(rs.next())
						  {
						    String a1=rs.getString("name");
						    String a2=rs.getString("password");
						    k++;
						  }
						 System.out.println("k的值为："+k);
						  if(k==0)
						 { 
						   String msg="用户不存在或密码错误！";
						   String title="错误操作！";  
						   a2.showMessageDialog(container,msg,title,JOptionPane.INFORMATION_MESSAGE);
						    }
						    else
						    	{
						    	String msg1="登录成功";
						        String title1="登录成功";
						    	a2.showMessageDialog(container,msg1,title1,JOptionPane.INFORMATION_MESSAGE);}
				
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
			}
		 });  
         
        init();
         
        panel = new MyPanel();
        //将所有的组件添加到panel面板中
        panel.add(title);
        panel.add(lab_zhanghao);
        panel.add(lat_password);
        panel.add(name_text);
        panel.add(pwd_text);
        panel.add(btn_register);
        panel.add(btn_land);
        panel.setLayout(null);
         
        getContentPane().add(panel);
    }
    private void init() {
    	title.setForeground(Color.gray);
    	title.setSize(600,100);
    	title.setLocation(50,10);
    	title.setFont(titleFont);
        lab_zhanghao.setSize(200,100);//200,100
        lab_zhanghao.setLocation(150,70);
        lab_zhanghao.setFont(namefont);
        lat_password.setSize(200,100);
        lat_password.setLocation(150,150);
        lat_password.setFont(pwFont);
        name_text.setSize(170,30);
        name_text.setLocation(220,115);
        pwd_text.setSize(170,30);
        pwd_text.setLocation(220,185);
        btn_register.setSize(120,40);
        btn_register.setLocation(160,230);
        btn_land.setSize(120, 40);
        btn_land.setLocation(330,230);
        btn_register.setFont(btn);
        btn_land.setFont(btn);
    }
     
    public static void main(String[] args)
    throws ClassNotFoundException,
        InstantiationException, IllegalAccessException,
        UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        new denglu().setVisible(true);
    }
}