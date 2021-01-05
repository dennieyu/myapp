package myapp.asynchronous.event;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class ListenerB implements EventListener {

	public ListenerB() {
		EventHandler.addListener(this);
	}

	@Override
	public void onEvent(String event) {
		if (event.equals("EVENT_B")) {
			log.debug("event[EVENT_B] done!! ______________________");
		}
	}

}
