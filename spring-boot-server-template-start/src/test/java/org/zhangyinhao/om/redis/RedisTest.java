package org.zhangyinhao.om.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhangyinhao.om.ApplicationStart;

@Slf4j
@SpringBootTest(classes = {ApplicationStart.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testAddAndGet(){
        String key = "test";
        stringRedisTemplate.opsForValue().set(key,"hello java。。。。。。。");
        String val = stringRedisTemplate.opsForValue().get(key);
        log.info("redis test val = {}",val);
    }

}
