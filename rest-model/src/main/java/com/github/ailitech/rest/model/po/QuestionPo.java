package com.github.ailitech.rest.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionPo {
    private Long id;

    private String title;

    private Date createTime;

    private Integer diffcult;

    private Integer grade;

    private String imgUrl;

    private Integer type;

    private Integer subject;

    private String options;
}