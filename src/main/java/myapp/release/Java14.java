package myapp.release;

import myapp.release.Java13.Day;

// switch expressions (standard)
// text block (standard)
// pattern matching for instanceof (preview)
// record class (preview)
public class Java14 {

	public static void main(String[] args) {
		// switch expressions (standard)
		Java13.switchExpression1();
		Java13.switchExpression2(Day.SAT);
		Java13.switchExpression3(1);

		// text block (standard)
		textBlock();

		// pattern matching for instanceof (preview)
		instanceOfPatternMatching();

		// record class (preview)
		records();

		// helpful NullPointerExceptions
		// -XX:+ShowCodeDetailsInExceptionMessages VM 옶션을 추가해 줘야함

		// remove the Concurrent Mark Sweep (CMS) Garbage Collector
		// G1GC 또는 ZCG를 사용해야함
	}

	public static void textBlock() {
		String tb = """
				Hello
				World
				""";

		System.out.println(tb);
	}

	@SuppressWarnings("preview")
	public static void instanceOfPatternMatching() {
		Object object = "my message";
		if (object instanceof String message) {
			System.out.println(message);
		}
	}

	public static void records() {
		Point point = new Point(1, 2);
		System.out.println(String.format("(%d,%d)", point.x, point.y));
	}

	/* @formatter:off */
	@SuppressWarnings("preview")
	record Point(int x, int y) {}
	/* @formatter:on */

	@SuppressWarnings("preview")
	record Person(String name, int age) {
		public Person {
			if (age < 0) {
				throw new IllegalArgumentException("Age cannot be negative");
			}
		}
	}

}
