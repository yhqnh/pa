package com.ikamobile.service.exception;

/**
 * Created by Administrator on 2016/7/10.
 */
public class SMSException extends Exception {
    final public static String INTERNAL_ERROR = "-1";

    private String code="";
    //private String message="";
    private String to;
    private String smsMsg;

    public SMSException(String code,String message){
        super(message);
        this.code = code;
        //this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSmsMsg() {
        return smsMsg;
    }

    public void setSmsMsg(String smsMsg) {
        this.smsMsg = smsMsg;
    }

    public void record(String to,String smsMsg){
        this.to = to;
        this.smsMsg = smsMsg;
    }
}
