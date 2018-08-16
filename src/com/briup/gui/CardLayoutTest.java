package com.briup.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PseudoColumnUsage;

import javax.swing.JFrame;
/*
 * ��ҳ�õıȽ϶�
 */
public class CardLayoutTest {

	public static void main(String[] args) {
		JFrame jf=new JFrame("card���ֲ���");
		final Panel panel=new Panel();
		final CardLayout c=new CardLayout();
		String names[]={"��һ��","�ڶ���","������","������","������"};
		panel.setLayout(c);
		Button btns[]=new Button[5];
		for(int i=0;i<btns.length;i++ ){
			btns[i]=new Button(names[i]);
			panel.add(btns[i],names[i]);
		}
		final Panel p2=new Panel();
		Button btn_pre=new Button("ǰһ��");
		btn_pre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.previous(panel);
				
			}
		});
		Button btn_next=new Button("��һ��");
		btn_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.next(panel);
				
			}
		});
		Button btn_first=new Button("��һ��");
		btn_first.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.first(panel);
				
			}
		});
		Button btn_last=new Button("���һ��");
		btn_last.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.last(panel);
			}
		});
		Button btn_show=new Button("�м���");
		btn_show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(panel, "������");;
			}
		});
		p2.setLayout(new FlowLayout());
		p2.add(btn_first);
		p2.add(btn_pre);
		p2.add(btn_next);
		p2.add(btn_last);
		
		p2.add(btn_show);
		jf.add(panel);
		jf.add(p2,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(3);
		jf.pack();
		jf.setVisible(true);
		
	}
}
