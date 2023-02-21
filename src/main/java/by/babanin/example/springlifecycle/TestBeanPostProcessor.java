package by.babanin.example.springlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(TestBeanPostProcessor.class);

    @Value("${showTestBeanOnly:true}")
    private boolean showTestBeanOnly;

    public TestBeanPostProcessor() {
        LogUtils.infoWithStacktrace(log, "BeanPostProcessor constructor");
    }

    @PostConstruct
    public void init() {
        LogUtils.infoWithStacktrace(log, "@PostConstruct annotated BeanPostProcessor method");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(shouldShowBean(beanName)) {
            LogUtils.infoWithStacktrace(log, "BeanPostProcessor#postProcessBeforeInitialization() method - beanName = " + beanName);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(shouldShowBean(beanName)) {
            LogUtils.infoWithStacktrace(log, "BeanPostProcessor#postProcessAfterInitialization() method - beanName = " + beanName);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @PreDestroy
    public void destroy() {
        LogUtils.infoWithStacktrace(log, "@PreDestroy annotated BeanPostProcessor method");
    }

    private boolean shouldShowBean(String beanName) {
        return !showTestBeanOnly || SpringLifecycleConfiguration.isTestBean(beanName);
    }
}
