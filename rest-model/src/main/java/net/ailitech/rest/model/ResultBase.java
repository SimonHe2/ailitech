package net.ailitech.rest.model;

import lombok.Data;

@Data
public class ResultBase {
    private int status;
    private String message;

    public boolean isSuccess(){
        return status==0;
    }
}
