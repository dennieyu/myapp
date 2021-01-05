package myapp.asynchronous.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class CallerApp {

	public static void main(String[] args) {
		Callee callee = new Callee();
		callee.setCallback(new Callback() {
			@Override
			public void addCount() {
				callee.addCount();
				log.debug("current count={}", callee.getCount());
			}

			@Override
			public void subtractCount() {
				callee.subtractCount();
				log.debug("current count={}", callee.getCount());
			}

			@Override
			public boolean isCompleted() {
				if (callee.getCount() >= 10) {
					log.info("completed!!");
				}
				return callee.getCount() >= 10;
			}
		});

		while (true) {
			if (callee.execute()) {
				break;
			}
		}
	}

}
