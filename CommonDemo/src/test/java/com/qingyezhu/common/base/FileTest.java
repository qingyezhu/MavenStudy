package com.qingyezhu.common.base;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

public class FileTest {

	@Test
	public void testFilter(){
		//匿名内部类：没有名字，以及构造器，调用外部的变量，要求该变量是被final修饰的
		final String suffix = "txt";
		String dirPath = "D:\\";
		File dir = new File(dirPath);
		dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				boolean ret = isFile(pathname) || endsWith(pathname.getAbsolutePath());
				return ret;
			}
			
			private boolean isFile(File currentFile){
				return currentFile.isFile();
			}
			
			private boolean endsWith(String path){
				return path.endsWith(suffix);
			}
		});
	}
}
