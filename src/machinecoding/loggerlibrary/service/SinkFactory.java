package machinecoding.loggerlibrary.service;

import machinecoding.loggerlibrary.enums.LogLevel;
import machinecoding.loggerlibrary.enums.SinkType;
import machinecoding.loggerlibrary.model.sink.Sink;
import machinecoding.loggerlibrary.model.sink.StdoutSink;

public class SinkFactory {
    public static Sink getSink(SinkType sinkType, LogLevel logLevel, String timeFormat) {
        Sink sink = null;
        switch (sinkType) {
            case STDOUT: {
                sink = new StdoutSink(logLevel, timeFormat);
                break;
            }
            default:{
                sink = new StdoutSink(logLevel, timeFormat);
                break;
            }
        }
        return sink;
    }
}
