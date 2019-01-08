package com.shengsiyuan.study.thread.pool;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 打印0~300的数值
 * @author zhaohe
 *
 */
public class ForkJoinPoolTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		ForkJoinPool pool=new ForkJoinPool();
		//提交可分解的Printtask任务
		pool.submit(new PrintTask(0, 300));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//关闭线程池
		pool.shutdown();
		*/
		int arr[]=new int[100];
		Random rand=new Random();
		int total=0;
		long start=System.currentTimeMillis();
		for(int i=0,len=arr.length;i<len;i++){
			int tmp=rand.nextInt(20);
			arr[i]=tmp;
			total+=arr[i];
		}
		long end=System.currentTimeMillis();
		System.out.println(total+" 用时 "+(end-start));
		//创建通用池
		start=System.currentTimeMillis();
		ForkJoinPool pool=ForkJoinPool.commonPool();
		Future<Integer> future=pool.submit(new CallTask(arr,0,arr.length));
		end=System.currentTimeMillis();
		System.out.println(" 用时 "+(end-start));
		System.out.println(future.get());
		pool.shutdown();
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
class CallTask extends RecursiveTask<Integer>{
	//每个"小任务"最多累加20个数
	private static final int THRESHOLD=20;
	private int arr[];
	private int start;
	private int end;
	public CallTask(int[] arr,int start, int end) {
		super();
		this.arr=arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum=0;
		if(end-start<THRESHOLD){
			for(int i=start;i<end;i++){
				sum+=arr[i];
			}
			return sum;
		}else{
			int middle=(start+end)/2;
			CallTask left=new CallTask(arr,start, middle);
			CallTask right=new CallTask(arr,middle, end);
			//执行2个小任务
			left.fork();
			right.fork();
			return left.join()+right.join();
		}
		
	}
	
}
class PrintTask extends RecursiveAction{
	//每个"小任务"最多只打印50个数
	private static final int THRESHOLD=50;
	private int start;
	private int end;
	public PrintTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if(end-start<THRESHOLD){
			for(int i=start;i<end;i++){
				System.out.println(Thread.currentThread().getName()+"的i值： "+i);
			}
		}else{
			int middle=(start+end)/2;
			PrintTask left=new PrintTask(start, middle);
			PrintTask right=new PrintTask(middle, end);
			//执行2个小任务
			left.fork();
			right.fork();
		}
		
	}
	
}

