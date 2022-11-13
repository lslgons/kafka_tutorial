package com.dizzy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class KafkaProducerSync {
    public static int SEND_AMOUNT=100;
    public static void main(String[] args) {

        System.out.println("====>> Start Kafka Producer Tutorial - Producer with ACK");
        Properties props = new Properties();
        props.put("bootstrap.servers", "peter-kafka01.foo.bar:9092,peter-kafka02.foo.bar:9092,peter-kafka03.foo.bar:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        try {
            for(int i=0;i<100; ++i) {
                ProducerRecord<String, String> record = new ProducerRecord<>("peter-basic01", "Hello Kafka - "+ i);
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Topic: %s, Partition: %d, Offset: %d, Key: %s, Received Message: %s\n", metadata.topic(),
                        metadata.partition(), metadata.offset(), record.key(), record.value());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        System.out.println("====>> End Kafka Producer Tutorial - Producer with ACK");
    }
}
