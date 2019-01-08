package test.algorithm;

public class BinarySearch {

	public static int binarySearch(int[] a,int value){
		int low=0,high=a.length-1;
		int mid=(low+high)/2;
		while(low<=high){
			if(value<a[mid]){
				high=mid-1;
			}
			else if(value>a[mid]){
				low=mid+1;
			}
			else{
				return mid;
			}
			mid=(low+high)/2;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] a=new int[]{1,2,4,7,9,13,24,24,30};
		int index=binarySearch(a, 19);
		if(index>0){
			System.out.println("找到位置："+index);
		}
		else{
			System.out.println("不存在");
		}
	}

}
