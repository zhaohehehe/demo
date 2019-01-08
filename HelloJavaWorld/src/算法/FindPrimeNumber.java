package test.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import test.exception.MyFirstException;

/**
 * 判断101-200之间有多少个素数，并输出所有素数
 * 断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
 * @author zhaohe
 *2016年8月14日 下午11:38:30
 */
public class FindPrimeNumber{
	public List<Integer> SearchPrime(int from,int to) throws Exception{
		List<Integer> list=new ArrayList<Integer>();
		if((to-from)<0){
			throw new MyFirstException("减数大于被减数！");
		}
		try{
			for(int i=0;i<(to-from+1);i++){
				if(isPrime(from+i)){
					list.add(Integer.valueOf(from+i));
				}
			}
		}catch(MyFirstException e){
			e.printStackTrace();
			e.getMessage();
		}
		return list;
	}
	public boolean isPrime(int current) throws Exception{
		if(current<=0){
			throw new MyFirstException("输入数字小于或等于0！");
		}
		if(current==1){
			throw new MyFirstException("1既不是素数也不是合数！");
		}
		for(int i=2;i<=Math.sqrt(current);i++){
			if((current%i)==0){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		FindPrimeNumber fp=new FindPrimeNumber();
		System.out.println("请输入from:\t");
		int from=0;
		int to=0;
		if(in.hasNext()){
			from=in.nextInt();
		}
		System.out.println("请输入to:\t");
		if(in.hasNext()){
			to=in.nextInt();
		}
		System.out.println(from+"到"+to+"之间素数有:");
		try {
			List<Integer> list=fp.SearchPrime(from, to);
			Iterator<Integer> it=list.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.exit(1);
		}

	}
}
