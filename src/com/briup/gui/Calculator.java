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
	
	// ��һ���������ʲô��
	
	private static final long serialVersionUID = -1047298397568411277L;
	private JTextField textField;
    private ActionListener myListener;
    
	public Calculator() {
	
		setTitle("������"); // ��������Ϊ������
		setSize(300, 300); // ���ô���Ϊ 300x300
		setLocationRelativeTo(null); // ����λ��Ϊ����
		setResizable(false); // ����Ϊ��С���ɱ��
		setDefaultCloseOperation(EXIT_ON_CLOSE); // �����˳�
		
		
		Container container = getContentPane(); // �½�һ������
        container.setLayout(new BorderLayout(5, 5)); // �������Ҽ��Ϊ5����
        
        
		JPanel pnlNorth = new JPanel();  // �������
		JPanel pnlCenter = new JPanel();  // �м����
		
        // ��textFeild��panel ���ŵ�������
		container.add(pnlNorth, BorderLayout.NORTH); // ��pnlNorth���ڱ���
        container.add(pnlCenter, BorderLayout.CENTER); // ��pnlCenter����center��
		
        textField = new JTextField();  // �ı�������
        //textField.setCaret( new Font("����", Font.PLAIN, 16));
        textField.setEditable(false);  // �����޸�
        textField.setHorizontalAlignment (JTextField.RIGHT); //��������Ҷ���
        
        
        //---------------
        pnlNorth.setLayout(new BorderLayout(5, 5)); // �����ϱߵ��ı���Ͱ�ť��λ��Ϊ�߽粼��
        //---------------
        
        JButton btnClear = useButton("<<<"); // newһ��button��ť
        pnlNorth.add(textField, BorderLayout.CENTER); // ���ı���ŵ��м�
		pnlNorth.add(btnClear, BorderLayout.EAST); // ����ť�ŵ�����ߣ�dong��
  
        String[] titles = {
				"7", "8", "9", "+", 
				"4", "5", "6", "-",
				"1", "2", "3", "*",
				"0", ".", "/", "="
		};
		
		// ��������Ű�ť��λ�õ�panelΪ���񲼾�
		// 4x4������ button��button֮��ľ���Ϊ3����
		pnlCenter.setLayout(new GridLayout(4, 4, 3, 3));
		for (int i = 0; i < titles.length; i++) {
			JButton btnNum = useButton(titles[i]);
			// btnNum.setFont(new Font("����", Font.PLAIN, 16));
			// ���������ֱ��ʾ�� ���壬��ʽ�����壬б��ȣ����ֺ�
			btnNum.setFont(new Font("΢���ź�",Font.PLAIN, 24));
			pnlCenter.add(btnNum);
		}
       
    }
    
	public JButton useButton (final String titles) {
		/*
		 * String.valueof(x)  ����x���ַ�����ʾ
		 * �����titles֮����Ҫ��final���� ��Ϊ�˲����޸�
		 * Ȼ�����ʹ��String��valueof������Ϊ�˽�
		 */
		JButton button = new JButton(String.valueOf(titles));
		if(myListener == null){
			myListener = new ActionListener(){
				public void actionPerformed(ActionEvent event){
      				String  btnInformation  = event.getActionCommand();//������˶�����ص������ַ���
    				char key2 = btnInformation.charAt(0);
    				System.out.println("�����key2��ֵ��"+key2);
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
	        		textField.setText("");
	        		textField.setText(String.valueOf(key2));
	        		flag = false;
				} else {
					text = textField.getText() + key2;
		        	textField.setText(text);
		        	text = "";  // ��Ϊtext����ÿ��ʹ������֮�������յĲ���
											
				}
	            break;
	            
	        case '.':   
	        	if("".equals(textField.getText()))
	        		break; // ��һ��Ԫ�ز���Ϊ.    
	        	
	        	if(!"".equals(textField.getText())){
	        		text = textField.getText();
					len = text.length();
					int i = 0;
					for(; i<len; i++) {
						if('.' == text.charAt(i)){
							break; // ��������������
						}
					} // ȷ���ַ������Ƿ��Ѿ�����.
					if(i<len)  //  һ���ַ����в��ܳ��ֶ��. 
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
            	text = textField.getText(); // ��ȡ��ǰ���ַ���
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
            	if("".equals(textField.getText())) // �ų�  7*=  7+= ֮�����
            		break;
            	b = textField.getText();
            	
            	calculation(a, b);
                textField.setText(String.valueOf(value));
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
		new Calculator().setVisible(true);
    }
}