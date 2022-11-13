package com.dizzy;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerAuto {
    public static void main(String[] args) {

        System.out.println("====>> Start Kafka Consumer Tutorial - Consumer");
        Properties props = new Properties();
        props.put("bootstrap.servers", "peter-kafka01.foo.bar:9092,peter-kafka02.foo.bar:9092,peter-kafka03.foo.bar:9092");
        props.put("group.id", "peter-consumer01"); // 컨슈머가 속한 컨슈머 그룹을 식별, 동일한 그룹내 컨슈머 정보는 모두 공유
        props.put("enable.auto.commit", "true"); // 백그라운드로 주기적으로 오프셋 커밋
        props.put("auto.offset.reset", "latest"); // 초기 오프셋이 없거나 현재 오프셋이 더이상 존재하지 않을경우 reset설정, lastest는 가장 마지막 오프셋으로 설정
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("peter-basic01"));

        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(1000); //1초동안 폴링유지, 해당시간만큼 블록
                for(ConsumerRecord<String, String> record : records) {
                    System.out.printf("Topic: %s, Partition: %s, Offset: %d, Key: %s, Value: %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());

                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
        System.out.println("====>> End Kafka Consumer Tutorial - Consumer");
        
    }
}
