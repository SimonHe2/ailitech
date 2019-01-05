package net.ailitech.rest.biz;

import com.github.ailitech.rest.support.SupportAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(SupportAutoConfig.class)
public class BootLauncher {
    public static void main(String[] args){
        SpringApplication.run(BootLauncher.class,args);
    }
}
