package net.ailitech.rest.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class WishPo {
    private Long id;
    private String content;
    private String userIdentity;
    private Date createDate;
}
