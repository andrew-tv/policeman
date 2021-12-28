package agency.july.validjsondemo;

import agency.july.validjsondemo.models.Items;
import agency.july.validjsondemo.models.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class ValidJsonDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ValidJsonDemoApplication.class, args);
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        File tests = new File("src/main/resources/test.json");
        System.out.println(tests.getAbsolutePath());

        Items root = objectMapper.readValue(tests, Items.class);

        System.out.println("Hello, World!");
        System.out.println(root.getName());
        System.out.println(root.getItems()[0].getName());
        System.out.println(root.getItems()[0].getType());

        objectMapper.writeValue(new File("src/main/resources/test_after.json"), root);


    }
}
