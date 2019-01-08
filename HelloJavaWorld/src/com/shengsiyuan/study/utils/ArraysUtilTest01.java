package com.shengsiyuan.study.utils;

import java.util.Arrays;

public class ArraysUtilTest01 {

	public static void main(String[] args) {
		int[] a=new int[]{1,2,3};
		int[] b=new int[]{1,2,3};
		int[] c=new int[2];
		boolean is=Arrays.equals(a, b);
		System.out.println(is);//T
		System.out.println(a.equals(b));//F
		System.arraycopy(a, 1, c, 0, a.length-1);
		for(int i=0;i<c.length;i++){
			System.out.println(c[i]);//2,3
		}
		
	}
}
