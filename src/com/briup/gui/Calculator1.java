package com.briup.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculator1 extends JFrame{
	private static final long serialVersionUID = -1047298397568411277L;
	private TextField a0;
	private TextField a1;
	private TextField a2;
	private Button button1;
	private Button button2;
    private ActionListener myListener;
    public Calculator1(){
    	setVisible(false);
    	Panel p=new Panel();
		 button1=new Button("+");
		 button2=new Button("=");
		a0=new TextField(12);
		p.add(a0);
		p.add(button1);
		a1=new TextField(12);
		p.add(a1);
		p.add(button2);
		a2=new TextField(12);
		p.add(a2);
		add(p,BorderLayout.NORTH);
		Panel p2=new Panel();
		p2.setLayout(new GridLayout(4,5,4,4));
		final String names[]={"0","1","2","3","4","5","6","7","8","9","+","-","*","/",".","="};
		JButton btns[]=new JButton[16];
		for(int i=0;i<btns.length;i++){
			btns[i]=useButton(names[i]);
			p2.add(btns[i]);
			
		}
		add(p2);
		setDefaultCloseOperation(3);
		setLocation(200, 200);
		pack();
		setVisible(true);
    }
    public JButton useButton (final String names) {
		/*
		 * String.valueof(x)  返回x的字符串表示
		 * 这里的titles之所以要用final修饰 是为了不被修改
		 * 然后后面使用String的valueof方法是为了将
		 */
		JButton button = new JButton(String.valueOf(names));
		if(myListener == null){
			myListener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
      				String  btnInformation  = event.getActionCommand();
    				char key2 = btnInformation.charAt(0);
    				action(key2);
    			}
			};
		}
        button.addActionListener(myListener);
		
        return button;
    }
	
	int len;
	String a, b, value; 
	char exit = '0'; // 初始化为=进入的时候避免多次进入case '=': 出错 ，在使用完之后置为=
	char op = 'n'; // 最听话的op 始终指向前一个操作符  这里的=是逻辑意义上的得到运算结果的意思
	boolean flag = false;
	//  flag 为false的时候进行数字长度增加的操作
	//  flag 为true的时候进行数字首次输入的操作
	private void action(char key2){
        /*
         * 在计算器的运算中整体的数字都是被转化为了double型的
         * Double.parseDouble ...
         * 
         */
    	String text;
    	switch(key2){
        	/*
        	 * 例如：
        	 * 		首先你输入一串数字：如果下一个符号为 + - *　/ 中的任意一个
        	 * 		首先将textfeild里面的值清空
        	 * 		再将操作符置为   + - *  / 
        	 * 		
        	 * 		!"".equals(textField.getText())   在textField上不存在字符串的时候
			 *		
        	 *      多个点的时候    两个数之间多次点击加减乘除的时候
        	 *      多次加减乘除
        	 */
	        case '1':
	        case '2':
	        case '3':
	        case '4':
	        case '5':
	        case '6':
	        case '7':
	        case '8':
	        case '9':
	        case '0':
	        	/*
	        	 * 当flag为true的时候进行前面的字符串的清空，后面的第一个字符的输入
	        	 * 当flag为false的时候进行后面的字符串的输入
	        	 */
	        	if (flag) {
	        		a0.setText("");
	        		a0.setText(String.valueOf(key2));
	        		flag = false;
				} else {
					text = a0.getText() + key2;
		        	a0.setText(text);
		        	text = "";  // 因为text公用每次使用完了之后进行清空的操作
											
				}
	        	if(!"".equals(a0.getText())){
	        		if (flag) {
		        		a1.setText("");
		        		a1.setText(String.valueOf(key2));
		        		flag = false;
					} else {
						text = a1.getText() + key2;
			        	a1.setText(text);
			        	text = "";  // 因为text公用每次使用完了之后进行清空的操作
												
					}
	        	}
	            break;
	            
	        case '.':   
	        	if("".equals(a0.getText()))
	        		break; // 第一个元素不能为.    
	        	
	        	if(!"".equals(a0.getText())){
	        		text = a0.getText();
					len = text.length();
					int i = 0;
					for(; i<len; i++) {
						if('.' == text.charAt(i)){
							break; // 出现两个点的情况
						}
					} // 确认字符窜中是否已经存在.
					if(i<len)  //  一个字符串中不能出现多个. 
						break;
					text = a0.getText() + key2;
		            a0.setText(text);
		            text = "";
	        	}	
	        	break;	
	            
        	case '+': 
        		if(!"".equals(a0.getText())){
        			if ('n' == op) {
        				a = a0.getText();
        				op = '+'; 
        				flag = true;
        			} else {
        				b = a1.getText();
        				button1.setLabel("+");
        				calculation(a, b);
        				a = value;
        				a2.setText(value);
        				op = '+';
        				flag = true;
        			}
        			exit = '1';
        		}
        		
        		break;
                
            case '-':
            	if(!"".equals(a0.getText())){
        			if ('n' == op) {
        				a = a0.getText();
        				op = '-'; 
        				flag = true;
        			} else {
        				b = a1.getText();
        				button1.setLabel("-");
        				calculation(a, b);
        				a = value;
        				a2.setText(value);
        				op = '-';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
            	
            case '*':
            	if(!"".equals(a0.getText())){
        			if ('n' == op) {
        				a = a0.getText();
        				op = '*'; 
        				flag = true;
        			} else {
        				b = a1.getText();
        				button1.setLabel("*");
        				calculation(a, b);
        				a = value;
        				a2.setText(value);
        				op = '*';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
                
            case '/':
            	if(!"".equals(a0.getText())){
        			if ('n' == op) {
        				a = a0.getText();
        				op = '/'; 
        				flag = true;
        			} else {
        				b =a1.getText();
        				button1.setLabel("/");
        				calculation(a, b);
        				a = value;
        				a2.setText(value);
        				op = '/';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
                
            case '<':          	   
            	text = a0.getText(); // 获取当前的字符串
            	if(!"".equals(text)){
            		len = text.length();
                	text = text.substring(0, len-1);
                	a0.setText(text);
            	}
            	text="";
            	break;
   
            case '=':
            	
            	if('0' == exit){
            		a0.setText("");
            		a1.setText("");
            		a2.setText("");
            		break;
            	}
            	if("".equals(a0.getText())) // 排除  7*=  7+= 之类错误
            		break;
            	b = a1.getText();
            	
            	calculation(a, b);
                a2.setText(String.valueOf(value));
                break;
            default: ;   // 在本计算器程序中没有其它情况
        }
    }
	
	private void calculation(String a, String b){
    	double v1 = Double.parseDouble(a);
    	double v2 = Double.parseDouble(b);
    	double v=0;
    	switch(op){
            case '+':
                v = v1 + v2;
                exit = '0';
                a = b = null;
                op = 'n';
                break;
				
            case '-':
                v = v1 - v2;
                exit = '0';
                a = b = null;
                op = 'n';
                break;
				
            case '*':
                v = v1 * v2;
                exit = '0';
                a = b = null;
                op = 'n';
                break;
				
            case '/':
            	if(v2==0) break;
                v = v1 / v2;
                exit = '0';
                a = b = null;
                op = 'n';
                break;	
            default: ;
        }
    	value = String.valueOf(v);
    }
	 public static void main(String[] args) {
			new Calculator1().setVisible(true);
	    }
}
