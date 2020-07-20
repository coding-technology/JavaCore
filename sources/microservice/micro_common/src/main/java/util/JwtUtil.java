package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * Created by 颜群
 */

@Component
@ConfigurationProperties("jwt.config")

@PropertySource("classpath:application.yml")
public class JwtUtil {
    private String key ;
    private long ttl ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    //服务端生成jwt-token的方法 （JWTServer）
    //约定：roles有两个值   normal/admin
    public String createJWT(String id, String subject,String roles){
        Date now = new Date() ;
      long nowMillis =   System.currentTimeMillis() ;

      return  Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt( now  )
                .setExpiration( new Date( nowMillis +  ttl   )   )
                .signWith( SignatureAlgorithm.HS256, key   )
                .claim("roles",roles).compact() ;//token
    }


    //客户单发送jwt-token时进行的校验方法 (JWTClient)
     public Claims parseJWT(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody() ;
     }
}

