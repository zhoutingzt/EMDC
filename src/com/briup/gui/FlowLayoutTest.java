package com.briup.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class FlowLayoutTest {
	public static void main(String[] args) {
		JFrame jFrame=new JFrame("flow���ֲ���");
		jFrame.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));//��������Ҿ�Ϊ50  ���¼���Ϊ20
		Button btns[]=new Button[10];
		for(int i=0;i<btns.length;i++){
			btns[i]=new Button("����"+i);
			jFrame.add(btns[i]);
		}
		jFrame.getContentPane().setBackground(Color.blue);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setLocation(200,200);
		jFrame.pack();// ����Ӧ  �����д��� �����Լ��趨���ڵĴ�С�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ��֡�
		jFrame.setVisible(true);
	}

}
