package net.ailitech.rest.biz.facade;


import net.ailitech.rest.model.ResultBase;
import net.ailitech.rest.model.dto.WishDto;
import net.ailitech.rest.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class wishFacade {

    @Autowired
    private WishService wishService;

    @RequestMapping(value = "/wish",method = RequestMethod.POST)
    public boolean wish(@RequestBody WishDto request){

        ResultBase result=wishService.saveWish(request);
        return result.isSuccess();
    }

}
