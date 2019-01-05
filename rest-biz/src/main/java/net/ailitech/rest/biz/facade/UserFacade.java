package net.ailitech.rest.biz.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFacade {

    @RequestMapping("/hello")
    public String hello(){
        return "hello word";
    }
}
