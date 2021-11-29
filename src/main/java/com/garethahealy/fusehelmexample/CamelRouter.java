package com.garethahealy.fusehelmexample;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Value("${app-prop}")
    private String appProp;

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("timer://foo?fixedRate=true&period=60000").id("hello")
            .log("hello: " + appProp);
        // @formatter:on
    }
}