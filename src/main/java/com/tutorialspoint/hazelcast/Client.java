package com.tutorialspoint.hazelcast;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;

import java.io.InputStream;
import java.util.Random;

/**
 * https://github.com/kobalski/zookeeper-sample
 */
public class Client {
    public static void main(String[] args) {
        String xmlFileName = "hazelcast-client.xml";
        InputStream xmlResource = Client.class.getClassLoader().getResourceAsStream(xmlFileName);
        ClientConfig clientConfig = new XmlClientConfigBuilder(xmlResource).build();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IList<String> list = client.getList("customers");
        list.add(new Random().nextInt() + "");
        list.forEach(s -> System.out.println(s));

    }
}
