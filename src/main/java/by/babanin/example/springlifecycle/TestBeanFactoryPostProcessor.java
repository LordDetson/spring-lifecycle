package by.babanin.example.springlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(TestBeanFactoryPostProcessor.class);

    public TestBeanFactoryPostProcessor() {
        LogUtils.infoWithStacktrace(log, "BeanFactoryPostProcessor constructor");
    }

    // Never called
    @PostConstruct
    public void init() {
        LogUtils.infoWithStacktrace(log, "@PostConstruct annotated BeanFactoryPostProcessor method");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LogUtils.infoWithStacktrace(log, "BeanFactoryPostProcessor#postProcessBeanFactory method");
    }

    // Never called
    @PreDestroy
    public void destroy() {
        LogUtils.infoWithStacktrace(log, "@PreDestroy annotated BeanFactoryPostProcessor method");
    }
}
