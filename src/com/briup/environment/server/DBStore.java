package com.briup.environment.server;

import java.util.Collection;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;
/*
 * DBStore提供了入库模块的规范，该接口实现类将Environment集合进行持久化
 */
public interface DBStore extends EmdcModule{
  public void savetoDB(Collection<Environment> c) throws Exception;
}
