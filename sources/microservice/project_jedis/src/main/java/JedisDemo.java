import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Created by 颜群
 */
public class JedisDemo {
    public static void main(String[] args) throws  Exception {
    //java - redis
        Jedis jedis = new Jedis("192.168.2.130",6379) ;


        System.out.println(  jedis.ping( ) );
        //String
        jedis.select(0) ;
        jedis.flushDB() ;
        jedis.set("kj1","vj1234567");
        jedis.setnx("kj2","vj2");
        String r = jedis.getrange("kj1",2,4) ;
        String v = jedis.get("kj1");
        System.out.println(v);
        System.out.println(r);

        //数字
        jedis.set("n","1") ;
        jedis.incr("n")     ;
        jedis.incr("n")     ;
        jedis.incr("n")     ;
        jedis.decr("n") ;
        jedis.incrBy("n",10) ;
        jedis.decrBy("n",3) ;
        System.out.println(  jedis.get("n")   );

        //key
        Boolean f = jedis.exists("n");
        System.out.println(f);
        String type = jedis.type("n");
        System.out.println(f);
        System.out.println(type);
        jedis.expire("n",100) ;

//        Thread.sleep(3000);
        System.out.println(  jedis.ttl("n") );

        jedis.del("n","kj1") ;

        //list
        jedis.lpush("list1","a","b","c") ;
//        jedis.lpush("list1","hello","world") ;
        System.out.println("----");
        jedis.lpush("list2","listv1","listv2","list2v3") ;
        jedis.lset("list1",1,"QQ") ;//"v1","QQ","v3"


//        jedis.lrem("list1",2,"v1") ;
//        jedis.ltrim( "list2", 0,1  );//只保留第0、第1个元素，其他全部删除

        List<String> list1 = jedis.lrange("list1", 0, -1);
        for(String e :list1){
            System.out.println(e);
        }

        System.out.println("----");
        List<String> list2 = jedis.lrange("list2", 0, 1);
        for(String e :list2){
            System.out.println(e);
        }

        //hash
        jedis.hset("person","name","zs");
        jedis.hset("person","age","23");

        Map<String, String> person = jedis.hgetAll("person");
        System.out.println(person);

        Map<String,String> map = new HashMap<>() ;
        map.put("id","1");
        map.put("name","lisi");

        jedis.hset("people" ,map) ;
        Map<String, String> result = jedis.hgetAll("people");
        System.out.println(result);

        Set<String> keys = jedis.hkeys("people");
        List<String> values = jedis.hvals("people") ;
        System.out.println("keys:"+keys);
        System.out.println("values:"+values);

        //set
        jedis.sadd("skey","sv1","sv2") ;
        Long count = jedis.scard("skey");
        System.out.println(count);
        System.out.println(  jedis.sismember("skey","sv1"));

       //sortedset
        jedis.zadd("zset" , 10,"zv1") ;
        jedis.zadd("zset" , 9,"zv2") ;

        Map<String,Double> map2 = new HashMap<>() ;
        map2.put("zv3",8.8) ;
        map2.put("zv4",9.9) ;
        jedis.zadd("zset",map2) ;
        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println(zset);

        Set<Tuple> zset1 = jedis.zrangeByScoreWithScores("zset", 9, 10);
        System.out.println(zset1);


    }
}
