package pm.project.restaurante_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pm.project.restaurante_app.config.FileStorageProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties({FileStorageProperties.class})
public class RestauranteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteAppApplication.class, args);
	}

}
