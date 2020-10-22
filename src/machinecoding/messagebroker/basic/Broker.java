package machinecoding.messagebroker.basic;

import java.util.Queue;

public class Broker {
    //ds
    Exchange exchange;

    Broker(Exchange e) {
        this.exchange = e;
    }

    void addQueueInExchange(Queue q, String queueRoutingKey) {
        exchange.addNewQueue(q, queueRoutingKey);
    }
}
