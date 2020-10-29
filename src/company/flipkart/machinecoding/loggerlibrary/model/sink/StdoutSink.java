package company.flipkart.machinecoding.loggerlibrary.model.sink;

import company.flipkart.machinecoding.loggerlibrary.enums.LogLevel;
import company.flipkart.machinecoding.loggerlibrary.model.Message;

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
