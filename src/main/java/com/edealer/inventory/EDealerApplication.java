package com.edealer.inventory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EDealerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EDealerApplication.class).run(args);
    }
}
