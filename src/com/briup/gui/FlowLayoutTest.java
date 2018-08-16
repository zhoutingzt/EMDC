package com.briup.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class FlowLayoutTest {
	public static void main(String[] args) {
		JFrame jFrame=new JFrame("flow布局测试");
		jFrame.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));//组件的左右距为50  上下间间距为20
		Button btns[]=new Button[10];
		for(int i=0;i<btns.length;i++){
			btns[i]=new Button("测试"+i);
			jFrame.add(btns[i]);
		}
		jFrame.getContentPane().setBackground(Color.blue);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setLocation(200,200);
		jFrame.pack();// 自适应  如果不写这个 可以自己设定窗口的大小调整此窗口的大小，以适合其子组件的首选大小和布局。
		jFrame.setVisible(true);
	}

}
