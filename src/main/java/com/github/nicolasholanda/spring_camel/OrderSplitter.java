package com.github.nicolasholanda.spring_camel;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderSplitter {
    public List<String> split(String body) {
        return Arrays.asList(body.split(","));
    }
}