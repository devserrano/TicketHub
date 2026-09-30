package com.tickethub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync        // correos asíncronos
@EnableScheduling   // job de SLA
public class TicketHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketHubApplication.class, args);
    }
}
