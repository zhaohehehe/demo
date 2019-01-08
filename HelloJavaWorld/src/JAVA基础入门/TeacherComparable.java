package JAVA基础入门;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeacherComparable {
	public static void main(String[] args) {
		Teacher o1=new Teacher("3");
		Teacher o2=new Teacher("2");
		Teacher o3=new Teacher("1");
		List<Teacher> a=new ArrayList<Teacher>();
		a.add(o1);
		a.add(o2);
		a.add(o3);
		Teacher[] staff=new Teacher[3];
		staff[0]=o1;
		staff[1]=o2;
		staff[2]=o3;
		//方法一：
		/*Arrays.sort(staff);
		for(Teacher s:staff){
			System.out.println(s.id);
			}
		}*/
		//方法二：
		Collections.sort(a);
		for(Teacher s:a){
			System.out.println(s.id);
			}
		}
}
