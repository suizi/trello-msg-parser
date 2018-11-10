package at.suizi.cv.trellomailparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TrelloMailParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloMailParserApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setConnectTimeout(2000);
		return builder.build();
	}
}
