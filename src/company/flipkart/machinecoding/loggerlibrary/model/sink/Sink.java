package company.flipkart.machinecoding.loggerlibrary.model.sink;

import company.flipkart.machinecoding.loggerlibrary.model.Message;

public abstract class Sink {
    public abstract void logMessage(Message msg);
}
