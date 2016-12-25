package com.qingyezhu.common.base;

import org.junit.Test;

public class IntegerTest {

	@Test
	public void testIntToByte(){
		//字面量的赋值，当是在其数值范围内时，就不需要进行强制转换，否则需要强制转换，丢失高位
		//当是变量的复制时，则会进行强制类型转换
		int i0 = 1, i1 = 128;
		byte b0 = 1, b1 = (byte) 128, b2 = (byte) i0, b3 = (byte) i1;
		
		Byte b4 = 0, b5 = (byte) 128, b6 = (byte) i0;
		//自动类型转换
		int i2 = b1, i3 = b5;
		Integer i4 = i2;
		
		//当有对应类型的则使用对应的类型，若没有则看看有没有包装类，即进行装箱，若再没有，则进行自动类型转换，看起高类型是否存在
		add(1, 2);
//		add((byte)1, (byte)2);
		add(1, 128);
//		add((byte)1, (byte)128);
		add(b0, b1);
		add(b4, b4);
		add(i0, i1);
//		add((byte)i0, (byte)i1);
		add(i4, i4);
	}
//	private void add(byte b1, byte b2){
//		//byte/Byte，其他的强制类型转换
//		System.out.println(b1 + "=b1 add byte b2=" + b2);
//	}	
	
	private void add(int i1, int i2){
		//是int或Integer,或者是低于int的byte/short,char以及其对应的包装类
		System.out.println(i1 + "=i1 add int i2=" + i2);
	}
//	
//	private void add(Byte b1, Byte b2){
//		//只需要byte或Byte类型
//		System.out.println(b1 + "=b1 add Byte b2=" + b2);
//	}
	
//	private void add(Integer i1, Integer i2){
//		//只需要是int或Integer类型
//		System.out.println(i1 + "=i1 add Integer i2=" + i2);
//	}
}
