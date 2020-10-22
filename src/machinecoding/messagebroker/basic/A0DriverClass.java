package machinecoding.messagebroker.basic;

// Implement a MessageBroker which accept messages from Publisher and deliver to Subscriber.
// To begin with start with single Publisher and Subscriber.
// But design it in such a way to scale up to many publisher and subscriber associated with a Single Broker.
// Take Performance and parallel processing into consideration.

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


// One producer binds to exchange, exchange has n queues, each queue can have a consumer.
// I'm using three Queues : one for odd, one for even, one for just number (both odd even).
// Each queue has one consumer feeding off it.
public class A0DriverClass {

    public static void main(String[] args) {
        Exchange e = new Exchange();
        Broker messageBroker = new Broker(e);

        Queue odd = new ConcurrentLinkedQueue();
        messageBroker.addQueueInExchange(odd, "odd");

        Queue even = new ConcurrentLinkedQueue();
        messageBroker.addQueueInExchange(even, "even");

        Producer p = new Producer(e);
        Consumer C1 = new Consumer("Odd consumer", e, "odd");
        Consumer C2 = new Consumer("Even consumer", e, "even");
    }
}






