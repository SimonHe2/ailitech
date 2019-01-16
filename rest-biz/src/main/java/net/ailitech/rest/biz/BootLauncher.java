package net.ailitech.rest.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.ailitech.rest")
public class BootLauncher {
    public static void main(String[] args){
        SpringApplication.run(BootLauncher.class,args);
    }
}
