package map.project.MihaiStupyMAPSpring.debugLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

@Component
public class BeanDebugger implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Bean: " + beanName);
        }
    }
}

