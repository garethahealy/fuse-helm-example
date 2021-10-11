package com.garethahealy.fusehelmexample;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("direct:checkTransactions").id("checkTransactions")
            .log("todo - read from kafka");
        // @formatter:on
    }
}