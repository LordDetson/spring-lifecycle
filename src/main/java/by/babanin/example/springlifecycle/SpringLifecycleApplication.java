package by.babanin.example.springlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringLifecycleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringLifecycleApplication.class, args);
		if(Boolean.parseBoolean(context.getEnvironment().getProperty("triggerStartAndStopContext", "false"))) {
			context.start();
			context.stop();
		}
	}

}
