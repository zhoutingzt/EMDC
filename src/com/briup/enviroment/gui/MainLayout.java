
package com.briup.enviroment.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.briup.jdbc.ConnectionFactory;

public class MainLayout extends JFrame implements ActionListener{
    private JButton searchButton;//查询按钮
    private DefaultTableModel defaultModel = null;//默 认的表控制模型  它可以来控制 JTBALE 
    private JTable table = null;//显示和编辑常规二维单元表
    private Connection conn = ConnectionFactory.getConnection();//通过连接工厂创建数据库连接对象
    private Statement statement = null;//创建statement
    private ResultSet rs = null;//创建结果集
    private String time = null;//下拉框时间值
    private String envir = null;//下拉框环境种类值
    private JScrollPane s = null;//滚动条
    JComboBox comboBox = null;//时间下拉框
    JComboBox comboBox2 = null;//环境种类下拉框
//    Dialog dig = null;
    JButton jb = null;//统计数据按钮框
    //初始化整体布局
    public MainLayout() {
    	setTitle("用户查询");
    	setBounds(100, 100, 650, 600);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	init();
    	setVisible(true);
    }
    //初始化组件布局
    public void init() {
        JLabel label=new JLabel("查询日期:");
        comboBox=new JComboBox();  
        comboBox.addItem("19号");  
        comboBox.addItem("20号");  
        comboBox.addItem("21号");  
        comboBox.addItem("22号");  
        comboBox.addItem("23号");  
        comboBox.addItem("24号");  
        comboBox.addItem("25号");  
        comboBox.addItem("26号");  
        comboBox.addItem("29号");  
        JLabel label2=new JLabel("环境种类:"); 
        comboBox2=new JComboBox(); 
        comboBox2.addItem("温度");
        comboBox2.addItem("湿度");
        comboBox2.addItem("光照强度");
        comboBox2.addItem("二氧化碳");
        searchButton = new JButton("查询");
        jb = new JButton("统计数据");
        JPanel north = new JPanel();
        north.add(label);
        north.add(comboBox);
        north.add(label2);
        north.add(comboBox2);
        north.add(searchButton);
        north.add(jb);
        add(north, BorderLayout.NORTH);//默认的布局为bordlayout(边框布局)
        //创建表头
        String[] name = {"环境","发送方id","树莓派id","传感器地址","数据","时间"};
        //创建显示数据
        String[][] data = new String[0][6];
    	/*
    	 * 创建一个默认的表格模型,这是 TableModel 的一个实现
    	 * 它使用一个 Vector 来存储单元格的值对象，该 Vector 
    	 * 由多个 Vector(线程安全,和arrayList类似.效率低,安全) 组成
    	 * DefaultTableModel(Object[][] data, Object[] columnNames) 
         * 构造一个 DefaultTableModel，并通过将 data 和 columnNames 传递到 setDataVector 方法来初始化该表。
    	 */
    	defaultModel = new DefaultTableModel(data,name);
    	/*
    	 * JTable用于展示和编辑规律的平面表格
    	 * setPreferredScrollableViewportSize设置此表视口的首选大小。
    	 * JTable(Object[][] rowData, Object[] columnNames) 
         * 构造一个 JTable 来显示二维数组 rowData 中的值，其列名称为 columnNames。
    	 */
    	table=new JTable(defaultModel);
     	table.setPreferredScrollableViewportSize(new Dimension(400, 80));//设置宽度400和高度80
        /*
         *  Create the scroll pane and add the table to it.
         *  这也是官方建议使用的方式，否则表头不会显示，
         *  需要单独获取到TableHeader自己手动地添加显示
         *  将此表的数据模型设置为defaultModel，并向其注
         *  册以获取来自新数据模型的侦听器通知
         *  参数:defaultModel - 此表的新数据源，
         *  TableModel 接口指定了 JTable 用于询问表格式数
         *  据模型的方法。定义了表格要展示的数据，并控制
         *  是否允许修改，数据更新的监听。
         */
     	s = new JScrollPane(table);
     	table.setModel(defaultModel);
     	table.setBackground(Color.yellow);//显示数据的背景颜色
     	add(new JScrollPane(s));
     	/*
     	 * 分别给时间下拉框,环境种类下拉框,查询按钮,统计按钮
     	 * 添加事件监听
     	 */
     	searchButton.addActionListener(this);
     	comboBox.addActionListener(this);
     	comboBox2.addActionListener(this);
     	jb.addActionListener(this);
    }
   
//    public static void main(String[] args) {
//        new MainLayout();
//    }
    
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();//getSource()获得最初发生 Event 的对象。
		if(source == jb) {
			//获取下拉框时间值,环境类型值
			time = (String) comboBox.getSelectedItem();
        	envir = (String) comboBox2.getSelectedItem();
        	try {
        		//创建statement对象
				statement = conn.createStatement();
				//时间值得到是包含'号'.例如16号,需要截取16
				time = time.substring(0,2);
				//sql语句查询条数,最大值,最小值,平均值,环境种类名称,按照名称进行分组再查询
				String sql = "select count(*),max(data),min(data),avg(data),name from e_detail_"
						+time+" where name = '"+envir+"' group by name";
				System.out.println(sql);
				//执行sql语句
				rs=statement.executeQuery(sql);
				 /*
				  * 构建StringBuffer,将得到的条数,最大值,最小值,平均值追加到
				  * StringBuffer对象上,通过\n换行处理
				  */
				StringBuffer sb = null;
    			while(rs.next()){
    				float count = rs.getFloat(1);
    				float max = rs.getFloat(2);
    				float min = rs.getFloat(3);
    				float avg = rs.getFloat(4);
    				 sb=new StringBuffer();
    				   sb.append(envir+time+"号数据为\n条数:").append(count).append("\n最大值:")
    				   .append(max).append("\n最小值:").append(min).append("\n平均值:").append(avg);
    			}
    			//提示框显示获取到的数据
    			JOptionPane.showMessageDialog(this, sb.toString());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(source==searchButton) {
			/*
			 * 先删除才能再添加,实现刷新效果
			 * getDataVector()返回一个java.util.Vector对象
			 * 再调用removeAllElements()从此向量中移除全
			 * 部组件，并将其大小设置为零。 此方法的功能
			 * 与 clear() 方法的功能相同.
			 * void clear():Removes all of the elements from this Vector.
			 * void removeAllElements():Removes all components from this vector and sets its size to zero.
			 * 	removeAllElements()更完整一点。
			 */
			defaultModel.getDataVector().removeAllElements();
			time = (String) comboBox.getSelectedItem();
        	envir = (String) comboBox2.getSelectedItem();
        	try {
    			statement = conn.createStatement();
    			time = time.substring(0,2);
    			/*
    			 * 需要显示的时间包含秒,需要使用to_char进行格式控制.否则
    			 * 查询到的时间只有年月日.
    			 * 如果没有数据,提示用户.并且返回
    			 * 如果有数据.没产生一条数据构建一个Vector对象
    			 * 添加到defaultModel中
    			 */
    			String sql = "select name,srcid,dstid,sersoraddress,data,to_char(gather_data,'mm-dd hh24:mm:ss') from e_detail_"
    						+time+" where name = '"+envir+"'";
    			System.out.println(sql);
    			rs=statement.executeQuery(sql);
    			if(!rs.next()) {
    				JOptionPane.showMessageDialog(null, "警告:"+envir+time+"号没有数据!", "信息提示！", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
            	while(rs.next()){
    	            	Vector v=new Vector();
    	            	v.addElement(rs.getObject(1));
    	            	v.addElement(rs.getObject(2));
    	            	v.addElement(rs.getObject(3));
    	            	v.addElement(rs.getObject(4));
    	            	v.addElement(rs.getObject(5));
    	            	v.addElement(rs.getObject(6)); 
    	            	System.out.println(v);
    	            	defaultModel.addRow(v);
                }
        	} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
		}
	}
}