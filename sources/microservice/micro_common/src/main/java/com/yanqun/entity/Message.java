package com.yanqun.entity;

/*
 * Created by 颜群
 */
public class Message {
    private boolean flag ; //成功/失败
    private Integer code ;//响应码
    private Object data ;//请求的结果数据

    public Message() {
    }

    public Message(boolean flag, Integer code, Object data) {
        this.flag = flag;
        this.code = code;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
