package test.algorithm;
/**
 * 时间复杂度是所取得“增量”序列的函数，目前没有很好地增量：当n为正无穷时，时间复杂度为n*pow(log2(n),2)
 * @author zhaohe 
 * 2016/10/01
 *
 */
public class ArrayShellSort {
	//直接插入排序InsertSort01等价于InsertSort02
	public static void InsertSort01(int[] a){
		int i,j,temp;
		for(i=1;i<a.length;i++){
			if(a[i]<a[i-1]){
				temp=a[i];//暂存当前a[i]
				a[i]=a[i-1];
				j=i-2;
				while(a[i]<a[j]&&j>=0){
					//移动记录
					a[j+1]=a[j];
					j--;
				}
				a[j]=temp;
				//j<0或者a[i]<a[j]跳出循环
			}
		}
	}
	public static void InsertSort02(int a[],int n)
	{
		int i,j;
		int temp;
		for(i=1;i<n;i++)
		{
			temp=a[i];
			for(j=i-1;j>=0;j--)
			{
				if(temp<a[j])
					a[j+1]=a[j];
				else
					break;
			}
			a[j+1]=temp;
		}
	}
	//希尔插入排序，在直接插入排序的基础上
	public static void shellInsert(int[] a,int dk){
		int temp;
		//从a[dk]开始，依次和他之前dk位置的值进行插入排序
		for(int i=dk;i<a.length;i++){
			if(a[i]<a[i-dk]){
				temp=a[i];
				int j;
				for(j=i-dk;j>=0&&a[j]>temp;j-=dk){
		//j>=0&&a[j]>temp顺序不能颠倒！！如果顺序颠倒，会出现数组越界的情况；
		//因为&&逻辑运算，如果左边不成立就不会执行右边；就不会在j<0的情况导致a[j]越界
					a[j+dk]=a[j];
				}
				a[j+dk]=temp;
			}
		}
		
		/*0,1,2,3,4,5,6,7;
		 *dk=4;
		 *  0-4
		 */	
	} 
	public static void shellSort(int[] a,int[] dk){
		//dk[] 为增量序列,dk.length趟直接插入排序
		for(int i=0;i<dk.length;i++){
			shellInsert(a, dk[i]);
		}
	}
	public static void main(String[] args) {
		int[] a=new int[]{-5,3,9,0,9,6};
		int[] b=new int[]{-5,3,9,0,9,6};
		int[] c=new int[]{-5,3,9,0,9,6};
		int[] dk=new int[]{3,1};//n/2
		shellSort(a, dk);
		InsertSort01(b);
		InsertSort02(a,a.length);
		
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		for(int i=0;i<b.length;i++){
			System.out.println(a[i]);
		}
		for(int i=0;i<c.length;i++){
			System.out.println(a[i]);
		}
	}

}
