package com.briup.environment.gui;

import com.briup.environment.util.SystemUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

//用户注册部分
public class Register extends JFrame{
    private static final long serialVersionUID = -4868535842017956748L;
    private JPanel contentPane;
    private JTextField yonghu;
    private JPasswordField mima;
    private JPasswordField again;
    
    String newuser,newkey,newagain,gender,info;
    
    private JRadioButton man;
    private JRadioButton woman;
    private JTextArea userinfo;
    
    static Point origin = new Point();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 250) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2;
        setBounds(w, h, 250, 400);
        setUndecorated(true);
        contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(null);

        String path = null;
        path = SystemUtil.imgPath+"background3.jpg";
        ImageIcon background = new ImageIcon(path);
        // 把背景图片显示在一个标签里面
        JLabel label = new JLabel(background);
        // 把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0, -1, this.getWidth(), this.getHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 把背景图片添加到分层窗格的最底层作为背景
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        //设置可见  */
        this.addMouseListener(new MouseAdapter() {
            // 按下(mousePressed
            // 不是点击，而是鼠标被按下没有抬起)
            public void mousePressed(MouseEvent e) {
                // 当鼠标按下的时候获得窗口当前的位置
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            // 拖动（mouseDragged
            // 指的不是鼠标在窗口中移动，而是用鼠标拖动）
            public void mouseDragged(MouseEvent e) {
                // 当鼠标拖动时获取窗口当前位置
                Point p = getLocation();
                // 设置窗口的位置
                // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
                        - origin.y);
            }
        });

        JLabel lblNewLabel = new JLabel("用户名");
        lblNewLabel.setBounds(55, 30, 54, 15);
        contentPane.add(lblNewLabel);
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 12));

        yonghu = new JTextField();
        yonghu.setBounds(55, 50, 140, 21);
        yonghu.setOpaque(false);
        yonghu.setFont(new Font("黑体", Font.PLAIN, 15));
        yonghu.setCaretColor(Color.white);
        yonghu.setForeground(Color.white);

        contentPane.add(yonghu);
        yonghu.setColumns(10);
        yonghu.setBorder(new LineBorder(Color.white));

        ButtonGroup bg=new ButtonGroup();        
        man = new JRadioButton();
        man.setOpaque(false);
		bg.add(man);
		man.setText("男");
		man.setForeground(Color.white);
		man.setBounds(55, 80, 54, 15);
		
        
		woman=new JRadioButton();
		woman.setOpaque(false);
		woman.setForeground(Color.white);
		bg.add(woman);
		woman.setText("女");
		woman.setBounds(150, 80, 54, 15);
		contentPane.add(man);
		contentPane.add(woman);
        
        
        
        JLabel userinfoLabel = new JLabel("简历");
        userinfoLabel.setBounds(55, 100, 54, 15);
        
        contentPane.add(userinfoLabel);
        userinfoLabel.setForeground(Color.white);
        userinfoLabel.setFont(new Font("黑体", Font.PLAIN, 12));

        userinfo = new JTextArea(10,10); //设置的行数没有起到作用
        userinfo.setText("");
        userinfo.setRows(100);
        userinfo.setColumns(10);
        userinfo.setLineWrap(true);
        userinfo.setBounds(55,120, 140, 21);
        
        userinfo.setOpaque(false);
        //userinfo.setBorder(null);
        userinfo.setFont(new Font("黑体", Font.PLAIN, 15));
        userinfo.setCaretColor(Color.white);
        userinfo.setForeground(Color.white);

        userinfo.setBorder(new LineBorder(Color.white));
        contentPane.add(userinfo);
        
        
        
        
        
        JLabel lblNewLabel_1 = new JLabel("密码");
        lblNewLabel_1.setBounds(55, 150, 54, 15);
        contentPane.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.white);
        lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 12));

        mima = new JPasswordField();
        mima.setBounds(55, 170, 140, 21);
        contentPane.add(mima);
        mima.setColumns(10);
        mima.setOpaque(false);
        mima.setBorder(new LineBorder(Color.white));
        mima.setFont(new Font("", Font.PLAIN, 15));
        mima.setCaretColor(Color.white);
        mima.setForeground(Color.white);

        
        JLabel lblNewLabel_2 = new JLabel("确认密码");
        lblNewLabel_2.setBounds(55, 200, 54, 15);
        contentPane.add(lblNewLabel_2);
        lblNewLabel_2.setForeground(Color.white);
        lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 12));

        again = new JPasswordField();
        again.setBounds(55, 220, 140, 21);
        contentPane.add(again);
        again.setColumns(10);
        again.setOpaque(false);
        again.setBorder(new LineBorder(Color.white));
        again.setFont(new Font("", Font.PLAIN, 15));
        again.setCaretColor(Color.white);
        again.setForeground(Color.white);

        
        
        final JLabel label1 = new JLabel("");
        label1.setBounds(60, 240, 200, 15);
        contentPane.add(label1);

        JButton btnNewButton_3 = new JButton("注 册");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newuser = yonghu.getText();
                newkey = new String(mima.getPassword());
                newagain =new String(again.getPassword());
                info=userinfo.getText();
                System.out.println("usrinfo的信息为:"+info);
                System.out.println(newkey.equals(newagain)+"*********");
                if(man.isSelected()){
                	gender="男";
                }else{
					gender="女";
				}
                System.out.println("性别:"+gender);
                if(newkey.equals(newagain)){
                    User user = new UserImpl();
                    boolean flag= user.searchByName(newuser);
                    System.out.println("数据库中是否存在当前注册用户："+flag);
                    if (flag==false){
                        if(user.register(newuser,newkey,gender,info)){
                            SystemUtil.alert("注册成功!",1);
                        }else {
                            SystemUtil.alert("注册失败!",0);
                        }
                    }else {
                        label1.setText("用户名已存在");
                        label1.setForeground(Color.red);
                        label1.setFont(new Font("黑体", Font.PLAIN, 12));
                    }
                }else {
                    again.setText("");
                    label1.setText("密码不一致");
                    label1.setForeground(Color.red);
                    label1.setFont(new Font("黑体", Font.PLAIN, 12));
                }
            }
        });
        btnNewButton_3.setBounds(55,270, 140, 23);
        contentPane.add(btnNewButton_3);
        btnNewButton_3.setForeground(Color.white);
        btnNewButton_3.setBackground(Color.GRAY);
        btnNewButton_3.setFont(new Font("黑体",Font.PLAIN, 14));
        btnNewButton_3.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton btnNewButton = new JButton("返 回");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login frame = new Login();
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(55, 310, 140, 23);
        btnNewButton.setFont(new Font("黑体",Font.PLAIN, 14));
        contentPane.add(btnNewButton);
        btnNewButton.setForeground(Color.white);
        btnNewButton.setBackground(Color.GRAY);
        btnNewButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
