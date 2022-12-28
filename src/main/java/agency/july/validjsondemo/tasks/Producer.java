package agency.july.validjsondemo.tasks;

import static agency.july.validjsondemo.enums.Storage.*;
import static agency.july.validjsondemo.enums.ServiceSingleton.BROKER;

import agency.july.validjsondemo.models.ProducerParams;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

@Getter
@Setter
@ToString
public class Producer implements Task {

    private ProducerParams params;

    @Override
    @JsonIgnore
    public void doIt() {

        System.out.println(this);
        System.out.println(this.params.getBootstrap());

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER.getUrlString()); //"localhost:9092"
        props.put(ProducerConfig.CLIENT_ID_CONFIG, this.params.getClient());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
//        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, this.params.getService().getUrlString());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, this.params.getService());
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        KafkaProducer kafkaProducer = new KafkaProducer<>(props);

        STORAGE.put(this.params.getClient() + "_producer", kafkaProducer);
        STORAGE.put(this.params.getClient() + "_schema", this.params.getSchema());
        STORAGE.put(this.params.getClient() + "_topic", this.params.getTopic());

    }
}
