package paultr.offers.utility;

import com.squareup.otto.Bus;

public class EventBus extends Bus {
    private static EventBus bus = new EventBus();

    private EventBus() {}

    public static EventBus getInstance() {
        return bus;
    }
}
