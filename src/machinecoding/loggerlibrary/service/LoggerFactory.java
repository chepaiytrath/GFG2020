package machinecoding.loggerlibrary.service;

import machinecoding.loggerlibrary.enums.LogLevel;
import machinecoding.loggerlibrary.enums.LoggerType;
import machinecoding.loggerlibrary.enums.SinkType;
import machinecoding.loggerlibrary.model.logger.AsyncLogger;
import machinecoding.loggerlibrary.model.logger.Logger;
import machinecoding.loggerlibrary.model.logger.SyncLogger;
import machinecoding.loggerlibrary.model.sink.Sink;


public class LoggerFactory {
    public static Logger getLogger(LoggerType loggerType, int bufferSize, SinkType sinkType, LogLevel logLevel, String timeFormat) {
        Sink sink = SinkFactory.getSink(sinkType, logLevel, timeFormat);
        Logger logger = null;
        switch (loggerType) {
            case SYNC: {
                logger = new SyncLogger();
                break;
            }
            case ASYNC: {
                logger = new AsyncLogger(bufferSize);
                break;
            }
            default: {
                logger = new SyncLogger();
                break;
            }
        }
        if (logger != null) {
            logger.getSinks().add(sink);
        }
        return logger;
    }
}
