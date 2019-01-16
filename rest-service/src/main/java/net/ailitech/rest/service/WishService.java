package net.ailitech.rest.service;

import net.ailitech.rest.dal.mapper.WishDao;
import net.ailitech.rest.model.ResultBase;
import net.ailitech.rest.model.dto.WishDto;
import net.ailitech.rest.model.po.WishPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WishService {
    @Autowired
    private WishDao wishDao;
    public ResultBase saveWish(WishDto wishDto){
        ResultBase result=new ResultBase();
        if(wishDto==null){
            result.setStatus(-4000);
            result.setMessage("wishDto is null");
            return result;
        }
        if(StringUtils.isBlank(wishDto.getContent())){
            result.setStatus(-4001);
            result.setMessage("wishDto.content is null");
            return result;
        }
        WishPo wishPo=new WishPo();
        wishPo.setContent(wishDto.getContent());
        wishPo.setCreateDate(new Date());
        wishDao.insert(wishPo);
        return result;
    }
}
