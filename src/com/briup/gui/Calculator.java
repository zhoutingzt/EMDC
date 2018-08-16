package com.briup.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Caret;

public class Calculator extends JFrame {
	
	// 这一句的作用是什么？
	
	private static final long serialVersionUID = -1047298397568411277L;
	private JTextField textField;
    private ActionListener myListener;
    
	public Calculator() {
	
		setTitle("计算器"); // 设置名字为计算器
		setSize(300, 300); // 设置大下为 300x300
		setLocationRelativeTo(null); // 设置位置为居中
		setResizable(false); // 设置为大小不可变的
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 可以退出
		
		
		Container container = getContentPane(); // 新建一个容器
        container.setLayout(new BorderLayout(5, 5)); // 设置左右间距为5像素
        
        
		JPanel pnlNorth = new JPanel();  // 北面面板
		JPanel pnlCenter = new JPanel();  // 中间面板
		
        // 把textFeild和panel 都放到容器上
		container.add(pnlNorth, BorderLayout.NORTH); // 把pnlNorth放在北面
        container.add(pnlCenter, BorderLayout.CENTER); // 把pnlCenter放在center面
		
        textField = new JTextField();  // 文本框设置
        //textField.setCaret( new Font("宋体", Font.PLAIN, 16));
        textField.setEditable(false);  // 不可修改
        textField.setHorizontalAlignment (JTextField.RIGHT); //设置输出右对齐
        
        
        //---------------
        pnlNorth.setLayout(new BorderLayout(5, 5)); // 设置上边的文本框和按钮的位置为边界布局
        //---------------
        
        JButton btnClear = useButton("<<<"); // new一个button按钮
        pnlNorth.add(textField, BorderLayout.CENTER); // 将文本框放到中间
		pnlNorth.add(btnClear, BorderLayout.EAST); // 将按钮放到做左边（dong）
  
        String[] titles = {
				"7", "8", "9", "+", 
				"4", "5", "6", "-",
				"1", "2", "3", "*",
				"0", ".", "/", "="
		};
		
		// 设置下面放按钮的位置的panel为网格布局
		// 4x4的网格 button与button之间的距离为3像素
		pnlCenter.setLayout(new GridLayout(4, 4, 3, 3));
		for (int i = 0; i < titles.length; i++) {
			JButton btnNum = useButton(titles[i]);
			// btnNum.setFont(new Font("宋体", Font.PLAIN, 16));
			// 三个参数分别表示： 字体，样式（粗体，斜体等），字号
			btnNum.setFont(new Font("微软雅黑",Font.PLAIN, 24));
			pnlCenter.add(btnNum);
		}
       
    }
    
	public JButton useButton (final String titles) {
		/*
		 * String.valueof(x)  返回x的字符串表示
		 * 这里的titles之所以要用final修饰 是为了不被修改
		 * 然后后面使用String的valueof方法是为了将
		 */
		JButton button = new JButton(String.valueOf(titles));
		if(myListener == null){
			myListener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
      				String  btnInformation  = event.getActionCommand();//返回与此动作相关的命令字符串
    				char key2 = btnInformation.charAt(0);
    				System.out.println("输出科key2的值："+key2);
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
	        		textField.setText("");
	        		textField.setText(String.valueOf(key2));
	        		flag = false;
				} else {
					text = textField.getText() + key2;
		        	textField.setText(text);
		        	text = "";  // 因为text公用每次使用完了之后进行清空的操作
											
				}
	            break;
	            
	        case '.':   
	        	if("".equals(textField.getText()))
	        		break; // 第一个元素不能为.    
	        	
	        	if(!"".equals(textField.getText())){
	        		text = textField.getText();
					len = text.length();
					int i = 0;
					for(; i<len; i++) {
						if('.' == text.charAt(i)){
							break; // 出现两个点的情况
						}
					} // 确认字符窜中是否已经存在.
					if(i<len)  //  一个字符串中不能出现多个. 
						break;
					text = textField.getText() + key2;
		            textField.setText(text);
		            text = "";
	        	}	
	        	break;	
	            
        	case '+': 
        		if(!"".equals(textField.getText())){
        			if ('n' == op) {
        				a = textField.getText();
        				op = '+'; 
        				flag = true;
        			} else {
        				b = textField.getText();
        				calculation(a, b);
        				a = value;
        				textField.setText(value);
        				op = '+';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
                
            case '-':
            	if(!"".equals(textField.getText())){
        			if ('n' == op) {
        				a = textField.getText();
        				op = '-'; 
        				flag = true;
        			} else {
        				b = textField.getText();
        				calculation(a, b);
        				a = value;
        				textField.setText(value);
        				op = '-';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
            	
            case '*':
            	if(!"".equals(textField.getText())){
        			if ('n' == op) {
        				a = textField.getText();
        				op = '*'; 
        				flag = true;
        			} else {
        				b = textField.getText();
        				calculation(a, b);
        				a = value;
        				textField.setText(value);
        				op = '*';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
                
            case '/':
            	if(!"".equals(textField.getText())){
        			if ('n' == op) {
        				a = textField.getText();
        				op = '/'; 
        				flag = true;
        			} else {
        				b = textField.getText();
        				calculation(a, b);
        				a = value;
        				textField.setText(value);
        				op = '/';
        				flag = true;
        			}
        			exit = '1';
        		}
        		break;
                
            case '<':          	   
            	text = textField.getText(); // 获取当前的字符串
            	if(!"".equals(text)){
            		len = text.length();
                	text = text.substring(0, len-1);
                	textField.setText(text);
            	}
            	text="";
            	break;
   
            case '=':
            	
            	if('0' == exit){
            		textField.setText("");
            		break;
            	}
            	if("".equals(textField.getText())) // 排除  7*=  7+= 之类错误
            		break;
            	b = textField.getText();
            	
            	calculation(a, b);
                textField.setText(String.valueOf(value));
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
		new Calculator().setVisible(true);
    }
}