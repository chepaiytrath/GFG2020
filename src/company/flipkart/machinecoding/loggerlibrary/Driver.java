package company.flipkart.machinecoding.loggerlibrary;

import company.flipkart.machinecoding.loggerlibrary.enums.LoggerType;
import company.flipkart.machinecoding.loggerlibrary.enums.SinkType;
import company.flipkart.machinecoding.loggerlibrary.model.Message;
import company.flipkart.machinecoding.loggerlibrary.model.logger.Logger;
import company.flipkart.machinecoding.loggerlibrary.service.LoggerFactory;

import static company.flipkart.machinecoding.loggerlibrary.enums.LogLevel.*;

public class Driver {
    public static void main(String[] args) {
        LoggerType loggerType = LoggerType.SYNC;
        int bufferSize = 0;
        SinkType sinkType = SinkType.STDOUT;
        String timeFormat = "dd-MM-yyyy-hh-mm-ss";

        Logger logger = LoggerFactory.getLogger(loggerType, bufferSize, sinkType, INFO, timeFormat);
        String content = "This is a sample log message1";
        Message msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message2";
        msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message3";
        msg = new Message(content, DEBUG);
        logger.log(msg);

        content = "This is a sample log message4";
        msg = new Message(content, FATAL);
        logger.log(msg);

        content = "This is a sample log message5";
        msg = new Message(content, ERROR);
        logger.log(msg);

        content = "This is a sample log message6";
        msg = new Message(content, WARN);
        logger.log(msg);




        /*LoggerType loggerType = LoggerType.ASYNC;
        int bufferSize = 2;
        SinkType sinkType = SinkType.STDOUT;
        String timeFormat = "dd-MM-yyyy-hh-mm-ss";

        Logger logger = LoggerFactory.getLogger(loggerType, bufferSize, sinkType, INFO, timeFormat);
        String content = "This is a sample log message1";
        Message msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message2";
        msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message3";
        msg = new Message(content, DEBUG);
        logger.log(msg);

        content = "This is a sample log message4";
        msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message5";
        msg = new Message(content, INFO);
        logger.log(msg);

        content = "This is a sample log message6";
        msg = new Message(content, INFO);
        logger.log(msg);*/
    }
}