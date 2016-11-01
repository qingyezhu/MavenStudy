package com.qingyezhu.common.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListTest {

	private static final Logger logger = LoggerFactory.getLogger(ArrayListTest.class);
	@Test
	public void testRemoveAll(){
		List<Integer> integerList = new ArrayList<>();
		integerList.add(1);
		integerList.add(11);
		integerList.add(12);
		integerList.add(112);
		
		List<String> stringList = new ArrayList<String>();
		stringList.add("abc");
		
		logger.info("removeAll={}", integerList.removeAll(stringList));
	}
	
	@Test
	public void testToArray(){
		List<Integer> integerList = new ArrayList<Integer>(){
			{
				add(11);
				add(21);
				add(131);
			}
		};
		Object[] objArr = integerList.toArray();
		logger.info("ObjectArr={}, class={}", Arrays.toString(objArr), objArr.getClass());
		
		for(Object obj : objArr){
			logger.info("object={}, class={}", obj, obj.getClass());
			Integer integer = (Integer) obj;
			logger.info("Integer={}", integer);
		}
		
//		Integer[] integerArr = (Integer[]) objArr;//运行出错，类型转换失败
		Integer[] integerArr = integerList.toArray(new Integer[0]);
		logger.info("IntegerArr={}, class={}", Arrays.toString(integerArr), integerArr.getClass());
	
		int[] intArr = {1, 3, 234, 4};
		logger.info("intArr={}, class={}", Arrays.toString(intArr), intArr.getClass());

//		Object[] tmpObjArr = integerArr;
//		Object[] tmpObjArr = intArr;//编译出错，基本类型的数组不能转换为引用类型的数组
		
//		int[] tmpIntArr = integerList.toArray(intArr);//编译出错，需要的是基本类型的包装类
		
	}
}
