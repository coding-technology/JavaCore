import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by 颜群
 */
public class JWTServer {
    //1.服务端产生token
    public static void createToken(){
        //1.Header

        Map<String,Object> header = new HashMap<>();
        //类型
        header.put("typ","jwt");
        //加密算法
        header.put("alg","HS256");


        //2.PayLoad  (Map  Claims)
        Claims claims = new DefaultClaims() ;
        claims.setId("microservice")
                .setSubject("微服务项目")
                .setIssuedAt(new Date() )//签发时间
                .setExpiration(new Date( System.currentTimeMillis() +  1800*1000       )  )//有效时间  （半小时内有效）
                .setAudience("自定义声明信息") ;

        //3.Signature

        //盐
        String  salt = "yanqun"  ;
         byte[] saltBase64 =  DatatypeConverter.parseBase64Binary(salt) ;

        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;

        SecretKeySpec secretKey =  new SecretKeySpec(saltBase64,hs256.getJcaName()) ;


        //生成token

        String token = Jwts.builder().setHeader(header).setClaims(claims).signWith(hs256, secretKey).compact();
        System.out.println("服务端生成token："+token);

    }

    public static void main(String[] args) {
        createToken();
    }
}
