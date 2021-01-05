package myapp.asynchronous.event;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class ListenerA implements EventListener {

	public ListenerA() {
		EventHandler.addListener(this);
	}

	@Override
	public void onEvent(String event) {
		if (event.equals("EVENT_A")) {
			log.debug("event[EVENT_A] done!! ______________________");
		}
	}

}
