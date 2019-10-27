package com.sse.kaizhong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sse.kaizhong.bean.TestDruid;
import com.sse.kaizhong.uitl.SimpleHttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IocContextTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestDruid.class);
        System.out.println("容器里面已经注入的bean：");
        printBeans(context);
        Object testdruid = context.getBean("testDruid");
        System.out.println(testdruid);
    }

    private void printBeans(AnnotationConfigApplicationContext context) {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void testHttp(){
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=219.136.134.157";

        String res = null;
        try {
             res = SimpleHttpUtil.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);

        String ret = null;
        if (!StringUtils.isEmpty(res)) {
            JSONObject jsonObject = JSON.parseObject(res);
            if (jsonObject.containsKey("data")){
                ret = jsonObject.getString("data");
            }
        }
        System.out.println(ret);
    }

}
