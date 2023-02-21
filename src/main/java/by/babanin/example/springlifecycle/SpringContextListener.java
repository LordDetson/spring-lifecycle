package by.babanin.example.springlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener {

    private static final Logger log = LoggerFactory.getLogger(SpringContextListener.class);

    @EventListener
    public void handleContextRefreshedEvent(ContextRefreshedEvent event) {
        LogUtils.infoWithStacktrace(log, "context refreshed");
    }

    @EventListener
    public void handleContextStartedEvent(ContextStartedEvent event) {
        LogUtils.infoWithStacktrace(log, "context started");
    }

    @EventListener
    public void handleContextStoppedEvent(ContextStoppedEvent event) {
        LogUtils.infoWithStacktrace(log, "context stopped");
    }

    @EventListener
    public void handleContextClosedEvent(ContextClosedEvent event) {
        LogUtils.infoWithStacktrace(log, "context closed");
    }
}
