package ru.iteco.mfsm.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "ru.xemii.mfsm")
public class MFSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(MFSMApplication.class, args);
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("MFSMApplication inited");
    }
}
