package myapp.asynchronous.event;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
@Slf4j
public class MainEventApp {

	public static void main(String[] args) {
		new ListenerA();
		// new ListenerA();
		new ListenerB();
		// new ListenerB();

		EventHandler.callEvent(MainEventApp.class, "EVENT_A", false); // 동기
		log.debug("after call event[EVENT_A]");
		EventHandler.callEvent(MainEventApp.class, "EVENT_B", true);  // 비동기
		log.debug("after call event[EVENT_B]");
	}

}
