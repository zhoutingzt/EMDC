package com.briup.environment.ztgui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class MyPanel extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //����һ�ű���ͼƬ  2.jpg��ͼƬ��·��  �Լ��趨Ϊ�Լ���Ҫ��ӵ�ͼƬ
        Image image = new ImageIcon("src/login.jpg").getImage();
        g.drawImage(image, 0, 0, this);
    }
}
 
