package com.qingyezhu.common.util;

import org.apache.commons.lang.ArrayUtils;

public class IpUtils {

	/**
	 * ip字符串转换为数值<br/>
	 * 四部分，每部分的数据范围是[0-256)，故每部分的数据最多8位，故用一个32位来存储数据<br/>
	 * @param ipStr
	 * @return
	 */
	public static long ipToNumber(String ipStr){
		int[] ipArr = getIpNumArr(ipStr);
		long ret = -1;
		if(ArrayUtils.isNotEmpty(ipArr)){
			ret = ipArr[3];
			ret += ipArr[2] << 8;
			ret += ipArr[1] << 16;
			//此处结果超过int，故要先转换为long
			ret += (ipArr[0]*1L) << 24;
		}
		return ret;
	}

	private static int[] getIpNumArr(String str) {
		String[] strArr = str.split("[.]");
		if(ArrayUtils.isEmpty(strArr) || strArr.length != 4){
			return null;
		}
		int[] numArr = new int[4];
		for(int i = 0, len = strArr.length;i < len;i ++){
			numArr[i] = Integer.parseInt(strArr[i]);
		}
		return numArr;
	}
	
	/**
	 * 将数值转换为ip字符串
	 * @param ipNum
	 * @return
	 */
	public static String numberToIp(long ipNum){
		long numa = ipNum & 0x000000ff,
		    numb = (ipNum & 0x0000ffff) >> 8,
			numc = (ipNum >> 16) & 0x000000ff,
			numd = ipNum >> 24;
		return String.format("%d.%d.%d.%d", numd, numc, numb, numa);
	}
}
