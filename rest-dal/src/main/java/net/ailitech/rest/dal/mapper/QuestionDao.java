package net.ailitech.rest.dal.mapper;

import net.ailitech.rest.model.po.QuestionPo;

import java.util.List;

public interface QuestionDao {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionPo record);

    int insertSelective(QuestionPo record);

    QuestionPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionPo record);

    int updateByPrimaryKey(QuestionPo record);
    List<QuestionPo> query(QuestionPo record);


}