package machinecoding.messagebroker.basic;

public class Consumer implements Runnable {
    //ds
    Exchange exchange;
    String consumerName;
    String routingQueue;
    Thread consumerThread;

    Consumer(String consumerName, Exchange e, String rq) {
        this.consumerName = consumerName;
        exchange = e;
        routingQueue = rq;

        consumerThread = (new Thread(this));
        consumerThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object queueMessage = exchange.consumeMessage(routingQueue);
            if (queueMessage != null)
                System.out.println(consumerName + " reading " + queueMessage);
        }
    }
}