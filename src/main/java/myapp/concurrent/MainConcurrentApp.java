package myapp.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class MainConcurrentApp {

	private final ExecutorService executor;
	private final ForkJoinPool fjp;

	private final static int MAX_THREAD_POOL = 5;

	public MainConcurrentApp() {
		executor = Executors.newFixedThreadPool(MAX_THREAD_POOL);
		fjp = new ForkJoinPool(MAX_THREAD_POOL);
	}

	public void workByExecutor(List<Task> taskList) {
		DurationMonitor.clear();
		StopWatch sw = new StopWatch();
		sw.start();

		List<Future<String>> futures = new ArrayList<>();
		try {
			taskList.forEach(t -> {
				futures.add(executor.submit(() -> {
					t.run();
					return null;
				}));
			});
			for (Future<String> f : futures) {
				f.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage(), e);
		}

		sw.stop();
		log.info("executionTime={}ms, total={}s", sw.getTotalTimeMillis(), DurationMonitor.duration);
	}

	public void workByForkJoinPool(List<Task> taskList) {
		DurationMonitor.clear();
		StopWatch sw = new StopWatch();

		sw.start();

		try {
			fjp.submit(() -> {
				taskList.parallelStream().forEach(task -> {
					task.run();
					// log.info("fjp >> {}", fjp.toString()); // indications of run state, parallelism level, and worker and task counts
				});
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage(), e);
		}

		sw.stop();

		log.info("executionTime={}ms, total={}s", sw.getTotalTimeMillis(), DurationMonitor.duration);
		// log.info("fjp >> {}", fjp.toString()); // indications of run state, parallelism level, and worker and task counts
	}

	public static void main(String[] args) throws InterruptedException {
		log.info("main start ______________________");

		MainConcurrentApp app = new MainConcurrentApp();

		List<Task> taskList = new ArrayList<Task>();
		for (int i = 1; i <= 5; i++) {
			Task task = new Task("Task " + i);
			taskList.add(task);
		}

		// executor
		app.workByExecutor(taskList);
		log.info("after workByExecutor ______________________");

		// fjp
		app.workByForkJoinPool(taskList);
		log.info("after workByForkJoinPool ______________________");

		app.executor.shutdown();
		app.fjp.shutdown();

		log.info("main shutdown ______________________");
	}

}

@Deprecated
class MyThreadsFactory implements ThreadFactory {

	private int threadCount = 0;
	private String poolName = "MyPool";

	MyThreadsFactory(String poolName) {
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, poolName + "-Thread" + ++threadCount);
	}

}
