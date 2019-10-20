package com.yanqun;

public class RequestInfo {
    private long requestId ;
    private String requsetDesc ;//下单、支付、推荐
    public RequestInfo() {
    }

    public RequestInfo(long requestId, String requsetDesc) {
        this.requestId = requestId;
        this.requsetDesc = requsetDesc;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequsetDesc() {
        return requsetDesc;
    }


    public void setRequsetDesc(String requsetDesc) {
        this.requsetDesc = requsetDesc;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "requestId=" + requestId +
                ", requsetDesc='" + requsetDesc + '\'' +
                '}';
    }
}
