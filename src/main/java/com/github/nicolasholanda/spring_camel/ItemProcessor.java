package com.github.nicolasholanda.spring_camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ItemProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        String item = exchange.getIn().getBody(String.class);
        if ("item2".equals(item)) {
            throw new RuntimeException("Failed to process " + item);
        }
        exchange.getIn().setBody("Processed-" + item);
    }
}

