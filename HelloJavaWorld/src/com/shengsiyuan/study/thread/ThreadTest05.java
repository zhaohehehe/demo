package com.shengsiyuan.study.thread;
/**
 * 有顺序的执行0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9
 * @author zhaohe
 *
 */
public class ThreadTest05 {

	public static void main(String[] args) {
		Content2 content=new Content2();
		
		Thread bt1=new BodyThread3(content);
		content=new Content2();
		Thread bt2=new BodyThread4(content);
		bt1.start();
		bt2.start();
		
	}

}
class Content2{
	private Object object=new Object();
	//private Object object2=new Object();
	//给Content加锁，因为只有一个对象，所以bt2先不执行
	public  void execute1(){
		synchronized (this) {//当前对象
			for(int i=0;i<10;i++){
				try {
					Thread.sleep((long) (Math.random()*500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("hello:"+i);
			}
		}
	}
	//给Content加锁，因为只有一个对象，所以bt1先不执行
	public void execute2(){
		synchronized (this) {
			for(int i=0;i<10;i++){
				try {
					Thread.sleep((long) (Math.random()*500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("world:"+i);
			}
		}
	}
}
class BodyThread3 extends Thread{
	private Content2 em;
	public BodyThread3(Content2 em) {
		this.em=em;
	}
	@Override
	public void run() {
		this.em.execute1();
	}
}
class BodyThread4 extends Thread{
	private Content2 em;
	public BodyThread4(Content2 em) {
		this.em=em;
	}
	@Override
	public void run() {
		this.em.execute2();
	}
}