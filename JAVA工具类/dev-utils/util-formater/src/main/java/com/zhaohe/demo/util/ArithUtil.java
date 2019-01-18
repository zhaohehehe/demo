package com.zhaohe.demo.util;

import java.math.BigDecimal;

/**
 * 浮点数四则运算
 * @author zhaohe
 *
 */
public class ArithUtil {
	//默认除发运算精度
	private static final int DEF_DIV_SCALE=8;
	private ArithUtil(){}
	public static double divide(double a,double b){
		BigDecimal a1=BigDecimal.valueOf(a);
		BigDecimal b1=BigDecimal.valueOf(b);
		return b1.divide(a1, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static void main(String[] args) {
		System.out.println(ArithUtil.divide(10, 3));
	}
}
