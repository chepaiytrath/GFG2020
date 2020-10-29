package machinecoding.loggerlibrary.enums;

public enum LogLevel {
    DEBUG(1), INFO(2), WARN(3), ERROR(4), FATAL(5);
    private int priority;

    LogLevel(int p) {
        this.priority = p;
    }

    public int getPriority() {
        return priority;
    }
}
