package company.flipkart.machinecoding.loggerlibrary.model.logger;

import company.flipkart.machinecoding.loggerlibrary.model.Message;
import company.flipkart.machinecoding.loggerlibrary.model.sink.Sink;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncLogger extends Logger {
    private LinkedBlockingQueue<Message> bucket = new LinkedBlockingQueue<>();
    private List<Sink> sinks;
    private int bufferSize;

    public AsyncLogger(int bufferSize) {
        this.sinks = new ArrayList<>();
        this.bufferSize = bufferSize;
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.submit(new Worker(bucket));
        }
    }

    @Override
    public void log(Message msg) {
        if (bucket.size() < bufferSize) {
            bucket.add(msg);
        }
    }

    class Worker implements Runnable {
        private LinkedBlockingQueue<Message> bucket;

        public Worker(LinkedBlockingQueue bucket) {
            this.bucket = bucket;
        }

        @Override
        public void run() {
            while(true){
                if(bucket.size() > 0){
                    Message m = bucket.poll();
                    for (Sink s : sinks) {
                        s.logMessage(m);
                    }
                }
            }
        }
    }

    public List<Sink> getSinks() {
        return sinks;
    }

    public void setSinks(List<Sink> sinks) {
        this.sinks = sinks;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
