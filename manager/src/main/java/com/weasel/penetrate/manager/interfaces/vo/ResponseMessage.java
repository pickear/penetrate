package com.weasel.penetrate.manager.interfaces.vo;

/**
 * Created by dylan on 2017/3/4.
 */
public class ResponseMessage {

    private boolean success;
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(boolean success) {
        this.success = success;
    }

    public ResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseMessage setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public static ResponseMessage success(){

        return new ResponseMessage(true,"成功");
    }

    public static ResponseMessage error(){
        return new ResponseMessage(false);
    }
}
