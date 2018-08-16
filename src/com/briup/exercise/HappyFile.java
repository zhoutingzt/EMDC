package com.briup.exercise;

import java.io.File;

public class HappyFile {

	public static void main(String[] args) throws Exception{
		File file=new File("D:/"+"hello.txt");
		if(!file.exists()){
			file.createNewFile();
			}
		if(file.isDirectory()){
			System.out.println(file.getName()+"是目录");
		}else if(file.isFile()) {
			System.out.println(file.getName()+"是文件");
		}
        File directory=new File("D:/IOTest");
        if(!directory.exists()){
        	directory.mkdirs();
        }
        System.out.println(directory);
        file.renameTo(new File(directory+"/"+file.getName()));
        File file2[]=directory.listFiles();
        for (int i = 0; i < file2.length; i++) {
			System.out.println(file2[i]);
		}
	}

}
