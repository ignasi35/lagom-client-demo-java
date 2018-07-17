package com.example.hello.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;


public interface HttpbinService extends Service {

    default Descriptor descriptor() {
        return named("httpbin").withCalls(
            pathCall("/get", this::get )
        );
    }

    ServiceCall<NotUsed,HeadersResponse> get();
}
