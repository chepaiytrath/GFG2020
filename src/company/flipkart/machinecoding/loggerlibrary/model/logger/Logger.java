package company.flipkart.machinecoding.loggerlibrary.model.logger;

import company.flipkart.machinecoding.loggerlibrary.model.Message;
import company.flipkart.machinecoding.loggerlibrary.model.sink.Sink;

import java.util.List;

public abstract class Logger {
    public abstract void log(Message msg);

    public abstract List<Sink> getSinks();
}
