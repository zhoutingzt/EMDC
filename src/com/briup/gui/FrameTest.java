package com.briup.gui;

import java.awt.Color;

import javax.swing.JFrame;



public class FrameTest {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setTitle("��һ��gui");
		frame.setLocation(200,200);
        frame.setResizable(false);//�Ƿ������ק
        frame.getContentPane().setBackground(Color.blue);//�Ȼ�ø����������ñ���
        
        frame.setDefaultCloseOperation(3);//EXIT_ON_CLOSE,ֱ�ӹر�Ӧ�ó���
	}

}
