package machinecoding.messagebroker.basic;

//producer job
public class Producer implements Runnable {
    //ds
    Exchange exchange;
    Thread producerThread;

    Producer(Exchange e) {
        exchange = e;
        producerThread = (new Thread(this));
        producerThread.start();
    }

    @Override
    public void run() {
        //hasSent = true;
        int i = 0;

        while (i < 100) {
            if (i % 2 == 0)
                exchange.addAndRouteQueueMessage(i, "even");
            else
                exchange.addAndRouteQueueMessage(i, "odd");
            System.out.println("prod .. " + i);
            i++;
        }
        System.out.println("prod done ");
    }
}