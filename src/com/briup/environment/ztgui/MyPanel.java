package com.briup.environment.ztgui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class MyPanel extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //绘制一张背景图片  2.jpg是图片的路径  自己设定为自己想要添加的图片
        Image image = new ImageIcon("src/login.jpg").getImage();
        g.drawImage(image, 0, 0, this);
    }
}
 
