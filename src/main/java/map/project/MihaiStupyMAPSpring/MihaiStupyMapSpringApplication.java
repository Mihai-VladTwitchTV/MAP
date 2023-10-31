package map.project.MihaiStupyMAPSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "map.project.MihaiStupyMAPSpring.data.repository")
public class MihaiStupyMapSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MihaiStupyMapSpringApplication.class, args);
	}

}
