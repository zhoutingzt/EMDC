package com.briup.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JFrame;

public class BoderLayoutTest extends JFrame{

	public BoderLayoutTest(){
		
	}
	public BoderLayoutTest(String title){
		setTitle(title);
		getContentPane().setBackground(Color.blue);
		setResizable(true);
		setDefaultCloseOperation(3);
		init();
		setSize(500,500);
		setVisible(true);
	}
	public void init(){
		Button buttons[]=new Button[5];
		String positions[]={
				BorderLayout.NORTH, BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST,BorderLayout.CENTER
		};
		for(int i=0;i<4;i++){
			buttons[i]=new Button(positions[i]);//����һ����ָ����ǩ�İ�ť��
			add(buttons[i],positions[i]);// ��ָ���������ӵ���������β���� Ĭ����boder����
		}
		Button button=new Button("����");
		//add(button);
		
		Panel panel=new Panel();
		panel.add(new TextField(20));
		panel.add(new Button("panel����"));
		add(panel);
		
	}
	public static void main(String[] args) {
		new BoderLayoutTest("����boder����");
	}
}
