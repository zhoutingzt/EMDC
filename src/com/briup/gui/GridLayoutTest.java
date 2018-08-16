package com.briup.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PseudoColumnUsage;

import javax.naming.InitialContext;
import javax.swing.JFrame;

public class GridLayoutTest  extends JFrame{

	public GridLayoutTest(){
	}
	public GridLayoutTest(String title){
		setTitle(title);
		getContentPane().setBackground(Color.blue);
		setSize(300,300);
		setLocation(200,200);
		setDefaultCloseOperation(3);
		setLayout(new GridLayout(3,3,20,30));//三行三列 20左右间距 30上下间距
		init();
		setVisible(true);
		
	}
	public  void init(){
		Button btns[]=new Button[9];
		for(int i=0;i<btns.length;i++){
			btns[i]=new Button("按钮："+(i+1));
			add(btns[i]);
		}
	}
	public static void main(String[] args) {
		//new GridLayoutTest("网格布局测试");
		JFrame f=new JFrame("网格布局测试");
		f.setResizable(false);
		Panel p=new Panel();
		final Button button1=new Button("+");
		Button button2=new Button("=");
		final TextField a=new TextField(12);
		p.add(a);
		p.add(button1);
		final TextField a1=new TextField(12);
		p.add(a1);
		p.add(button2);
		final TextField a2=new TextField(12);
		p.add(a2);
		f.add(p,BorderLayout.NORTH);
		Panel p2=new Panel();
		p2.setLayout(new GridLayout(3,5,4,4));
		final String names[]={"0","1","2","3","4","5","6","7","8","9","+","-","*","/","."};
		final Button[] btns=new Button[15];
		for(int i=0;i<btns.length;i++){
			btns[i]=new Button(names[i]);
			p2.add(btns[i]);
			
		}
		f.add(p2);
		f.setDefaultCloseOperation(3);
		f.setLocation(200, 200);
		f.pack();
		f.setVisible(true);
	btns[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("0");
				}else{
					a1.setText("0");
				}
				
			}
			
		});
     btns[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("1");
				}else{
					a1.setText("1");
				}
				
			}
			
		});
     btns[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("2");
				}else{
					a1.setText("2");
				}
				
			}
			
		});
     btns[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("3");
				}else{
					a1.setText("3");
				}
			}
			
		});
      btns[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("4");
				}else{
					a1.setText("4");
				}
			}
			
		});
      
      btns[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("5");
				}else{
					a1.setText("5");
				}
			}
			
		});
      btns[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("6");
				}else{
					a1.setText("6");
				}
			}
			
		});
      btns[7].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("7");
				}else{
					a1.setText("7");
				}
			}
			
		});
      btns[8].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("8");
				}else{
					a1.setText("8");
				}
			}
			
		});
      btns[9].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(a.getText())){
					a.setText("9");
				}else{
					a1.setText("9");
				}
			}
			
		});
      btns[10].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setLabel(""+btns[10].getLabel());
			}
			
		});
      btns[11].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setLabel(""+btns[11].getLabel());
			}
			
		});
      btns[12].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setLabel(""+btns[12].getLabel());
			}
			
		});
      btns[13].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button1.setLabel(""+btns[13].getLabel());
			}
			
		});
//      btns[14].addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				button1.setLabel(""+btns[14].getLabel());
//			}
//			
//		});
      button2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if("+".equals(button1.getLabel())){
				int sum=Integer.parseInt(a.getText())+Integer.parseInt(a1.getText());
				a2.setText(""+sum);
				a.setText("");
				a1.setText("");
			}
			if("-".equals(button1.getLabel())){
				int sum=Integer.parseInt(a.getText())-Integer.parseInt(a1.getText());
				a2.setText(""+sum);
				a.setText("");
				a1.setText("");
			}
			if("*".equals(button1.getLabel())){
				int sum=Integer.parseInt(a.getText())*Integer.parseInt(a1.getText());
				a2.setText(""+sum);
				a.setText("");
				a1.setText("");
			}
			if("/".equals(button1.getLabel())){
				int sum=(Integer.parseInt(a.getText()))/(Integer.parseInt(a1.getText()));
				a2.setText(""+sum);
				a.setText("");
				a1.setText("");
			}
			
		}
	});
	}
}
