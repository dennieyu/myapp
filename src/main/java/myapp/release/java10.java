package myapp.release;

import java.util.ArrayList;

// var keyword
public class java10 {

	public static void main(String[] args) {
		var list = new ArrayList<Student>();

		list.add(new Student("dennieyu", 46));
		list.add(new Student("jolia", 12));

		for (var s : list) {
			System.out.println(s);
		}
	}

}

/* @formatter:off */
@SuppressWarnings("preview")
record Student(String name, Integer age) {}
/* @formatter:on */
