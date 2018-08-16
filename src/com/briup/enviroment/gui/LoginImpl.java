/*package com.briup.enviroment.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.briup.enviroment.gui.MainLayout;
import com.briup.environment.gui.User;
import com.briup.enviroment.gui.UserDao;
import com.briup.enviroment.gui.UserDaoImpl;

public class LoginImpl implements Login{

	//登录图片的路径
	private String path="src/login.png";//图片直接放在包下面
	//登录页面背景图片路径
	private String path2="src/background.jpg";
	//控制弹出的对话框是否退出
	private	Dialog dig;
	@Override
	public void login() throws Exception {
		final JFrame frame=new JFrame("用户登录");
		frame.getContentPane().setBackground(new Color(220,230,240));//红绿蓝
		//布局的开始偏移量
		frame.setLocation(300, 200);
		
		 * java默认为flowLayout布局，设置为null即为清空布局管理器，之后添加组件，常常是设置组件左上角坐标相对于容器左上角（0,0）
		 * 的x,y值来确定组件的位置，即使更改容器大小也不会改变位置，这种方式常常用于窗体大小固定的容器里
		 
		//手动布局
		frame.setLayout(null);//手工布局
		//设置窗口大小
		frame.setSize(420,370);
		
		JLabel labelImage=new JLabel(new ImageIcon(path));//添加图片
		labelImage.setSize(80,80);//设置图片的大小
		labelImage.setLocation(40, 190);//设置图片的绝对位置
		
		JLabel backImage=new JLabel(new ImageIcon(path2));
		backImage.setSize(420,170);
		backImage.setLocation(0,0);
		
		JLabel ac=new JLabel("注册账号");
		//设置字体颜色
		ac.setForeground(Color.blue);
		ac.setSize(60,30);
		ac.setLocation(335,195);
		
		Label pwd =new Label("找回密码");
		pwd.setForeground(Color.blue);
		pwd.setSize(60,30);
		pwd.setLocation(335, 225);
		
		final TextField username=new TextField();
		username.setSize(195,25);
		username.setLocation(130, 195);
		
		final JPasswordField password=new JPasswordField();
		password.setSize(195, 30);
		password.setLocation(130, 225);
		
		Checkbox cb1=new Checkbox("记住密码");
		cb1.setSize(75,20);//大小
		cb1.setLocation(130, 260);//绝对位置
		Checkbox cb2=new Checkbox("自动登录");
		cb2.setSize(75, 20);
		cb2.setLocation(260, 260);
		
		JButton jbt=new JButton();
		jbt.setText("登录");//setName和setText（）
		jbt.setSize(195, 30);
		jbt.setLocation(130, 285);
		
		frame.setResizable(false);//设置是否可以拖拽
		frame.add(labelImage);
		frame.add(backImage);
		frame.add(ac);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
		frame.add(pwd);
		frame.add(cb1);
		frame.add(cb2);
		frame.add(jbt);
		frame.add(password);
		frame.add(username);
	
		
		 * 给窗口注册一个事件监听，当你点击了窗口右上方的叉号时，此方法被调用，出现想要退出吗和是，否来个按钮
		 
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				 * Dialog(Frame owner, String title, boolean modal) 
                 * 构造一个最初不可见的 Dialog，它带有指定的所有者 Frame、标题和模式。
				 
				dig = new Dialog(frame, "确认退出", true);
		    	Panel panel = new Panel();
		    	Button yes = new Button("是");
		    	Button no = new Button("否");
		    	panel.add(yes);
		    	panel.add(no);
		    	//设置窗口相对于指定组件的位置,null则此窗口将置于屏幕的中央
		    	dig.setLocationRelativeTo(null);
		    	yes.addActionListener(new ActionListener() {// 内部类形式注册及重写事件处理方法
			    	public void actionPerformed(ActionEvent e) {
			    		//关闭窗体,释放资源
			    		frame.dispose();//dispose() 释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源
			    		System.out.println("测试fram");
			    	}
		    	});
		    	no.addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent e) {
		    			//释放对话框资源
		    			dig.dispose();//为什么点击否的时候 不能单单只释放对话框的资源
		    			System.out.println("测试对话框");
		    		}
		    	});
		    	dig.add(new Label("想要退出吗？"));
		    	dig.add(panel, "South");
		    	dig.setSize(200, 100);
		    	dig.setVisible(true);
			}
	});
		
		 * 添加鼠标监听事件，当点击注册字体时，重新创建一个布局
		 * 并且将原来的布局释放
		 * 
		 
		ac.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//另外写一个类来实现注册
				RegistLayout registLayout = new RegistLayout();
				frame.dispose();
			}
		});
		
		 * 登录按钮注册监听,如果用户名和密码正确,跳转到
		 * 环境控制主界面否则还在当前页面
		 
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				UserDao dao = new UserDaoImpl();
				try {
					User user = (User) dao.find(username.getText());
					if(user!=null) {
						if(user.getPwd().equals(password.getText())) {
							
							 * 消息提示框
							 * 第一个参数是控制弹出对话框相对的中心位置，如果是null，则是在屏幕中间
							 * 第二个参数是输出的内容信息
							 * 第三个参数是输出的标题信息
							 * 第四个参数是输出时左边显示的图表样式
							 
							JOptionPane.showMessageDialog(null, "恭喜您,登录成功", "信息提示！",JOptionPane.PLAIN_MESSAGE);
							MainLayout ml=new MainLayout();//为跳转的界面
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误！", "信息提示！", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "该用户不存在！", "信息提示！", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
			
		
	}
	public static void main(String[] args) {
		Login login=new LoginImpl();
		try {
			login.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/