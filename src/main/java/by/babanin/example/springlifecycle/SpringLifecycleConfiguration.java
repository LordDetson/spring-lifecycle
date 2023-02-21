package by.babanin.example.springlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringLifecycleConfiguration {

    public static final String TEST_BEAN_NAME = "testBean";

    @Bean(name = TEST_BEAN_NAME, initMethod = "init", destroyMethod = "cleanUp")
    TestBean testBean() {
        return new TestBean();
    }

    public static boolean isTestBean(String beanName) {
        return beanName.equals(TEST_BEAN_NAME);
    }
}
