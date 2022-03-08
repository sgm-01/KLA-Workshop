package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.solution.ReadYaml;
import project.solution.WorkFlow;

@SpringBootApplication
public class ProjectApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		//System.out.println("Hello World!");
		new ReadYaml().readYaml();
		LOGGER.info("<task_name> <function>");

	}

}
