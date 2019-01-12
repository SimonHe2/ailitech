package net.ailitech.rest.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(InboundAutoConfig.class)
public class BootLauncher {
    public static void main(String[] args){
        SpringApplication.run(BootLauncher.class,args);
    }
}
