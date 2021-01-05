package myapp.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class Task implements Runnable {

	private String name;

	public Task(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			Long duration = (long) (Math.random() * 10);
			log.info("executing={}, duration={}s", name, duration);
			TimeUnit.SECONDS.sleep(duration);
			DurationMonitor.reset(duration);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

}

class DurationMonitor {

	static long duration;

	private static final ReentrantLock lock = new ReentrantLock();

	public static void reset(long d) {
		lock.lock();
		try {
			duration = duration + d;
		} finally {
			lock.unlock();
		}
	}

	public static void clear() {
		lock.lock();
		try {
			duration = 0;
		} finally {
			lock.unlock();
		}
	}

}
