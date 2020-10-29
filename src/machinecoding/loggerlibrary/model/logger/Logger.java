package machinecoding.loggerlibrary.model.logger;

import machinecoding.loggerlibrary.model.Message;
import machinecoding.loggerlibrary.model.sink.Sink;

import java.util.List;

public abstract class Logger {
    public abstract void log(Message msg);

    public abstract List<Sink> getSinks();
}
