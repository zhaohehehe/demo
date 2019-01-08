package com.shengsiyuan.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class TestThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
public class CallableThreadTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*FutureTask<Integer> task=new FutureTask<>((Callable<Integer>)()->{
			int i=0;
			for( ;i<10;i++){
				System.out.println(Thread.currentThread().getName());
				if(i==2){
					new Thread(TestThread,"").start();
				}
			}
		});*/
		FutureTask<Integer> task=new FutureTask<>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int i=0;
				for(;i<10;i++){
					System.out.println(Thread.currentThread().getName());
				}
				return i;
			}
		});
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName());
			if(i==5){
				new Thread(task,"有返回值的县线程").start();
				//get方法会阻塞main线程
				System.out.println(task.get());
			}
		}
	}
}




























