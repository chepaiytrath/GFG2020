package machinecoding.loggerlibrary.model.sink;

import machinecoding.loggerlibrary.enums.LogLevel;
import machinecoding.loggerlibrary.model.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutSink extends Sink {
    private LogLevel logLevel;
    private String timeFormat;

    public StdoutSink(LogLevel logLevel, String timeFormat) {
        this.logLevel = logLevel;
        this.timeFormat = timeFormat;
    }

    @Override
    public synchronized void logMessage(Message m) {
        if (m.getLevel().getPriority() >= this.logLevel.getPriority()) {
            // Enrich msg with timestamp
            StringBuffer msg = new StringBuffer();
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(this.timeFormat));
            msg.append(time + " ");
            msg.append("[" + logLevel + "]" + " ");
            msg.append(m.getContent());

            System.out.println(msg);
        }
    }
}
