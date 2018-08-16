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
			buttons[i]=new Button(positions[i]);//构造一个带指定标签的按钮。
			add(buttons[i],positions[i]);// 将指定的组件添加到此容器的尾部。 默认是boder布局
		}
		Button button=new Button("测试");
		//add(button);
		
		Panel panel=new Panel();
		panel.add(new TextField(20));
		panel.add(new Button("panel测试"));
		add(panel);
		
	}
	public static void main(String[] args) {
		new BoderLayoutTest("测试boder布局");
	}
}
