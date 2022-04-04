package com.maeinghome.ehcache;

import com.maeinghome.ehcache.service.ICacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {EhcacheApplication.class})
public class EhcacheUnitTest {
    @Autowired
    private ICacheService cacheService;

    @Test
    public void serviceTest() {
        String s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name1", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name1", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name1", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
        s = cacheService.selectOne("name", "tel");
        System.out.println(s);
    }
}
