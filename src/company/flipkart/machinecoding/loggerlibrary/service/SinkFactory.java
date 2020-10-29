package company.flipkart.machinecoding.loggerlibrary.service;

import company.flipkart.machinecoding.loggerlibrary.enums.LogLevel;
import company.flipkart.machinecoding.loggerlibrary.enums.SinkType;
import company.flipkart.machinecoding.loggerlibrary.model.sink.Sink;
import company.flipkart.machinecoding.loggerlibrary.model.sink.StdoutSink;

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
