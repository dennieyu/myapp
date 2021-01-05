package myapp.functional;

import java.util.Arrays;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class FunctionalTest2 {

	public static void main(String[] args) {
		Student s1 = new Student("dennieyu", 46, 100, 100);
		Student s2 = new Student("jolia", 12, 100, 100);

		Arrays.asList(s1, s2).forEach(System.out::println);

		Comparator<Student> c = Comparator.comparing(Student::getAge); // double colon operator
		// int diff = c.compare(s2, s1);
		int diff = c.compare(s1, s2);

		log.debug("diff={}", (diff > 0));
	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {

	private String name;
	private int age;
	private int englishScore;
	private int mathScore;

}
