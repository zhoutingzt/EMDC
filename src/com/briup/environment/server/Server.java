package com.briup.environment.server;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * Server�����������server����������ʼ���ܿͻ��˷��͵���Ϣ���ҵ���DBstore�����ܵ������ݳ־û�
 */
public interface Server extends EmdcModule{
//Collection<Environment>
	public void reciver() throws Exception;
	//�÷���������Server��ȫ��ֹͣ����
	public void shutdown() throws Exception;
}
