package myapp.asynchronous.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class EventHandler {

	private static final int MAX_THREAD_POOL = 5;
	private static List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();

	private static synchronized List<EventListener> getListener() {
		return listeners;
	}

	public static synchronized void addListener(EventListener listener) {
		if (!getListener().stream().anyMatch(r -> r.getClass().getName().equals(listener.getClass().getName()))) {
			listeners.add(listener);
		} else {
			log.warn("{} is already existing", listener.getClass().getName());
		}
	}

	public static synchronized void removeListener(EventListener listener) {
		if (getListener().stream().anyMatch(r -> r.getClass().getName().equals(listener.getClass().getName()))) {
			listeners.remove(listener);
		} else {
			log.warn("{} is not existing", listener);
		}
	}

	public static synchronized void callEvent(final Class<?> caller, String event) {
		callEvent(caller, event, true);

	}

	public static synchronized void callEvent(final Class<?> caller, String event, boolean isAsynch) {
		if (isAsynch) {
			callEventByAsynch(caller, event);
		} else {
			callEventBySynch(caller, event);
		}
	}

	public static synchronized void callEventByAsynch(final Class<?> caller, String event) {
		log.debug("event[{}] occured by {}", event, caller.getName());
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL);

		for (final EventListener listener : listeners) {
			executorService.execute(() -> {
				if (listener.getClass().getName().equals(caller.getName())) {
					// log.debug("event[{}] skipped..by {}", event, caller.getName());
				} else {
					// log.debug("event[{}] catched..by {}", event, listener.getClass().getName());
					listener.onEvent(event);
				}
			});
		}

		executorService.shutdown();
	}

	public static synchronized void callEventBySynch(final Class<?> caller, String event) {
		log.debug("event[{}] occured by {}", event, caller.getName());
		for (final EventListener listener : listeners) {
			if (listener.getClass().getName().equals(caller.getName())) {
				// log.debug("event[{}] skipped..by {}", event, caller.getName());
			} else {
				// log.debug("event[{}] catched..by {}", event, listener.getClass().getName());
				listener.onEvent(event);
			}
		}
	}

}
