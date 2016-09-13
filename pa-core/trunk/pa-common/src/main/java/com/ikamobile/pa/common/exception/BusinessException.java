package com.ikamobile.pa.common.exception;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(){

    }

    public BusinessException(String message){
        super(message);
    }
}
