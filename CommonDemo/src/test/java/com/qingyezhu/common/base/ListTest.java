package com.qingyezhu.common.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testAddNull(){
		List<Integer> list = new ArrayList<>();
		list.add(new Integer(1));
		list.add(Integer.valueOf(128));
		list.add(Integer.valueOf(2));
		list.add(null);
		list.add(12);
		list.add(-23);
		System.out.println(list);
	}
}
