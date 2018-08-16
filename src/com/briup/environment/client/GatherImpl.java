package com.briup.environment.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.BackUpImpl;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import com.briup.environment.util.LogImpl;

public class GatherImpl implements Gather{
	//Environment对象集合用来保持获取的数据对象
    Collection<Environment> collection=new ArrayList<Environment>();
	//采集原始数据文件
    private String path;
   // private String path="src/radwtmp";
    //保存上一次读取字节数的文件
    private String path2;
   // private String path2="src/record";
    //声明为静态的话就直接加载进来
    private static LogImpl log;
    private BackUpImpl back;
   // private static LogImpl log=new LogImpl();
   // private BackUpImpl back=new BackUpImpl();
    private static Configuration configuration;
	@Override
	public Collection<Environment> gather() throws Exception {
		/*
		 * 1.从path2指定的路径读取保存上一次读取到的字节数的文件，第一次文件不存在，需要分情况
		 * 2.读取radwtmp文件的有效字节数，将返回的值保存在path2指定的文件中
		 * 3.先略过上一次读取的字节数，再进行本次解析
		 */
		File file=new File(path2);
		long num=0;
		if(file.exists()){
			DataInputStream dis=new DataInputStream(new FileInputStream(file));
			num=dis.readLong();
		}
		/*
		 * randomAccessFile流实现了略过功能
		 * 创建时提供来个参数，第一参数表示读取的文件路径，第二个参数表示已什么形式读取，r表示只读
		 */
		RandomAccessFile rf=new RandomAccessFile(path, "r");
		rf.seek(num);
		long num2=rf.length();
		
		//往path2中写入数据时 如果文件不存在就会自动创建文件
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(path2));
		dos.writeLong(num2);
		/*
		 * 1.构建缓存字符流按行读取数据
		 * 2.根据|分割字符串，根据第四个字段的不同分别赋予温度、湿度、二氧化碳，光照强度的环境名称
		 * 3.第七个字段表表示16进制环境值，将16进制换成10进制 如果是温度和湿度数据，前俩个字节是温度数据，中间俩个字节是湿度数据，
		 * 如果二氧化碳和光照前度前俩个字节就是对应的数据
		 * 4.温度和湿度是同一条记录，读取一行需要创建俩个对象，并且分别赋值
		 * 5.封装好的对象添加到collection集合中
		 */
		String s=null;
		String str[]=null;
		Environment environment=null;
		//count统计温度和湿度条数（str[3]是16）
		int count=0;
        //count2统计光照前度条数（str[3]是256）
		int count2=0;
		//count3统计二氧化碳条数（str[3]是1280）
		int count3=0;
		
		 Object obj=back.load("mapback",false);//加载完不删除
	        if(obj!=null){
	            //将上次采集的数据加入到collection集合中
	            log.warn("还原备份数据");
	            collection.addAll(collection);
	        }      
		
		while((s=rf.readLine())!=null){
			environment=new Environment();
			str=s.split("[|]");
			environment.setSrcId(str[0]);
			environment.setDesId(str[1]);
			environment.setDevId(str[2]);
			environment.setSersorAddress(str[3]);
			environment.setCount(Integer.parseInt(str[4]));
			environment.setCmd(str[5]);
			environment.setStatus(Integer.parseInt(str[7]));
			Long time =new Long(str[8]);
			Timestamp gather_date=new Timestamp(time);
			environment.setGather_date(gather_date);
			if("16".equals(str[3])){//str[3].equals("16")
				//根据温度转换公式将16进制装换成10进制
				float value=(float)((Integer.parseInt(str[6].substring(0, 4), 16)*0.00268127)-46.85);
				environment.setName("温度");
				environment.setData(value);
				collection.add(environment);
				//environment2=new Environment();
				//environment2=environment;
				
				environment=new Environment();
				environment.setSrcId(str[0]);
				environment.setDesId(str[1]);
				environment.setDevId(str[2]);
				environment.setSersorAddress(str[3]);
				environment.setCount(Integer.parseInt(str[4]));
				environment.setCmd(str[5]);
				environment.setStatus(Integer.parseInt(str[7]));
				environment.setGather_date(gather_date);
				//根据湿度转换公式将16进制装换成10进制
				float value2=(float)((Integer.parseInt(str[6].substring(4,8), 16)*0.00190735)-6);
				environment.setName("湿度");
				environment.setData(value2);
				collection.add(environment);
				count++;
			
			}else{
				float value=Integer.valueOf(str[6].substring(0,4),16);
				if("256".equals(str[3])){
					environment.setName("光照强度");
					environment.setData(value);
					collection.add(environment);
					count2++;
				}if("1280".equals(str[3])){
					environment.setName("二氧化碳");
					environment.setData(value);
					collection.add(environment);
					count3++;
				}
			}
			}
		log.info("备份采集模块的的数据");
		back.store("gatherback", collection, true);
		log.debug("采集环境数据："+collection.size());
		log.info("温度、湿度："+count);
		log.info("光照强度："+count2);
		log.info("二氧化碳："+count3);
//		System.out.println("采集的环境数据："+collection.size());
//		System.out.println("温度湿度："+count);
//		System.out.println("光照强度："+count2);
//		System.out.println("二氧化碳："+count3);
		return collection;
	}

	
	@Override
	public void init(Properties properties) throws Exception {
		path=properties.getProperty("src-file");
		path2=properties.getProperty("record-file");
		
	}

	@Override
	public void setConfiguration(Configuration configuration1) {
		this.configuration=configuration1;
		try {
			log=(LogImpl) configuration.getLog();
			back=(BackUpImpl) configuration.getBackup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception{
		configuration=new ConfigurationImpl();
		GatherImpl gather=(GatherImpl) configuration.getGather();
		try {
			Collection<Environment> collection=gather.gather();
			System.out.println("大小："+collection.size());
//			for (Environment environment : collection) {
//				System.out.println(environment);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
