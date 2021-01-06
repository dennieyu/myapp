package myapp.release;

// switch expressions (preview)
public class Java13 {

	public static void main(String[] args) {
		// switch expressions (preview)
		switchExpression1();
		switchExpression2(Day.SAT);
		switchExpression3(1);
	}

	public static void switchExpression1() {
		int code = 2;

		String result = switch (code) {
			case 1 :
				yield "1번";
			case 2 :
				yield "2번";
			default :
				yield "디폴트";
		};

		System.out.println(result);
	}

	public static void switchExpression2(Day today) {
		String day = switch (today) {
			case MON, TUE, WED, THUR, FRI -> "Weekday";
			case SAT, SUN -> "Weekend";
		};

		System.out.println(day);
	}

	public static void switchExpression3(int i) {
		switch (i) {
			case 0, 1, 2 :
				System.out.println("Hello");
			default :
				System.out.println("World");
		}
	}

	enum Day {
		MON, TUE, WED, THUR, FRI, SAT, SUN
	};

}
