package uni.fmi.masters.JobsPazardzhik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class JobsPazardzhikApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsPazardzhikApplication.class, args);
	}

}
