package myapp.concurrent.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class SynchronousTask implements Runnable {

	private String name;
	private CountDownLatch latch;

	public SynchronousTask(String s, final CountDownLatch latch) {
		name = s;
		this.latch = latch;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			Long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
			latch.countDown();
			log.info("executing={}, duration={}s", name, duration);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

}
