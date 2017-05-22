package com.tutorialspoint.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

/**
 * Created by phuongdv on 5/22/17.
 */

public class RecordFilterImpl implements RecordFilterStrategy<String, String> {

    private boolean called;

    @Override
    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
        called = true;
        return consumerRecord.value().contains("Hello");
    }

}