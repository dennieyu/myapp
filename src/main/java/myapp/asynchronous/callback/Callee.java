package myapp.asynchronous.callback;

import java.util.Scanner;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
@Data
public class Callee {

	private int count;
	private Callback callback;

	public Callee() {
		this.count = 0;
	}

	public void addCount() {
		this.count++;
	}

	public void subtractCount() {
		this.count--;
	}

	@SuppressWarnings("resource")
	public boolean execute() {
		Scanner scanner = new Scanner(System.in);
		if (count == 0) {
			log.info("input 1 10 times to complete!!");
		}

		int val = scanner.nextInt();
		switch (val) {
			case 1 :
				this.callback.addCount();
				break;
			case 2 :
				this.callback.subtractCount();
				break;
		}

		return this.callback.isCompleted();
	}

}
