import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/*
 * Created by 颜群
 */
public class JWTClient {

    //客户端 携带token访问服务端
    //token + 盐

    public static void visitServer(){
        String token = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtaWNyb3NlcnZpY2UiLCJzdWIiOiLlvq7mnI3liqHpobnnm64iLCJpYXQiOjE1OTM0MDA1NTIsImV4cCI6MTU5MzQwMjM1MiwiYXVkIjoi6Ieq5a6a5LmJ5aOw5piO5L-h5oGvIn0.wo3KfQ60D2whdwMdMZjemRXz5m3ScuJsfjILV5p7RQ4";
        String salt = "yanqun" ;

        //获取jsw对象,jws就包含了所有的服务端校验信息
        Jws<Claims> jws = Jwts.parser().setSigningKey(salt).parseClaimsJws(token);
        System.out.println(jws);

        Claims claims = jws.getBody();
        System.out.println("header:"+jws.getHeader());
        System.out.println("payload:"+claims.getId());
        System.out.println("payload:"+claims.getSubject());
        System.out.println("payload:"+claims.getIssuedAt());
        System.out.println("payload:"+claims.getExpiration());
        System.out.println("payload:"+claims.getAudience());

        System.out.println("signature:"+jws.getSignature());



        System.out.println(jws.getSignature().equals(token.split("\\.")[2]));




    }

    public static void main(String[] args) {
        visitServer();
    }

}
