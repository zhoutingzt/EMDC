package com.briup.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ClientTest {

	public static void main(String[] args) {
        Socket socket=null;
        OutputStream os=null;
        ObjectOutputStream oos=null;//���͵Ķ��� ����֧�� java.io.Serializable �� java.io.Externalizable �ӿڵĶ�����ܴ�����ȡ
        File file=new File("src/test.txt");
        FileInputStream fis;
        
		try {
			System.out.println("�ͻ��˿�ʼ��ȡ�ļ�....");
			fis = new FileInputStream(file);
			InputStreamReader isr=new InputStreamReader(fis);//���ֽ���ת���ַ���
		    BufferedReader br=new BufferedReader(isr);
		    String str=null;
		    String s[]=null;
		    Student student=null;
		    StudentCom sc=new StudentCom();
		  //  Student sc=new Student();
		    Set<Student> set=new TreeSet<Student>(sc);
		   //List<Student> list=new ArrayList<Student>();
		    while((str=br.readLine())!=null){
		    	student=new Student();
		    	s=str.split("[:]");
		    	//������
//		    	student.setId(Integer.parseInt(s[0]));
//		    	student.setName(s[1]);
//		    	student.setAge(Integer.parseInt(s[2]));
//		    	student.setGender(s[3]);
//		    	student.setScore(Integer.parseInt(s[4]));
		    	student=new Student(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),s[3],Integer.parseInt(s[4]));
		    	set.add(student);
		      //  System.out.println(student);
		    }
		    
		    System.out.println("�ͻ������ݲɼ����");
		    System.out.println("�ͻ��˺ͷ����������ڽ�������");
		    socket=new Socket("127.0.0.1",10000);
		    System.out.println("���ӳɹ�");
		    os=socket.getOutputStream();
		    oos=new ObjectOutputStream(os);
		    oos.writeObject(set);//���͵Ķ�����Ҫʵ��Serializable
		    oos.flush();
		    System.out.println("�ͻ��˷������");
//		    for (Student student2 : list) {
//				System.out.println(student2);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {//�󿪵��ȹ�
			if(oos!=null)
					oos.close();
			if(os!=null)
				os.close();
			if(socket!=null)
				socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
		}
		}
	}
}
//�Զ�������
class StudentCom implements Serializable,Comparator{//����д��������֮�䶼��� ͬʱ����ɶ��ԱȽϸ�
	/*
	 * �����Ϊ����Ȼ����
     * �Զ���������Ҫʵ��java.util.Comparator,��дcompare�������÷�������int���ͣ�-1��ʾ˳�򲻱䣬1��ʾ����λ�� 0��ʾ���ı�
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Student s1=(Student) o1;
		Student s2=(Student) o2;
		//1��ʾ����λ�� -1��ʾ������ 0��ʾ���
		//��ʾ����  �����1��Ϊ-1 -1��Ϊ1�Ļ�Ϊ����
		if(s1.getScore()>s2.getScore()){
			return 1;
		}else if(s1.getScore()<s2.getScore()) {
			return -1;
		}else{
			return 0;
		}
		
	}

}
