package com.briup.environment.ztgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class menu {
	static	void ff(){
		JFrame frm= new JFrame("ѧ������ϵͳ");
		frm.setSize(400, 200);
		SpringLayout layout = new SpringLayout();
		frm.setLayout(layout);		
		JButton addButton = new JButton("����");		frm.add(addButton);
		JButton deleteButton=new JButton("ɾ��");	frm.add(deleteButton);
		JButton updateButton = new JButton("�޸�");	frm.add(updateButton);
		JButton chaxunButton=new JButton("ѧ�Ų�ѯ");frm.add( chaxunButton);
		JButton chaxunjiluButton=new JButton("��ѯȫ��");frm.add(chaxunjiluButton);
       
		// ���ø����֮������λ��
		/*
		 * putConstraint(String e1, Component c1, int pad, String e2, Component c2) 
          * ����� c1 �ı� e1 ���ӵ���� c2 �ı� e2�������֮��ľ���̶���
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
		//ʵ�����Ӱ�ť,ͨ�������ĵ�����ʵ�֣�ͬʱʵ��ҳ�����ת
		addButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				//new add();
			//	add.f();
				
					}
			});
		//ʵ��ɾ����ť,ͨ�������ĵ�����ʵ�֣�ͬʱʵ��ҳ�����ת
		deleteButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//new delete();
		//	delete.f2();
				
				}
			});
		//ʵ�ָ��°�ť,ͨ�������ĵ�����ʵ�֣�ͬʱʵ��ҳ�����ת
		updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				//new update();
				//update.t1();
				}
			});
		//ʵ��ѧ�Ű�ť,ͨ�������ĵ�����ʵ�֣�ͬʱʵ��ҳ�����ת
		chaxunButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				//new snochaxun();
				//snochaxun.t2();
				}
		 });
		//ʵ�ֲ�ѯȫ����ť,ͨ�������ĵ�����ʵ�֣�ͬʱʵ��ҳ�����ת
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