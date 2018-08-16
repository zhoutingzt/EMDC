package com.briup.environment.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.Log;
import com.briup.environment.util.LogImpl;

public class DBStoreImpl implements DBStore{
	private String driver;
	private String url;
	private String username;
	private String password;
	private Connection connection;
	private PreparedStatement ps;
	private int batchSize;
//	private static Log log=new LogImpl();
//	private BackUpImpl back=new BackUpImpl();
	private static Configuration configuration=new ConfigurationImpl();
	private static Log log;
	private BackUpImpl back;
	@Override
	public void savetoDB(Collection<Environment> c) throws Exception {
		try {
			//System.out.println("fdsf:"+driver);
			Class.forName(driver);
			connection=DriverManager.getConnection(url,username,password);
			//表示当前批处理中sql语句数
			int count=0;
			//记录当前的天数，默认为0
			int day=0;
			//System.out.println("获取数据库的连接");
			Collection<Environment> collback=(Collection<Environment>) back.load("dbstoreback",true );
			if(collback!=null){
				log.info("正在准备装入备份数据");
				c.addAll(collback);
				log.info("备份数据装载完成");
			}
			
			for (Environment e : c) {
				/*
				 * 在俩中情况下需要创建新的ps对象
				 * ①ps==null 第一次进入到for循环中
				 * ②当日期发生变化的时候，day!=当前对象的采集时间
				 * environment.getGather_date()返回的是TimeStamp类
				 * TimeStamp对象的getDate()方法返回day of month
				 * TimeStamp对象的getDay()方法返回0-6对应的星期天-星期六
				 */
				if(ps==null||day!=e.getGather_date().getDate()){
					day=e.getGather_date().getDate();
					log.info("数据入库的天数："+day);
					
					//System.out.println("数据入库的天数："+day);
					/*
					 * 日期发生变化1号 变成2号
					 * 为了防止1号存在没有处理的数据
					 * 举例：批处理缓冲中存在500调数据，1号有300条，2号有200条 需要插入到不同的表
					 */
					if(ps!=null){
						//处理前一天留下的sql语句
						ps.executeBatch();
						//清空缓存
						ps.clearBatch();
						//关闭ps,构建新的sql语句的ps对象
						ps.close();
					}
					//根据日期不同，产生不同的sql语句
					String sql="insert into e_detail_"+day+" values(?,?,?,?,?,?,?,?,?)";
					ps=connection.prepareStatement(sql);
				}
				ps.setString(1, e.getName());
				ps.setString(2, e.getSrcId());
				ps.setString(3, e.getDesId());
				ps.setString(4, e.getSersorAddress());
				ps.setInt(5, e.getCount());
				ps.setString(6, e.getCmd());
				ps.setInt(7, e.getStatus());
				ps.setFloat(8, e.getData());
				ps.setTimestamp(9, e.getGather_date());
				//将sql语句放到皮处理中
				ps.addBatch();
				/*
				 * 记录当前批处理中sql语句的数量，当满足批处理条数要求是提交或者如果for循环中存在未处理完毕的sql语句，也就是在for循环结
				 * 结束是，批处理中仍有未处理的语句同样提交
				 */
				count++;
				if(count%batchSize==0){
					ps.executeBatch();
					ps.clearBatch();
				}
				
				
			}
			if(ps!=null){
				ps.executeBatch();
				ps.clearBatch();
				ps.close();
			}
		} catch (ClassNotFoundException e) {
			log.error("发生错误，数据回滚");
            back.store("dbstoreback",c,false);
			e.printStackTrace();
		}
	}
	@Override
	public void init(Properties properties) throws Exception {
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		username=properties.getProperty("username");
		password=properties.getProperty("password");
		batchSize=Integer.parseInt(properties.getProperty("batch-size"));
	}
	@Override
	public void setConfiguration(Configuration configuration) {
		try {
			log=configuration.getLog();
			back=(BackUpImpl) configuration.getBackup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
