package com.briup.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class FrameTest2 extends JFrame{

	public FrameTest2(){}
	public FrameTest2(String title){
		setTitle(title);
		getContentPane().setBackground(Color.blue);
		setDefaultCloseOperation(3);
		setSize(300,300);
		setLayout(null);
		//setLayout(new FlowLayout());//Ĭ�ϲ��ֻ��ǰ��Ĳ��ָ����ǵ� ʹ��ָ���Ĳ��ֵĻ� �������������Ʋ���  ��λ�ô�С�ȶ�û��
		Button b=new Button("����");
		b.setSize(300, 300);
		b.setLocation(50,50);
		add(b);
		
		//pack();//���ֻ��һ������ͻ�մ������������Ӧ
		
//		Button buttons[]=new Button[5];
//		for(int i=0;i<5;i++){
//			buttons[i]=new Button("����"+i);
//			add(buttons[i]);
//		}
		setVisible(true);
	}
	public static void main(String[] args) {
		new FrameTest2("�ڶ���Gui");
	}
}
