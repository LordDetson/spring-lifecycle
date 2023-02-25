package by.babanin.example.springlifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;

public class TestBean implements InitializingBean, DisposableBean, BeanNameAware {

    private static final Logger log = LoggerFactory.getLogger(TestBean.class);

    private BeanToInject bean;

    // Standard JVM Class Initialization Section

    static {
        LogUtils.infoWithStacktrace(log, "Static initialization block");
    }

    {
        LogUtils.infoWithStacktrace(log, "Non-static initialization block");
    }

    public TestBean() {
        LogUtils.infoWithStacktrace(log, "Bean constructor");
    }

    //  Bean Initialization Callbacks Section

    @PostConstruct
    public void annotationInitialization() {
        LogUtils.infoWithStacktrace(log, "@PostConstruct annotated method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.infoWithStacktrace(log, "InitializingBean#afterPropertiesSet() method");
    }

    public void init() {
        LogUtils.infoWithStacktrace(log, "initMethod registered method");
    }

    //  Bean Destruction Callbacks Section

    @PreDestroy
    public void annotationCleanUp() {
        LogUtils.infoWithStacktrace(log, "@PreDestroy annotated method");
    }

    @Override
    public void destroy() throws Exception {
        LogUtils.infoWithStacktrace(log, "TestBean#destroy() method");
    }

    public void cleanUp() {
        LogUtils.infoWithStacktrace(log, "destroyMethod registered method");
    }

    // The standard set of *Aware interfaces

    @Override
    public void setBeanName(String name) {
        LogUtils.infoWithStacktrace(log, "The standard set of *Aware interfaces");
    }

    // Setter-base Dependency Injection

    @Resource
    public void setBean(BeanToInject bean) {
        LogUtils.infoWithStacktrace(log, "Setter-base Dependency Injection");
        this.bean = bean;
    }
}
