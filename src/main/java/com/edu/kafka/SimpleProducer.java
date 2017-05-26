package com.edu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by phuongdv on 5/22/17.
 */
public class SimpleProducer {


    final static String TOPIC = "TextLinesTopic";
    public static Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.3.34.147:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put("bootstrap.servers", "10.3.34.147:9092");
//        props.put("acks", "all");
//        props.put("retries", "0");
//        props.put("linger.ms", "5");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }


    //send message to TOPIC: foo
    public static void main(String[] args) throws Exception {

        Producer<String,String> producer = new KafkaProducer<String, String>(producerConfigs());
        // send message to topic
        for (int i = 0; i < 1000; i++) {
            System.out.println("push message #" +i);
            String value = "message demo #" + i;
            producer.send(new ProducerRecord<String,String>(TOPIC,"",value));
            Thread.sleep(300);
        }

    }



}
