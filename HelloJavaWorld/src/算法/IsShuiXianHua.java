package test.algorithm;

import java.util.Scanner;

/**
 * 水仙花数是指一个 n 位数 ( n≥3 )，它的每个位上的数字的 n 次幂之和等于它本身。（例如：1^3 + 5^3+ 3^3 = 153）
 * 判断是否是水仙花数
 * @author zhaohe
 * 2016年9月30日 下午5:41:45
 *
 */
public class IsShuiXianHua {
	public int sumByOrder(int current){
		int yushu=current%10;
		int shang=current/10;
		if(shang==0){
			return current;
		}
		//4321=1+10*2+10*10*3+10*10*10*10*4
		//4321=1+10(2+10(3+10*4))
		return (yushu+10*(sumByOrder(shang)));
	}
	public int ShuiXianHua(int current,int num,int finalData){//递归到sum位数（右数sum位，初始sum=final），finalData代表初始数字的位数
		int yushu=(int) (current/(Math.pow(10, num-1)));//最高位数字
		if(num==1){
			return (int) Math.pow(current,finalData);
		}
		return (int)Math.pow(yushu, finalData)+ShuiXianHua((int) (current%(Math.pow(10, num-1))),num-1,finalData);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsShuiXianHua fs=new IsShuiXianHua();
		int sum=fs.sumByOrder(167);
		System.out.println(sum);
		if(153==fs.ShuiXianHua(153, 3, 3)){
			System.out.println("153是水仙花数");
		}
		if(1234==fs.ShuiXianHua(1234, 3, 3)){
			System.out.println("153是水仙花数");
		}
	}
//4321=1+10*2+10*10*3+10*10*10*10*4
//4321=1+10(2+10(3+10*4))
}
