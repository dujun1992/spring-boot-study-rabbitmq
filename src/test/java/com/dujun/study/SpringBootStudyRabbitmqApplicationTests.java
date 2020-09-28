package com.dujun.study;

import com.dujun.study.springboot.MyProducer;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootStudyRabbitmqApplicationTests {

    @Autowired
    MyProducer myProducer;

    @Test
    void test() {
        myProducer.send(("send i :"+123).getBytes());
    }

}
