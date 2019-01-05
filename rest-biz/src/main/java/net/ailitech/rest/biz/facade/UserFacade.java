package net.ailitech.rest.biz.facade;

import com.github.ailitech.rest.model.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserFacade {

    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name){
        return "hello word,"+name;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public UserDto user(@RequestBody UserDto userDto){
        return userDto;
    }

}
