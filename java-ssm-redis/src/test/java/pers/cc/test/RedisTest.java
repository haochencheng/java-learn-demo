package pers.cc.test;

import redis.clients.jedis.Jedis;

/**
 * Created by cc on 2016/12/6.
 */
public class RedisTest {

    public static void main(String[] args) {
        //连接本地Redis服务
        Jedis jedis=new Jedis("localhost");
        System.out.println("Connection to server successfully");
        //查看服务是否运行
        System.out.println("Server is running:"+jedis.ping());
        //设置userName
        jedis.set("userName","cc");
        //获取userName
        System.out.println("Stored string in redis:"+jedis.get("userName"));
    }

}
