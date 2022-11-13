package com.dizzy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("====> Start Kafka Producer Tutorial");
        KafkaProducerNoACK.main(args);

        System.out.println("====> End Kafka Producer Tutorial");

    }
}