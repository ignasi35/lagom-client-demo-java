package com.example.lagom.client;

import com.example.hello.api.HttpbinService;
import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        LagomClientFactory clientFactory = LagomClientFactory.create("legacy-system",
            LagomClientFactory.class.getClassLoader());

        try {
            URI staticServiceUri = URI.create("https://httpbin.org");
            HttpbinService serviceClient = clientFactory.createClient(HttpbinService.class, staticServiceUri);
            serviceClient.get().invoke()
                .thenAccept(hr -> {
                    System.out.println(hr);
                    latch.countDown();
                })
                .exceptionally(excp -> {
                    excp.printStackTrace();
                    latch.countDown();
                    return null;
                })
            ;
        } finally {
            latch.await();
            clientFactory.close();
        }
    }
}
