package by.babanin.example.springlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

@Component
public class TestLifecycle implements Lifecycle {

    private static final Logger log = LoggerFactory.getLogger(TestLifecycle.class);

    private boolean running;

    @Override
    public void start() {
        LogUtils.infoWithStacktrace(log, "Lifecycle#start method");
        running = true;
    }

    @Override
    public void stop() {
        LogUtils.infoWithStacktrace(log, "Lifecycle#stop method");
        running = false;
    }

    @Override
    public boolean isRunning() {
        LogUtils.infoWithStacktrace(log, "Lifecycle#isRunning method");
        return running;
    }
}
