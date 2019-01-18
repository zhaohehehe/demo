package com.zhaohe.demo.util;

public class Num2Rmb {
	private static String[] hanAttr ={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	private static String[] unitAttr={"十","百","千"};
	private Num2Rmb(){}
	/**
	 * 拆分成整数+小数
	 * @param num
	 * @return
	 */
	private static String[] divide(double num){
		long zhengshu=(long)num;
		long xiao=(long) ((num-zhengshu)*100);
		//或者
		//long xiao=Math.round((num-zhengshu)*100);
		return new String[]{zhengshu+"",String.valueOf(xiao)};
	}
	/**
	 * 不完整，没有考虑多个0的情况
	 * @param numStr
	 * @return
	 */
	private static String toHanStr(String numStr){
		String result="";
		int numLen=numStr.length();
		for(int i=0;i<numLen;i++){
			int num=numStr.charAt(i)-48;
			if(i!=numLen-1 && num!=0){
				result+=hanAttr[num] + unitAttr[numLen-2-i];
			}else if(num==0){
				result+=hanAttr[num];
			}else{
				result+=hanAttr[num];
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String[] result=Num2Rmb.divide(123.122);
		System.out.println(Num2Rmb.toHanStr(Num2Rmb.divide(100.6)[0]));
		
	}
	
}
