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
	
    //����һ�����б���ͼƬ�����
    private MyPanel panel;
    private JLabel lab_zhanghao = new JLabel("�˺�:");
    private JTextField name_text = new JTextField();
    Font namefont = new Font("����",1,25);//1��ʾ��б�� 30�Ǵ�С
    private JLabel lat_password = new JLabel("����:");
    Font pwFont  = new Font("����",1,25);
    private JPasswordField pwd_text = new JPasswordField();
    private JButton btn_register = new JButton("ע��");
    private JButton btn_land = new JButton("��½");
    Font btn = new Font("����",2,20);
    
    JLabel title=new JLabel("��ӭ��¼���������������������");
    Font titleFont = new Font("����",1,30);
   
    private Container contentPane = this.getContentPane();
 
    static Container container;
    final JOptionPane a2=new JOptionPane();
    
    
    String dbURL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";	// �����ַ���
    String userName = "envir";	
	String userPwd = "envir";
    public denglu () {
        this.setSize(600,450);//400,267
        //�û��������ڵĹرհ�ťʱ����ִ�еĲ���
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("��½");
        this.setResizable(false);
        btn_register.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try{
    			Class.forName("oracle.jdbc.driver.OracleDriver");
    		    Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
    			   // System.out.println("�������ݿ�ɹ�");
    			Connection dbconn=null;//
    			ResultSet rs=null;
    			Statement stmt = dbConn.createStatement();
    			String sql="select *from login where name='"+name_text.getText()+"'";
    			rs=stmt.executeQuery(sql);
    			if(rs.next()==true)
    			  {
    			    String S1="��ӵ��û��Ѵ���";
    				String T1="���������";
    			    a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);	
    			    }else
    			    {
    			    	String sql2="insert into login(name,password)"+
    						    "values('"+name_text.getText()+"','"+pwd_text.getText()+"' )";
    			    	//String sql2="insert into login(name,password)"+
    			     // "values('" +name_text.getText()+"','" +pwd_text.getText()+"')";
    			    	stmt.executeUpdate(sql2);	//�������Ӽ�¼
    			    	String S1="��ӳɹ���";
    				    String T1="��ӳɹ�����";
    				   a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);
    			    	
    			    }
    			    name_text.setText("");
    			    pwd_text.setText("");
    			   
    			}catch(SQLException ex)
    			{
    				System.out.println("/****SQL�����쳣*****/");
    				while(ex!=null)
    				{
    					System.out.println("�쳣��Ϣ:"+ex.getMessage());
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
					    System.out.println("�������ݿ�ɹ�");
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
						 System.out.println("k��ֵΪ��"+k);
						  if(k==0)
						 { 
						   String msg="�û������ڻ��������";
						   String title="���������";  
						   a2.showMessageDialog(container,msg,title,JOptionPane.INFORMATION_MESSAGE);
						    }
						    else
						    	{
						    	String msg1="��¼�ɹ�";
						        String title1="��¼�ɹ�";
						    	a2.showMessageDialog(container,msg1,title1,JOptionPane.INFORMATION_MESSAGE);}
				
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
			}
		 });  
         
        init();
         
        panel = new MyPanel();
        //�����е������ӵ�panel�����
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