package test.algorithm;
/**
 * 快速排顺序n*(log2(n))
 * @author zhaohe
 * 2016/09/30
 *
 */
public class ArrayQuickSort {

	public static int partition(int[] a,int low,int high){//low=1
		int pivotkeyValue=a[low];//暂存轴记录的值
		while(low<high){
			while(low<high&&pivotkeyValue<=a[high]){high--;}
			a[low]=a[high];
			while(low<high&&pivotkeyValue>=a[low]){low++;}
			a[high]=a[low];
		}
		a[low]=pivotkeyValue;
		return low;
		
	}
	public static void qSort(int[] a,int low,int high){
		int pivotLoc;
		if(low<high){
			pivotLoc=partition(a, low, high);
			qSort(a,low,pivotLoc);
			qSort(a,pivotLoc+1,high);
		}
	}

	public static void main(String[] args) {
		int[] a=new int[]{2,5,1,-3,6,9,3,6};
		qSort(a,0,a.length-1);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}

}
