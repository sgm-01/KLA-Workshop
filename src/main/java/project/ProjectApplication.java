package project;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import project.solution.Solution;

@Configuration
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		new Solution("C:\\Users\\gopika\\Desktop\\KLA\\DataSet\\Milestone2", "Milestone2B.yaml", "M2B_Workflow").solve();
	}

}
