package test.algorithm;

import java.util.Scanner;
/**
 * 2016年9月17日 下午12:32:25
 * @author zhaohe
 *
 */
public class judgeDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year, month, day;
	       int days = 0;
	       int dayCount = 0;
	       Scanner sc;
	       boolean isTrue = false;
	       do {
	           isTrue = false;
	           System.out.println("题目：输入某年某月某日，判断这一天是这一年的第几天？");
	           System.out.print("请输入年月日，中间以空格隔开：");
	           sc = new Scanner(System.in);
	           year = sc.nextInt();
	           //System.out.print("请输入月：");
	           //sc = new Scanner(System.in);
	           month = sc.nextInt();
	           //System.out.print("请输入日：");
	           //sc = new Scanner(System.in);
	           day = sc.nextInt();
	           // 判断非法输入
	           if (year <= 0 || month <= 0 || month > 12 || day <= 0 || day > 31) {
	              System.out.println("输入错误，请重新输入！");
	              isTrue = true;
	           }
	       } while(isTrue);
	       for (int i = 0; i < month; i++ ) {
	           switch (i) {
	           case 1:
	           case 3:
	           case 5:
	           case 7:
	           case 8:
	           case 10:
	           case 12:
	              days = 31;
	              break;
	           case 4:
	           case 6:
	           case 9:
	           case 11:
	              days = 30;
	              break;
	           case 2:
	              if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
	                  days = 29;
	              } else{
	                  days = 28;
	              }
	              break;
	           }
	           dayCount += days;
	       }
	       System.out.println(year + "年" + month + "月" + day + "日是这一年的第"
	               + (dayCount + day) + "天。");
	}

}
