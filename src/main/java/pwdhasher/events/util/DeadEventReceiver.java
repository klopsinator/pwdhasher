package pwdhasher.events.util;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author Andreas Werner
 *
 */
@Component
public class DeadEventReceiver {

	/**
	 * @param event
	 */
	@Subscribe
	public void receiveDeadEvent(DeadEvent event) {
		System.out.println(event);
	}
}
