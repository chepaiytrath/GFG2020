package machinecoding.loggerlibrary.model.sink;

import machinecoding.loggerlibrary.model.Message;

public abstract class Sink {
    public abstract void logMessage(Message msg);
}
