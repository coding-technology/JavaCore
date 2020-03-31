package micro_httpclient.entity;

/*
 * Created by 颜群
 */
public class HttpResponse {
    private Integer statusCode ;
    private String body ;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
