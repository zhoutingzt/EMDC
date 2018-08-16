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
		//setLayout(new FlowLayout());//默认布局会把前面的布局给覆盖掉 使用指定的布局的话 就是容器来控制布局  调位置大小等都没用
		Button b=new Button("测试");
		b.setSize(300, 300);
		b.setLocation(50,50);
		add(b);
		
		//pack();//如果只有一个组件就会沾满整容器自适应
		
//		Button buttons[]=new Button[5];
//		for(int i=0;i<5;i++){
//			buttons[i]=new Button("测试"+i);
//			add(buttons[i]);
//		}
		setVisible(true);
	}
	public static void main(String[] args) {
		new FrameTest2("第二个Gui");
	}
}
