package com.briup.gui;

import java.awt.Color;

import javax.swing.JFrame;



public class FrameTest {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setTitle("第一个gui");
		frame.setLocation(200,200);
        frame.setResizable(false);//是否可以拖拽
        frame.getContentPane().setBackground(Color.blue);//先获得父容器在设置背景
        
        frame.setDefaultCloseOperation(3);//EXIT_ON_CLOSE,直接关闭应用程序
	}

}
