package com.mycode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void test() {
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.forEach((k,v) -> {
            if("b".equals(k)){
                map.remove(k);
            }
        });
        map.forEach((k,v) -> System.out.println(k));
    }
    @Test
    public void contextLoads() {

        for (int i = 0; i < 10; i++) {
            if(i == 3){
                continue; // 0 1 2 4 5 6 7 8 9 test         跳过本次循环
//                break; //0 1 2 test       跳出循环
//                return; //0 1 2       方法结束
            }
            System.out.println(i);
        }
        System.out.println("test");
    }

}
