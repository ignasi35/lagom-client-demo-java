package com.example.hello.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

import java.util.Map;

/**
 * Invoking https://httpbin.org/get returns a JSON with all the request's headers
 {
 "args": {},
 "headers": {
     "Accept-Encoding": "gzip, deflate, sdch, br",
     "Accept-Language": "en-US,en;q=0.8,es;q=0.6,ca;q=0.4",
     "Host": "httpbin.org",
     "Referer": "https://httpbin.org/",
     "Upgrade-Insecure-Requests": "1",
     "User-Agent": "..."
 },
 "origin": "11.23.24.198",
 "url": "https://httpbin.org/get"
 }
 */


@Value
public class HeadersResponse implements Jsonable {

    private final Map<String, String> headers;
    private final String origin;
    private final String url;

    @JsonCreator
    public HeadersResponse(Map<String, String> headers, String origin, String url){
        this.headers = headers;
        this.origin = origin;
        this.url = url;
    }
}
