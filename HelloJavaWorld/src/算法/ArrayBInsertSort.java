package test.algorithm;
/**
 * ’€∞Î≤Â»Î≈≈–Ú£∫ ±º‰∏¥‘”∂»pow(n,2);
 * @author zhaohe
 *2016/10/01
 */
public class ArrayBInsertSort {
	public static void binaryInsertionSort(int[] a){
		int i,j;
		int temp,left,right,mid;
		for(i=1;i<a.length;i++){
			temp=a[i];
			left=0;right=i-1;
			while(left<=right){
				mid=(left+right)/2;
				if(a[mid]<temp){
					left=mid+1;
				}
				else{
					right=mid-1;
				}
			}
			for(j=i-1;j>=left;j--){
				a[j+1]=a[j];
			}
			a[left]=temp;
		} 
	}
	public static void main(String[] args) {
		int[] a=new int[]{-5,3,9,0,9,6};
		binaryInsertionSort(a);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}
}
