package com.qingyezhu.common.algorithm;

/**
 * 已知一个升序数组，查找值在数组中的相等的数的范围<br/>
 * 参考SortUtils.java中的binarySearchUpper和binarySearchLower方法<br/>
 * https://leetcode.com/problems/search-for-a-range/<br/>
 * @author zhuwang208531
 *
 */
public class SearchRange {
	public int[] searchRange(int[] nums, int target) {
		int lower = searchLower(nums, target), upper = searchUpper(nums, target);
		if (upper != lower) {
			//当查找的值存在时，必然不相等
			return new int[] { lower, upper - 1 };
		}
		return new int[] { -1, -1 };
	}

	/**
	 * 获取下界
	 * @param arr
	 * @param key
	 * @return
	 */
	private int searchUpper(int[] arr, int key) {
		int left = 0, right = arr.length - 1, mid = 0;
		while (left <= right) {
			mid = (left + right) >>> 1;
			if (arr[mid] <= key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	/**
	 * 获取上界
	 * @param arr
	 * @param key
	 * @return
	 */
	private int searchLower(int[] arr, int key) {
		int left = 0, right = arr.length - 1, mid = 0;
		while (left <= right) {
			mid = (left + right) >>> 1;
			if (arr[mid] < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

}
