package micro_city3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication
public class MicroCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCityApplication.class, args);
    }

}
