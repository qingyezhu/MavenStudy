package com.qingyezhu.common.algorithm;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(SortUtilsTest.class);

	final int[] arr1 = { 12, 31, 1, 8, 9, 9, 7, 1, 1 };

	final int[] arr2 = { 19, 0, 1, 0, 1, 7, -7, 7, -7, 1, -7, 7, 11, 12 };

	final int[] arr3 = { 11, 12, -11, 12, 10, 9, 6, 3, 8 };

	final int[] arr4 = { 1, 3, 7, 9, 10, 8, 6, 4, 2, 0, -1, -2, 9 };

	final int[] arr5 = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2 };

	private void testSort(SortCallback callback) {
		testSort(callback, arr1);
		testSort(callback, arr2);
		testSort(callback, arr3);
		testSort(callback, arr4);
		testSort(callback, arr5);
	}

	private void testSort(SortCallback callback, int[] arr) {
		logger.info("sort before {}", Arrays.toString(arr));
		callback.sort(arr);
		logger.info("sort after {}", Arrays.toString(arr));
	}

	@Test
	public void testDirectInsertSort() {
		SortCallback callback = new SortCallback() {

			@Override
			public void sort(int[] arr) {
				SortUtils.directInsertSort(arr);
			}
		};
		testSort(callback);
	}

	@Test
	public void testBinaryInsertSort() {
		SortCallback callback = new SortCallback() {

			@Override
			public void sort(int[] arr) {
				SortUtils.binaryInsertSort(arr);
			}
		};
		testSort(callback);
	}

	@Test
	public void testBubbleSort() {
		SortCallback callback = new SortCallback() {

			@Override
			public void sort(int[] arr) {
				SortUtils.bubbleSort(arr);
			}
		};

		testSort(callback);
	}

	@Test
	public void testQuickSort() {
		SortCallback callback = new SortCallback() {

			@Override
			public void sort(int[] arr) {
				SortUtils.quickSort(arr);
			}
		};

		testSort(callback);
	}

}
