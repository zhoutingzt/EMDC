package com.briup.environment.ztgui;
//package com.briup.enviroment.gui;
//
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.SpringLayout;
//
//public class update {
//	static Container container;//��������
//	   static  void t1(){
//		   JOptionPane a2=new JOptionPane();
//			JFrame fra=new JFrame("�޸���Ϣ");
//			fra.setSize(500, 400);
//		
//			fra.setVisible(true);
//			SpringLayout layout = new SpringLayout();
//			fra.setLayout(layout);	
//		    //������ʾ��Ϣʹ�õ����
//			
//			
//		    JLabel label1=new JLabel("ѧ��");  fra.add(label1);
//		    JTextField snofile=new JTextField(10);  fra.add(snofile);//�ı���
//		    
//		    JLabel label2=new JLabel("����");  fra.add(label2);
//		    JTextField snamefile=new JTextField(10);  fra.add(snamefile);//�ı���
//		    
//		    JLabel label3=new JLabel("�Ա�"); fra.add(label1);fra.add(label3);
//		    JTextField ssexfile=new JTextField(10); fra.add(ssexfile);//�ı���
//
//		    JLabel label4=new JLabel("רҵ");  fra.add(label4);
//		    JTextField sdeptfile=new JTextField(10); fra.add(sdeptfile);//�ı���
//		    
//		    JLabel label5=new JLabel("�γ�1");  fra.add(label5);
//		    JTextField cno1file=new JTextField(10);  fra.add(cno1file);//�ı���
//		    
//		    JLabel label6=new JLabel("�γ�2");  fra.add(label6);
//		    JTextField cno2file=new JTextField(16); fra.add(cno2file);
//		    
//		    JLabel label7=new JLabel("�γ�3");  fra.add(label7);
//		    JTextField cno3file=new JTextField(16);  fra.add(cno3file);
//		 // �������֮����Ե�λ��
//		    layout.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, fra);
//			layout.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, fra);	
//		
//			layout.putConstraint(SpringLayout.WEST, snofile, 10, SpringLayout.EAST,label1);
//			layout.putConstraint(SpringLayout.NORTH,snofile, 25, SpringLayout.NORTH, fra);
//			
//			layout.putConstraint(SpringLayout.WEST, snamefile, 10, SpringLayout.EAST,label2);
//			layout.putConstraint(SpringLayout.NORTH,snamefile, 25, SpringLayout.NORTH, snofile);
//			
//			layout.putConstraint(SpringLayout.WEST, ssexfile, 10, SpringLayout.EAST,label3);
//			layout.putConstraint(SpringLayout.NORTH,ssexfile, 25, SpringLayout.NORTH, snamefile);
//			
//			layout.putConstraint(SpringLayout.WEST, sdeptfile, 10, SpringLayout.EAST,label4);
//			layout.putConstraint(SpringLayout.NORTH,sdeptfile, 25, SpringLayout.NORTH, ssexfile);
//			
//			layout.putConstraint(SpringLayout.WEST, cno1file, 10, SpringLayout.EAST,label5);
//			layout.putConstraint(SpringLayout.NORTH,cno1file, 25, SpringLayout.NORTH, sdeptfile);
//			
//			layout.putConstraint(SpringLayout.WEST, cno2file, 10, SpringLayout.EAST,label6);
//			layout.putConstraint(SpringLayout.NORTH,cno2file, 25, SpringLayout.NORTH, cno1file);
//			
//			layout.putConstraint(SpringLayout.WEST, cno3file, 10, SpringLayout.EAST,label7);
//			layout.putConstraint(SpringLayout.NORTH,cno3file, 25, SpringLayout.NORTH, cno2file);
//			
//			layout.putConstraint(SpringLayout.WEST, cno3file, 10, SpringLayout.EAST,label7);
//			layout.putConstraint(SpringLayout.NORTH,cno3file, 25, SpringLayout.NORTH, cno2file);
//			
//			layout.putConstraint(SpringLayout.WEST, label2, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label2, 25, SpringLayout.NORTH, label1);
//			
//			layout.putConstraint(SpringLayout.WEST, label3, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label3, 25, SpringLayout.NORTH, label2);
//			
//			layout.putConstraint(SpringLayout.WEST, label4, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label4, 25, SpringLayout.NORTH, label3);
//			
//			layout.putConstraint(SpringLayout.WEST, label5, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label5, 25, SpringLayout.NORTH, label4);
//			
//			layout.putConstraint(SpringLayout.WEST, label6, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label6, 25, SpringLayout.NORTH, label5);
//			
//			layout.putConstraint(SpringLayout.WEST, label7, 10, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,label7, 25, SpringLayout.NORTH, label6);
//			
//			JButton updateButton=new JButton("�޸�"); fra.add(updateButton);
//			layout.putConstraint(SpringLayout.WEST, updateButton, 200, SpringLayout.WEST,fra);
//			layout.putConstraint(SpringLayout.NORTH,updateButton,200, SpringLayout.NORTH, fra);
//			
//		    updateButton.addActionListener(new ActionListener() {
//		    	@Override
//		    	public void actionPerformed(ActionEvent e) {
//		    		String dbURL = "jdbc:sqlserver://127.0.0.1:1433; DatabaseName=testDB";	// �����ַ���
//					  String userName = "sa";	String userPwd = "123456";
//					try{
//						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//					    Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//					 
//					     Statement  stat = dbConn.createStatement();
//					    String sql="select *from Student where sno='"+snofile.getText()+"'";
//					    ResultSet  rs=stat.executeQuery(sql);
//					    if(rs.next()==false)
//					    {
//					    	 String S1="�޸ĵļ�¼������";
//							    String T1="�޸ļ�¼��";
//							    a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);	
//					    }else
//					    {
//					    	String sql2="update  Student set sname='"+snamefile.getText()+"',ssex='"+ssexfile.getText()
//					    	+"',sdept='"+sdeptfile.getText()+"',cno1="+cno1file.getText()
//					    	+"where[sno]='"+snofile.getText()+"'";
//					    	stat.executeUpdate(sql2);
//					        String S1="�޸����";
//							 String T1="�޸���Ϣ��";
//							 a2.showMessageDialog(container,S1,T1,JOptionPane.INFORMATION_MESSAGE);
//					    
//					    }
//					   snofile.setText("");
//					    snamefile.setText("");
//					    ssexfile.setText("");
//					    sdeptfile.setText("");
//					    //sscorefile.setText("");
//					    cno1file.setText(" ");
//					    cno2file.setText(" ");
//					    cno3file.setText(" ");
//					    
//					}catch(SQLException ex)
//					{
//						System.out.println("/****SQL�����쳣*****/");
//						while(ex!=null)
//						{
//							System.out.println("�쳣��Ϣ:"+ex.getMessage());
//							ex=ex.getNextException();
//						}
//					}catch(Exception ex)
//					{
//						ex.printStackTrace();
//					}
//				}
//		         
//			 });
//		    
//	}
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//
//		}
//}
