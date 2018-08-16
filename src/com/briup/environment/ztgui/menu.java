package com.briup.environment.ztgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class menu {
	static	void ff(){
		JFrame frm= new JFrame("学生管理系统");
		frm.setSize(400, 200);
		SpringLayout layout = new SpringLayout();
		frm.setLayout(layout);		
		JButton addButton = new JButton("添加");		frm.add(addButton);
		JButton deleteButton=new JButton("删除");	frm.add(deleteButton);
		JButton updateButton = new JButton("修改");	frm.add(updateButton);
		JButton chaxunButton=new JButton("学号查询");frm.add( chaxunButton);
		JButton chaxunjiluButton=new JButton("查询全部");frm.add(chaxunjiluButton);
       
		// 设置各组件之间的相对位置
		/*
		 * putConstraint(String e1, Component c1, int pad, String e2, Component c2) 
          * 将组件 c1 的边 e1 连接到组件 c2 的边 e2，边与边之间的距离固定。
		 */
		layout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.WEST, frm);
		layout.putConstraint(SpringLayout.NORTH, addButton, 25, SpringLayout.NORTH, frm);	
				
		layout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.EAST, addButton);
		layout.putConstraint(SpringLayout.NORTH,deleteButton, 25, SpringLayout.NORTH, frm);
				
		layout.putConstraint(SpringLayout.WEST, updateButton , 10, SpringLayout.EAST, deleteButton);
		layout.putConstraint(SpringLayout.NORTH, updateButton , 25, SpringLayout.NORTH, frm);
				
		layout.putConstraint(SpringLayout.WEST, chaxunButton, 10, SpringLayout.EAST, updateButton);
		layout.putConstraint(SpringLayout.NORTH,chaxunButton, 25, SpringLayout.NORTH, frm);
				
		layout.putConstraint(SpringLayout.WEST, chaxunjiluButton, 10, SpringLayout.EAST,  chaxunButton);
		layout.putConstraint(SpringLayout.NORTH,chaxunjiluButton, 25, SpringLayout.NORTH, frm);
				
		frm.setVisible(true);
		//实现添加按钮,通过方法的调用来实现，同时实现页面的跳转
		addButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				//new add();
			//	add.f();
				
					}
			});
		//实现删除按钮,通过方法的调用来实现，同时实现页面的跳转
		deleteButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//new delete();
		//	delete.f2();
				
				}
			});
		//实现更新按钮,通过方法的调用来实现，同时实现页面的跳转
		updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				//new update();
				//update.t1();
				}
			});
		//实现学号按钮,通过方法的调用来实现，同时实现页面的跳转
		chaxunButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				//new snochaxun();
				//snochaxun.t2();
				}
		 });
		//实现查询全部按钮,通过方法的调用来实现，同时实现页面的跳转
		chaxunjiluButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				//new tongjichaxun();
				//tongjichaxun.t3();
				}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    }
}
