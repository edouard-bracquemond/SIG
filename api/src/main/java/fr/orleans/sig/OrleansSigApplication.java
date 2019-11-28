package fr.orleans.sig;

import fr.orleans.sig.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		OrleansSigApplication.class,
		Jsr310JpaConverters.class
})

@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class OrleansSigApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
		System.out.println("Spring boot application running in UTC+2 timezone :"+new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(OrleansSigApplication.class, args);
	}
}
