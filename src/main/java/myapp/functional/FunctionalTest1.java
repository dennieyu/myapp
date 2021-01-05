package myapp.functional;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
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
