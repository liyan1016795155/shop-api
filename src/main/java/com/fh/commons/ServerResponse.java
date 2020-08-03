package com.fh.commons;

public class ServerResponse {
    private Integer code;
    private String msg;
    private Object data;


    private  ServerResponse(){

    }
    private  ServerResponse(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public static ServerResponse success(Object data){
        return   new ServerResponse(200,"成功",data);

     }
     public static ServerResponse success(){
        return new ServerResponse(ServerEnum.SUCCESS.getCode(), ServerEnum.SUCCESS.getMsg(),null);
     }

    public static ServerResponse loginError(){
        return new ServerResponse(ServerEnum.LOGIN_ERROR.getCode(), ServerEnum.LOGIN_ERROR.getMsg(),null);
    }

    public static ServerResponse loginError(Object data){
        return   new ServerResponse(ServerEnum.LOGIN_ERROR.getCode(), ServerEnum.LOGIN_ERROR.getMsg(),data);

    }

     public static ServerResponse error(){
        return new ServerResponse(ServerEnum.ERROR.getCode(), ServerEnum.ERROR.getMsg(),null);
     }

    public static ServerResponse error(Object data){
        return   new ServerResponse(ServerEnum.ERROR.getCode(), ServerEnum.ERROR.getMsg(),data);

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
