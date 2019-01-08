package JAVAª˘¥°»Î√≈;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class StudentComparator implements Comparator<Student> {
	public int compare(Student o1, Student o2) {
		int v1 = Integer.valueOf(o1.id);
		int v2 = Integer.valueOf(o2.id);
		return v1 > v2 ? 1 : (v1 == v2 ? 0 : -1);
	}

	public static void main(String[] args) {
		Student o1 = new Student();
		o1.id = "1";
		Student o2 = new Student();
		o2.id = "2";
		Student o3 = new Student();
		o3.id = "3";
		List<Student> a = new ArrayList<Student>();
		a.add(o1);
		a.add(o2);
		a.add(o3);
		StudentComparator c = new StudentComparator();
		Collections.sort(a, c);
		for (Student s : a) {
			System.out.println(s.id);
		}
	}
}