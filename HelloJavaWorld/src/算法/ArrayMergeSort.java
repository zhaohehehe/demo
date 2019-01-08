package test.algorithm;
/**
 * 归并排序：时间复杂度n*(log2(n))
 * @author zhaohe
 * 2016年10月1日 下午7:26:09
 */
public class ArrayMergeSort {
	//相邻两个有序子序列归并low-mid;mid+1-high
	public static void merge(int[] src,int[] tar,int low,int mid,int high){
		int i=low,j=mid+1;
		int k=low;
		while(i<=mid&&j<=high){
			if(src[i]>src[j]){
				tar[k++]=src[j++];
			}
			else{
				tar[k++]=src[i++];
			}
		}
		while(i<=mid){
			tar[k++]=src[i++];
		}
		while(j<=high){
			tar[k++]=src[j++];
		}
	}
	public static void mergeSort(int[] src,int[] tar,int low,int high){
		if(low==high){
			tar[low]=src[low];
		}
		else if(low>high){return;}
		else{
			int mid=(low+high)/2;
			mergeSort(src,tar,low,mid);
			mergeSort(src,tar,mid+1,high);
			merge(src,tar,low,mid,high);
		}
	}
	public static void main(String[] args) {
		int[] src=new int[]{2,5,6,8,-7,2,4,9};
		int[] tar=new int[src.length];
		//merge(src,tar,0,3,7);
		mergeSort(src,tar,0,src.length-1);
		for(int i=0;i<src.length;i++){
			System.out.println(tar[i]);
		}
	}

}
