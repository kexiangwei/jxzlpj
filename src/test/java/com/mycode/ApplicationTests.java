package com.mycode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Test
    public void contextLoads() {

        for (int i = 0; i < 10; i++) {
            if(i == 3){
                continue; // 0 1 2 4 5 6 7 8 9 后置语句         跳过本次循环
//                break; //0 1 2 后置语句       跳出当前循环
//                return; //0 1 2       方法结束
            }
            System.out.println(i);
        }
        System.out.println("后置语句");
    }

}
