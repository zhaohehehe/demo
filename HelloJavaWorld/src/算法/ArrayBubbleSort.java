package test.algorithm;
/**
 * 冒泡时间复杂度n方
 * @author zhaohe
 *2016/10/01
 */
public class ArrayBubbleSort {
	//bubbleSort01等价于bubbleSort03
	public static void bubbleSort01(int[] a){
		int numCompare=0;
		boolean flag=false;
		while(numCompare<a.length-1&&!flag){
			for(int i=1;i<a.length-numCompare;i++){
				if(a[i]<a[i-1]){
					int temp=0;
					temp=a[i];a[i]=a[i-1];a[i-1]=temp;
					flag=true;
				}
			}
			if(flag){flag=false;}
			numCompare++;
		}
	}
	public static void bubbleSort02(int[] a){
		for(int i=0;i<a.length-1;i++){
			for(int j=0;j<a.length-i-1;j++){
				if(a[j]>a[j+1]){
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}
	public static void bubbleSort03(int[] a){
		for(int i=0;i<a.length-1;i++){
			boolean flag=false;
			for(int j=0;j<a.length-i-1;j++){
				if(a[j]>a[j+1]){
					flag=true;
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			//每趟是否有交换
			if(!flag){
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[] a=new int[]{-5,3,9,0,9,6};
		int[] b=new int[]{-5,3,9,0,9,6};
		int[] c=new int[]{-5,3,9,0,9,6};
		bubbleSort01(a);
		bubbleSort02(b);
		bubbleSort03(c);
		
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("----------------------");
		for(int i=0;i<b.length;i++){
			System.out.println(b[i]);
		}
		System.out.println("----------------------");
		for(int i=0;i<c.length;i++){
			System.out.println(c[i]);
		}
	}

}
