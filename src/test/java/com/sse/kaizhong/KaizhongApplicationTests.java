package com.sse.kaizhong;

import com.sse.kaizhong.bean.Kaizhong;
import com.sse.kaizhong.mapper.KaizhongMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KaizhongApplicationTests {
    @Autowired
    private KaizhongMapper kaizhongMapper;

    @Test
    public void contextLoads() {
        List<Kaizhong> list = kaizhongMapper.getStudentByCollege("北京邮电大学");
        System.out.println("查询数目：" + list.size());
        System.out.println(list.toString());

    }

}
