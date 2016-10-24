package com.qingyezhu.common.algorithm;

import org.apache.commons.lang.ArrayUtils;

public class SortUtils {

	/**
	 * 直接插入排序
	 * 
	 * @param arr
	 */
	public static void directInsertSort(int[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			// 当数组为空或长度为0时，直接返回
			return;
		}
		for (int i = 1, len = arr.length; i < len; i++) {
			int tmp = arr[i], j = i - 1;
			while (j >= 0 && arr[j] > tmp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = tmp;
		}
	}

	/**
	 * 二分插入排序
	 * 
	 * @param arr
	 */
	public static void binaryInsertSort(int[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			// 当数组为空或长度为0时，直接返回
			return;
		}
		for (int i = 1, len = arr.length; i < len; i++) {
			int tmp = arr[i], index = binarySearchLower(arr, 0, i, tmp);
			System.arraycopy(arr, index, arr, index + 1, i - index);
			arr[index] = tmp;
		}
	}

	/**
	 * 二分查找，返回第一个大于key的位置<br/>
	 * 数组arr的查找范围[fromIndex, toIndex)是升序的<br/>
	 * 
	 * @param arr
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return
	 */
	public static int binarySearchUpper(int[] arr, int fromIndex, int toIndex, int key) {
		int left = fromIndex, right = toIndex - 1, mid;
		while (left <= right) {
			mid = (left + right) >>> 1;
			if (arr[mid] <= key) {
				// 倾向于查找右边区，即上线
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(left + "====" + right);
		return left;
	}

	/**
	 * 二分查找，返回最后一个小于key的位置<br/>
	 * 数组arr的查找范围[fromIndex, toIndex)是升序的<br/>
	 * 
	 * @param arr
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return
	 */
	public static int binarySearchLower(int[] arr, int fromIndex, int toIndex, int key) {
		int left = fromIndex, right = toIndex - 1, mid;
		while (left <= right) {
			mid = (left + right) >>> 1;
			if (arr[mid] < key) {
				// 倾向于查找左半区，即下线
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			// 当数组为空或长度为0时，直接返回
			return;
		}
		for (int i = 0, len = arr.length; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	/**
	 * 数组中位置from和to对应的数据进行交换
	 * 
	 * @param arr
	 * @param from
	 * @param to
	 */
	private static void swap(int[] arr, int from, int to) {
		int tmp = arr[from];
		arr[from] = arr[to];
		arr[to] = tmp;
	}

	/**
	 * 快速排序
	 * 
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		quickSort0(arr, 0, arr.length - 1);
	}

	/**
	 * 分治法进行快速排序
	 * 
	 * @param arr
	 * @param fromIndex
	 * @param toIndex
	 */
	private static void quickSort0(int[] arr, int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			// 选取基准值，将数组分为两部分，在基准值的右边的是大于基准值，在基准值的左边的是小于基准值的，之后再对基准值左右两个数组进行以上排序
			int pivotIndex = partition(arr, fromIndex, toIndex);
			// 基准值位置已经确定
			quickSort0(arr, fromIndex, pivotIndex - 1);
			quickSort0(arr, pivotIndex + 1, toIndex);
		}
	}

	/**
	 * 将数组分为两部分，在基准值的左边都是小于等于基准值的，在基准值右边的都是大于等于基准值的<br/>
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	private static int partition(int[] arr, int left, int right) {
		// 一般使用left或right作为基准值
		int pivot = arr[left];
		while (left < right) {
			while (left < right && arr[right] >= pivot) {
				// 基准值右边的必然是大于等于基准值的
				right--;
			}
			// 当有小于基准值的，直接替换到基准值的左边
			arr[left] = arr[right];

			while (left < right && arr[left] <= pivot) {
				// 基准值的左边的必然是小于等于基准值的
				left++;
			}
			// 当有大于基准值的，直接替换到基准值的右边
			arr[right] = arr[left];
		}
		// 确定基准值的位置
		arr[left] = pivot;
		return left;
	}
}
