package company.flipkart.machinecoding.loggerlibrary.model.logger;

import company.flipkart.machinecoding.loggerlibrary.model.Message;
import company.flipkart.machinecoding.loggerlibrary.model.sink.Sink;

import java.util.ArrayList;
import java.util.List;

public class SyncLogger extends Logger {
    private List<Sink> sinks;

    public SyncLogger() {
        this.sinks = new ArrayList<>();
    }

    @Override
    public void log(Message m) {
        // Direct to sink(s)
        for (Sink s : sinks) {
            s.logMessage(m);
        }
    }

    @Override
    public List<Sink> getSinks() {
        return sinks;
    }

    public void setSinks(List<Sink> sinks) {
        this.sinks = sinks;
    }
}