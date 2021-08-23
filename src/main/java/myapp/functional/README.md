함수형 인터페이스
=====


정의
-----

- FunctionalInterface 소스 내용

> Note that instances of functional interfaces can be created with lambda expressions, method references, or constructor references

- 함수형 인터페이스는 람다식이나 메서드 레퍼런스, 생성자 래퍼런스룔 이용하여 인스턴스화 될 수 있다.

- 자바의 람다식은 함수형 인터페이스로만 접근이 된다.

- 여러 개의 디폴트 메서드가 있더라도 추상 메서드가 오직 하나면 함수형 인터페이스이다.


자바에서 기본적으로 제공하는 함수형 인터페이스
-----

| **Functional Interface**  | **Descriptor** | **Method** |
| :---: | ---: | ---: |
| Predicate | T -> boolean | boolean test(T t) |
| Consumer | T -> void | void accept(T t) |
| Supplier | () -> T | T get() |
| Function<T, R> | T -> R | R apply(T t) |
| Comparator | (T, T) -> int | int compare(T o1, T o2) |
| Runnable | () -> void | void run() |
| Callable | () -> T | V call() |


예제 #1 (람다식 이용)
-----

```
@Slf4j
public class FunctionalTest1 {

	public static void main(String[] args) {
		int a = 2;
		int b = 1;

		log.debug("{} + {} = {}", a, b, IntFunc.calc(Math.add, a, b));
		log.debug("{} - {} = {}", a, b, IntFunc.calc(Math.subtract, a, b));

		Runnable r = () -> {
			log.debug("Running!!");
		};
		r.run();
	}

}

@FunctionalInterface
interface IntFunc {
	int apply(Integer a, Integer b);

	static int calc(IntFunc f, Integer a, Integer b) {
		Objects.requireNonNull(a);
		Objects.requireNonNull(b);
		return f.apply(a, b);
	}

}

class Math {

	static IntFunc add = (a, b) -> a + b;
	static IntFunc subtract = (a, b) -> a - b;

}
```


예제 #2 (메서드 레퍼런스 이용)
-----

```
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
```
