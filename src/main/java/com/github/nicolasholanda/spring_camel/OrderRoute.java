package com.github.nicolasholanda.spring_camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderRoute extends RouteBuilder {

    @Override
    public void configure() {

        onException(Exception.class)
            .maximumRedeliveries(3)
            .redeliveryDelay(1000)
            .handled(true)
            .log("Error while processing item: ${exception.message}");

        from("timer:orderTimer?period=5000")
            .setBody().constant("item1,item2,item3")
            .log("Order received: ${body}")
            .split().method(OrderSplitter.class)
            .parallelProcessing()
            .process(new ItemProcessor())
            .aggregate(constant(true), new OrderAggregator())
                .completionSize(3)
            .log("Finished order: ${body}");
    }
}
