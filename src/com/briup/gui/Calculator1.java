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
		 * String.valueof(x)  ����x���ַ�����ʾ
		 * �����titles֮����Ҫ��final���� ��Ϊ�˲����޸�
		 * Ȼ�����ʹ��String��valueof������Ϊ�˽�
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
	char exit = '0'; // ��ʼ��Ϊ=�����ʱ������ν���case '=': ���� ����ʹ����֮����Ϊ=
	char op = 'n'; // ��������op ʼ��ָ��ǰһ��������  �����=���߼������ϵĵõ�����������˼
	boolean flag = false;
	//  flag Ϊfalse��ʱ��������ֳ������ӵĲ���
	//  flag Ϊtrue��ʱ����������״�����Ĳ���
	private void action(char key2){
        /*
         * �ڼ���������������������ֶ��Ǳ�ת��Ϊ��double�͵�
         * Double.parseDouble ...
         * 
         */
    	String text;
    	switch(key2){
        	/*
        	 * ���磺
        	 * 		����������һ�����֣������һ������Ϊ + - *��/ �е�����һ��
        	 * 		���Ƚ�textfeild�����ֵ���
        	 * 		�ٽ���������Ϊ   + - *  / 
        	 * 		
        	 * 		!"".equals(textField.getText())   ��textField�ϲ������ַ�����ʱ��
			 *		
        	 *      ������ʱ��    ������֮���ε���Ӽ��˳���ʱ��
        	 *      ��μӼ��˳�
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
	        	 * ��flagΪtrue��ʱ�����ǰ����ַ�������գ�����ĵ�һ���ַ�������
	        	 * ��flagΪfalse��ʱ����к�����ַ���������
	        	 */
	        	if (flag) {
	        		a0.setText("");
	        		a0.setText(String.valueOf(key2));
	        		flag = false;
				} else {
					text = a0.getText() + key2;
		        	a0.setText(text);
		        	text = "";  // ��Ϊtext����ÿ��ʹ������֮�������յĲ���
											
				}
	        	if(!"".equals(a0.getText())){
	        		if (flag) {
		        		a1.setText("");
		        		a1.setText(String.valueOf(key2));
		        		flag = false;
					} else {
						text = a1.getText() + key2;
			        	a1.setText(text);
			        	text = "";  // ��Ϊtext����ÿ��ʹ������֮�������յĲ���
												
					}
	        	}
	            break;
	            
	        case '.':   
	        	if("".equals(a0.getText()))
	        		break; // ��һ��Ԫ�ز���Ϊ.    
	        	
	        	if(!"".equals(a0.getText())){
	        		text = a0.getText();
					len = text.length();
					int i = 0;
					for(; i<len; i++) {
						if('.' == text.charAt(i)){
							break; // ��������������
						}
					} // ȷ���ַ������Ƿ��Ѿ�����.
					if(i<len)  //  һ���ַ����в��ܳ��ֶ��. 
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
            	text = a0.getText(); // ��ȡ��ǰ���ַ���
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
            	if("".equals(a0.getText())) // �ų�  7*=  7+= ֮�����
            		break;
            	b = a1.getText();
            	
            	calculation(a, b);
                a2.setText(String.valueOf(value));
                break;
            default: ;   // �ڱ�������������û���������
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
