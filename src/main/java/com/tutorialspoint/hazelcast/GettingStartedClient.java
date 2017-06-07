package com.tutorialspoint.hazelcast;

/**
 * Created by phuongdv on 6/7/17.
 */
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * See here : https://github.com/kobalski/zookeeper-sample
 */

public class GettingStartedClient {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5702");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap map = client.getMap("customers");
//        map.put("aaa","bbb");
        System.out.println("Map Size:" + map.size());
    }
}
