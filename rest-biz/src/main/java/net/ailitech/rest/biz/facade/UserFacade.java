package net.ailitech.rest.biz.facade;

import com.github.ailitech.rest.dal.mapper.QuestionDao;
import com.github.ailitech.rest.model.dto.UserDto;
import com.github.ailitech.rest.model.po.QuestionPo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserFacade {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name){
        return "hello word,"+name;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public List<QuestionPo> user(@RequestBody UserDto userDto){

        List<QuestionPo> pso= questionDao.query(null);
        return pso;
    }

}
