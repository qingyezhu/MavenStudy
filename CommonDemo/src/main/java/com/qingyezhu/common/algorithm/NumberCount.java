package com.qingyezhu.common.algorithm;

import java.util.HashSet;
import java.util.Set;

import com.qingyezhu.common.model.NumberCountVo;

public class NumberCount {

	public static void main(String[] args) {
		Set<NumberCountVo> set = new HashSet<NumberCountVo>();

		int[] arr = { 1, 2, 3, 4, 7, 4, 6, 2, 4, 5 };
		for (int i = 0, len = arr.length; i < len; i++) {
			set.add(new NumberCountVo(arr[i]));
		}
		System.out.println(set);
	}

}
