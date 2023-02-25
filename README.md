# Spring Lifecycle Demo
The project demonstrates the lifecycle of the Spring Boot

## User manual

This project has `maven-wrapper` and uses the `spring-boot-maven-plugin`. 

So you should open root directory of the project and run:
```shell
mvnw spring-boot:run
```

And the console outputs a log that clearly shows the Spring lifecycle using the integration tools provided by Spring Framework
```text
b.b.e.s.TestBeanFactoryPostProcessor     : BeanFactoryPostProcessor constructor
b.b.e.s.TestBeanFactoryPostProcessor     : BeanFactoryPostProcessor#postProcessBeanFactory method
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor constructor
b.b.e.s.TestBeanPostProcessor            : @PostConstruct annotated BeanPostProcessor method
b.b.example.springlifecycle.TestBean     : Static initialization block
b.b.example.springlifecycle.TestBean     : Non-static initialization block
b.b.example.springlifecycle.TestBean     : Bean constructor
b.b.example.springlifecycle.TestBean     : Setter-base Dependency Injection
b.b.example.springlifecycle.TestBean     : The standard set of *Aware interfaces
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor#postProcessBeforeInitialization() method - beanName = testBean
b.b.example.springlifecycle.TestBean     : @PostConstruct annotated method
b.b.example.springlifecycle.TestBean     : InitializingBean#afterPropertiesSet() method
b.b.example.springlifecycle.TestBean     : initMethod registered method
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor#postProcessAfterInitialization() method - beanName = testBean
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#start method
b.b.e.s.SpringContextListener            : context refreshed
b.b.e.s.SpringContextListener            : context closed
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#stop method
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#isRunning method
b.b.example.springlifecycle.TestBean     : @PreDestroy annotated method
b.b.example.springlifecycle.TestBean     : TestBean#destroy() method
b.b.example.springlifecycle.TestBean     : destroyMethod registered method
b.b.e.s.TestBeanPostProcessor            : @PreDestroy annotated BeanPostProcessor method
```

By default, the project does not call the `ApplicationContext#start` and `ApplicationContext#stop` methods. 

To see how the lifecycle changes in this case, you can run:
```shell
mvnw spring-boot:run -D spring-boot.run.arguments=--triggerStartAndStopContext=true
```

The console output:
```text
b.b.e.s.TestBeanFactoryPostProcessor     : BeanFactoryPostProcessor constructor
b.b.e.s.TestBeanFactoryPostProcessor     : BeanFactoryPostProcessor#postProcessBeanFactory method
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor constructor
b.b.e.s.TestBeanPostProcessor            : @PostConstruct annotated BeanPostProcessor method
b.b.example.springlifecycle.TestBean     : Static initialization block
b.b.example.springlifecycle.TestBean     : Non-static initialization block
b.b.example.springlifecycle.TestBean     : Bean constructor
b.b.example.springlifecycle.TestBean     : Setter-base Dependency Injection
b.b.example.springlifecycle.TestBean     : The standard set of *Aware interfaces
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor#postProcessBeforeInitialization() method - beanName = testBean
b.b.example.springlifecycle.TestBean     : @PostConstruct annotated method
b.b.example.springlifecycle.TestBean     : InitializingBean#afterPropertiesSet() method
b.b.example.springlifecycle.TestBean     : initMethod registered method
b.b.e.s.TestBeanPostProcessor            : BeanPostProcessor#postProcessAfterInitialization() method - beanName = testBean
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#start method
b.b.e.s.SpringContextListener            : context refreshed
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#isRunning method
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#start method
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.s.SpringContextListener            : context started
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#stop method
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#isRunning method
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#stop method
b.b.e.s.SpringContextListener            : context stopped
b.b.e.s.SpringContextListener            : context closed
b.b.e.s.TestSmartLifecycle               : SmartLifecycle#isRunning method
b.b.e.springlifecycle.TestLifecycle      : Lifecycle#isRunning method
b.b.example.springlifecycle.TestBean     : @PreDestroy annotated method
b.b.example.springlifecycle.TestBean     : TestBean#destroy() method
b.b.example.springlifecycle.TestBean     : destroyMethod registered method
b.b.e.s.TestBeanPostProcessor            : @PreDestroy annotated BeanPostProcessor method
```

If you want to take a closer look at how callback methods are called, then you should run:
```shell
mvnw spring-boot:run -D spring-boot.run.arguments=--logging.level.by.babanin.example.springlifecycle=TRACE
```
The console outputs the log with stacktrace of method call. For example:
```text
b.b.e.s.TestBeanFactoryPostProcessor     : BeanFactoryPostProcessor constructor

by.babanin.example.springlifecycle.TestException: Reached
        at by.babanin.example.springlifecycle.LogUtils.infoWithStacktrace(LogUtils.java:12) ~[classes/:na]
        at by.babanin.example.springlifecycle.TestBeanFactoryPostProcessor.<init>(TestBeanFactoryPostProcessor.java:19) ~[classes/:na]
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[na:na]
        at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499) ~[na:na]
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:480) ~[na:na]
        at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:197) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:87) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1300) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1198) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205) ~[spring-beans-6.0.4.jar:6.0.4]
        at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:199) ~[spring-context-6.0.4.jar:6.0.4]
        at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:745) ~[spring-context-6.0.4.jar:6.0.4]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:565) ~[spring-context-6.0.4.jar:6.0.4]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730) ~[spring-boot-3.0.2.jar:3.0.2]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:432) ~[spring-boot-3.0.2.jar:3.0.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:308) ~[spring-boot-3.0.2.jar:3.0.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1302) ~[spring-boot-3.0.2.jar:3.0.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1291) ~[spring-boot-3.0.2.jar:3.0.2]
        at by.babanin.example.springlifecycle.SpringLifecycleApplication.main(SpringLifecycleApplication.java:11) ~[classes/:na]
```