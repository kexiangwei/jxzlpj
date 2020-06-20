package com.mycode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("123","z3");
        map.put("t",null);
        map.put("t1",null);
        map.put("234","L4");
        map.put("t2",null);
        map.put("345","W5");
        System.out.println(map);
    }

}
