package com.briup.environment.server;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * DBStore�ṩ�����ģ��Ĺ淶���ýӿ�ʵ���ཫEnvironment���Ͻ��г־û�
 */
public interface DBStore extends EmdcModule{
  public void savetoDB(Collection<Environment> c) throws Exception;
}
