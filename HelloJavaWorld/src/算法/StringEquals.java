package test.algorithm;
/**
 * String是定长字符串/常量，每次都需要重新创建对象；
 * StringBuilder是可变长度的字符串，字符串的‘+’操作一般都会通过StringBuilder执行。
 * @author zhaohe
 *
 */
/**
 * 在判断字符串相等时，如果双方都是变量，用equals()函数；若不是，则也可以用"=="
 * 使用equals()时，双方不能有null的情况，否则会出现空指针错误
 * @author zhaohe
 * 2016年8月17日 上午10:46:06
 *
 */
public class StringEquals {
	public void Test(){
		String pwdPattern="123";
		String pwdPattern2="123";
		String pwdlist=new String("12");//引用类型
		String pwdlist1="12";//常量
		final String pwdlist2="3";//常量
		String pwdlist3=pwdlist+"3";//调用StringBuilder
		String pwdlist4=pwdlist1+"3";//调用StringBuilder
		String pwdlist5=pwdlist+pwdlist1;
		String pwdlist6=pwdlist+pwdlist2;
		/*只有形如final+"string"时，不需要调用StringBuilder*/
		String pwdlist7="12"+pwdlist2;//不需要调用StringBuilder
		String pwdlist8=pwdlist1+pwdlist2;//调用StringBuilder
		
		System.out.println(pwdPattern==pwdPattern2);//其实是一个对象
		System.out.println(pwdlist3==pwdPattern);//f
		System.out.println(pwdlist4==pwdPattern);//f?
		System.out.println(pwdlist5==pwdPattern);//f
		System.out.println(pwdlist6==pwdPattern);//f
		System.out.println(pwdlist7==pwdPattern);//T
		System.out.println(pwdlist8==pwdPattern);//f
		
		System.out.println(pwdlist3==pwdlist4);//f
		System.out.println(pwdlist3.equals(pwdlist4));//T
		System.out.println(pwdlist7==pwdlist4);//f
		System.out.println(pwdlist2=="3");//T
		System.out.println(pwdlist2==new String("3"));//f
		System.out.println(pwdlist1=="12");//T
		System.out.println(pwdlist=="12");//f
		System.out.println(pwdlist.equals("12"));//T
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringEquals rp=new StringEquals();
		rp.Test();
	}
}
