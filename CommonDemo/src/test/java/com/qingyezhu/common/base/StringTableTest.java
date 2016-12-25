package com.qingyezhu.common.base;

import java.util.UUID;

public class StringTableTest {

	//-XX:StringTableSize
	public static void main(String[] args) {
		for(int i = 0;i < 10000000; i ++){
			uuid();
		}
	}

	private static void uuid() {
		UUID.randomUUID().toString().intern();
	}
}
