package myapp.concurrent.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class ThreadPoolExecutorApp {

	private final static int MAX_THREAD_POOL = 5;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(MAX_THREAD_POOL);

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAD_POOL);

		log.info("main start ______________________");

		for (int i = 1; i <= MAX_THREAD_POOL; i++) {
			SynchronousTask task = new SynchronousTask("Task " + i, latch);
			executor.execute(task);
		}

		latch.await(10, TimeUnit.SECONDS);
		executor.shutdown();

		log.info("main shutdown ______________________");
	}

}
