package agency.july.validjsondemo;

import agency.july.validjsondemo.tasks.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

@SpringBootApplication
public class ValidJsonDemoApplication implements CommandLineRunner {

    @Value("${config.services}")
    private String configFileName;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ValidJsonDemoApplication.class, args);
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

//        ServiceUrls.init(new File(configFileName));
//        System.out.println(ServiceUrls.getURL("target_json_rpc"));
//        System.out.println(ServiceUrls.getURL("schema-registry"));

        File tests = new File("src/main/resources/test.json");
        System.out.println(tests.getAbsolutePath());

        Item root = objectMapper.readValue(tests, Item.class);

        System.out.println("Hello, World!");
        System.out.println(root.getName());
        System.out.println(root.getItems()[0].getName());
        System.out.println(root.getItems()[0].getType());

        objectMapper.writeValue(new File("src/main/resources/test_after.json"), root);

        root.doIt();
    }
}
