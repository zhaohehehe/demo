package test.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * 随机生成50个随机数，范围是[10,50],统计每个数字出现的次数以及次数最多的数字，并且按升续打印出来
 * @author zhaohe
 * 2016年10月1日 下午11:11:13
 */
public class RandomCharNum {
	public static void randomNumByCollection(int num){
		Random r=new Random(3);
		TreeMap<String,Integer> map=new TreeMap<String,Integer>();
		Set<String> set=map.keySet();
		int random;
		for(int i=0;i<num;i++){
			random=r.nextInt(41)+10;
			System.out.println(random);
			if(map.get(String.valueOf(random))==null){
				map.put(String.valueOf(random), 1);
			}
			else{
				int value=(Integer)(map.get(String.valueOf(random))).intValue();
				map.put(String.valueOf(random), value+1);
			}
		}
		Collection<Integer> values=map.values();
		int max=Collections.max(values).intValue();
		ArrayList<Integer> list=new ArrayList<Integer>();

		for(Iterator<String> it=set.iterator();it.hasNext();){
			String key=it.next();
			int value=map.get(key).intValue();
			if(value==max){
				list.add(Integer.parseInt(key));
			}
			System.out.println("随机数："+key+"出现的次数:"+value);
		}
		System.out.println("出现次数最多的随机数为：");
		for(Iterator<Integer> it=list.iterator();it.hasNext();){
			System.out.println(it.next().intValue());
		}
	}
	public static void randomNumByArray(int number){
		int[] a=new int[number];
		int[] num=new int[number];
		Random rr=new Random(3);
		for(int i=0;i<a.length;i++){
			a[i]=rr.nextInt(41)+10; 
			System.out.println(a[i]);
		}
		System.out.println("--------------------------");
		for(int i=0;i<a.length;i++){
			num[i]=1;
		}
		ArrayBubbleSort.bubbleSort03(a);
		int cur=a[0];
		int max=num[0];
		int j;
		for(int i=1;i<a.length;i++){
			if(a[i]!=a[i-1]){
				continue;
			}
			else{
				num[i]=num[i]+num[i-1];
				num[i-1]=0;
				if(num[i]>max){
					max=num[i];
				}
			}
		}
		for(int k=0;k<a.length;k++){
			if(num[k]!=0){
				System.out.println("随机数："+a[k]+" 出现"+num[k]+"次");
			}
		}
		System.out.println("--------------------------");
		System.out.println("出现次数最多的随机数为：");
		for(int m=0;m<a.length;m++){
			if(num[m]==max){
				System.out.println(a[m]+" ");
			}
		}
	}
	public static void randomNumAdvancedByArray(int number){
		//[10-50]之间共有41个不同数字,[0]->数字10;[40]->数字50
		int[] a=new int[41];
		Random rr=new Random(3);
		int temp;
		for(int i=0;i<number;i++){
			temp=rr.nextInt(41)+10;System.out.println(temp);
			a[temp-10]++; 
		}
		System.out.println("--------------------------");
		for(int i=0;i<a.length;i++){
			if(0==a[i]){ continue;}
			else{
				System.out.println("随机数："+(i+10)+" 出现"+a[i]+"次");
			}
		}
		int max=a[0];
		for(int i=1;i<a.length;i++){
			if(a[i]>max){max=a[i];}
		}
		System.out.println("--------------------------");
		System.out.println("出现次数最多的随机数为：");
		for(int i=1;i<a.length;i++){
			if(a[i]==max){
				System.out.println(i+10);
			}
		}
		
	}
	public static void testRandom(int seed){
		Random rr=new Random(seed);
		for(int i=0;i<5;i++){
			System.out.println(rr.nextInt(20)+" ");
		}
		System.out.println("------------------------");
		double random=Math.random();//[0.0-1.0)
		int result=(int)(random*41);//[0.0-41.0)
		result+=10;
		System.out.println(result);
		System.out.println("------------------------");
	}
	public static void main(String[] args) {
		/*testRandom(10);
		testRandom(10);
		testRandom(9);
		testRandom(9);*/
		randomNumByArray(50);
		System.out.println("------------------------");
		randomNumAdvancedByArray(50);
		System.out.println("------------------------");
		randomNumByCollection(50);
		
	}

}
