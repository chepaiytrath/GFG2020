package machinecoding.loggerlibrary.model;

import machinecoding.loggerlibrary.enums.LogLevel;

public class Message {
    private String content;
    private LogLevel level;

    public Message(String content, LogLevel level) {
        this.content = content;
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", level=" + level +
                '}';
    }
}
