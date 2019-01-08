package test.algorithm;

import java.util.Scanner;
/**
 * 统计字母、数字、空格等的个数
 * @author zhaohe
 *2016/09/10
 */
public class ComputeCharacterNum {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入字符串：");
		Scanner in=new Scanner(System.in);
		String str=in.next();
		int HNum=0;
		int ZNum=0;
		int ENum=0;
		String pattern="[\u4e00-\u9fa5]";//Unicode汉字范围
		String pattern2="[a-zA-Z]";
		//char[] arrChar=str.toCharArray();
		for(int i=0;i<str.length();i++){
			if(str.substring(i, i+1).matches(pattern)){
				HNum++;
			}
			else if(str.substring(i, i+1).matches(pattern2)){
				ZNum++;
			}
			else{
				ENum++;
			}
		}
		System.out.println("汉字的个数:"+HNum);
		System.out.println("字母的个数:"+ZNum);
		System.out.println("其他字符的个数:"+ENum);
	}

}
