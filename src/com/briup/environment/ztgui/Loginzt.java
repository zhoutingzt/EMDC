package com.briup.environment.ztgui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Loginzt {
	static Container container;
	private static MyPanel myPanel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ImageIcon img=new ImageIcon("src/login.jpg");
		//JLabel jimag=new JLabel(img);
		JFrame frame=new JFrame("管理员登录");
		Container contentPane=frame.getContentPane();
		//contentPane.setBackground(Color.CYAN); 
		
		contentPane.setLayout(new FlowLayout());
		frame.setSize(500, 200);
		frame.setLocation(500, 200);
		JLabel L1=new JLabel("登录名");
	    final JTextField usenamefile=new JTextField(8);//文本框
	    
	    
	    JLabel L2=new JLabel("密码");
	    final JPasswordField pwfile= new JPasswordField(8);//文本框
	    
	    JButton LoginButton=new JButton("登录");
	    JButton LoginButton1=new JButton("取消");
	    final JOptionPane a2=new JOptionPane();
	  
	    JComboBox imageComBox;
	    String Q[]={"老师","领导","数据库管理员","维修人员"};
	    JComboBox imageComboBox=new JComboBox(Q);
		imageComboBox.setMaximumRowCount(3);
		  JLabel label=new JLabel("请选择");
	
		 // myPanel=new MyPanel();
		 // contentPane.add(jimag);
	    contentPane.add(L1);
	    contentPane.add(usenamefile);
	    contentPane.add(L2);
	    contentPane.add(pwfile);
	    contentPane.add(imageComboBox);
		contentPane.add(label);
	    contentPane.add(LoginButton);
	    contentPane.add(LoginButton1);
	    LoginButton.addActionListener(new ActionListener()
		 {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginButton_actionPerformed(e);
				//new menu();
				//menu.ff();
			}

			private void LoginButton_actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dbURL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";	// 连接字符串
				String userName = "envir";
				String userPwd = "envir";
				  try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
					    Connection dbconn = DriverManager.getConnection(dbURL, userName, userPwd);
					    System.out.println("连接数据库成功");
					   Statement stat = dbconn.createStatement();
					   String sql="select * from login where name='" +usenamefile.getText()+"' and password='"+ pwfile.getText()+"'";
						    ResultSet rs=stat.executeQuery(sql);
						    int k=0;
			
						    while(rs.next())
						    {
						    	String a1=rs.getString("name");
						    	String a2=rs.getString("password");
						    	k++;
						    }
						    if(k==0)
						    { String msg="用户不存在或密码错误！";
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
	    
	    frame.setVisible(true);   
	}

}
