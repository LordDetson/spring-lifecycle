package by.babanin.example.springlifecycle;

import org.slf4j.Logger;

public final class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("It's util class");
    }

    public static void infoWithStacktrace(Logger log, String msg) {
        log.info(msg, log.isTraceEnabled() ? new TestException() : null);
    }
}
