package com.briup.environment.util;

public interface BackUP extends EmdcModule{

	/*
	 * 
	 */
	public void store(String filePath, Object obj,boolean append) throws Exception;
	public Object load(String filePath, boolean del) throws Exception;
}
