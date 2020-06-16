package pwdhasher.events.util;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @author Andreas Werner
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
