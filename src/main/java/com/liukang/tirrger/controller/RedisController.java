package com.liukang.tirrger.controller;

import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String,Object>   testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int","1");
        stringRedisTemplate.opsForValue().increment("int",1);
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String,String> hash =  new HashMap<>();
        hash.put("field1","value1");
        hash.put("field2","value2");

        stringRedisTemplate.opsForHash().putAll("hash",hash);
        stringRedisTemplate.opsForHash().put("hash","field3","value3");
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("field1","field2");
        hashOps.put("field4","value4");
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> testList(){
        stringRedisTemplate.opsForList().leftPushAll("list1","v2","v3","v4","v5","v10");
        stringRedisTemplate.opsForList().rightPushAll("list2","v1","v2","v3","v4","v6");
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");
        Object result1 = listOperations.rightPop();
        Object result2 = listOperations.index(1);
        listOperations.leftPush("v0");
        Long size = listOperations.size();
        List elements = listOperations.range(0, size - 2);
        Map<String,Object> map =  new HashMap<>();
        map.put("success",true);
        return map;
    }
    @RequestMapping("/set")
    @ResponseBody
    public Map<String,Object> testSet(){
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v6");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8");
        BoundSetOperations setOpt = stringRedisTemplate.boundSetOps("set1");
        setOpt.add("v6","v8");
        setOpt.add("v1","v7");
        Set set1 =  setOpt.members();
        Long size = setOpt.size();
        Set inter = setOpt.intersect("set2");
        setOpt.intersectAndStore("set2","inter");

        Set diff = setOpt.diff("set2");
        setOpt.diffAndStore("set2","diff");

        Set un = setOpt.union("set2");
        setOpt.unionAndStore("set2","un");

        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    @RequestMapping("/zset")
    @ResponseBody
    public Map<String,Object> testZset(){
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet =  new HashSet<>();
        for(int i=0 ;i<9 ;i++){
            double score = i*0.1;

            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value"+i,score);
            typedTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        BoundZSetOperations<String, String> zSetOps = stringRedisTemplate.boundZSetOps("zset1");
        zSetOps.add("value10",0.26);
        Set<String> range = zSetOps.range(1, 6);
        zSetOps.rangeByScore(0.2,0.6);
        RedisZSetCommands.Range range1 = new RedisZSetCommands.Range();
        range1.gt("value3");
        range1.lte("value8");
        Set<String> setLex = zSetOps.rangeByLex(range1);
        zSetOps.remove("value9","value2");
        Double score = zSetOps.score("value8");
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOps.rangeWithScores(1, 6);
        Set<ZSetOperations.TypedTuple<String>> typedTuples1 = zSetOps.rangeByScoreWithScores(1, 6);
        Set<String> set = zSetOps.reverseRange(2, 8);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;


    }
    @RequestMapping("/lua")
    @ResponseBody
    public Map<String,Object> testLua(){
        DefaultRedisScript<String> rs = new DefaultRedisScript<String>();
        rs.setScriptText("return 'Hello Redis'");
        rs.setResultType(String.class);
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(rs,stringSerializer,stringSerializer,null);
        Map<String,Object> map = new HashMap<>();
        map.put("str",str);
        return map;
    }
    @RequestMapping("/lua2")
    @ResponseBody
    public Map<String,Object> testLua2(String key1,String key2,String value1,String value2){
        String lua = "redis.call('set',KEYS[1],ARGV[1])\n"+
                     "redis.call('set',KEYS[2],ARGV[2])\n"+
                    "local str1 = redis.call('get',KEYS[1])\n"+
                    "local str2 = redis.call('get',KEYS[2])\n"+
                    "if str1 == str2 then \n"+
                    "return 1 \n"+
                    "end \n"+
                    "return 0\n";
        DefaultRedisScript<Long> rs = new DefaultRedisScript<Long>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);
        Long result  = (Long) redisTemplate.execute(rs, stringSerializer, stringSerializer, keyList, value1, value2);
        Map<String,Object> map = new HashMap<>();
        map.put("result",result);
        return map;
    }
}
