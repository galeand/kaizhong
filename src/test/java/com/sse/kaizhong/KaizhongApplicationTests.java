package com.sse.kaizhong;

import com.sse.kaizhong.bean.Friend;
import com.sse.kaizhong.bean.Kaizhong;
import com.sse.kaizhong.mapper.FriendMapper;
import com.sse.kaizhong.mapper.KaizhongMapper;
//import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KaizhongApplicationTests {
    @Autowired
    private KaizhongMapper kaizhongMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Test
    public void contextLoads() {
        List<Kaizhong> list = kaizhongMapper.getStudentByCollege("北京邮电大学");
        System.out.println("查询数目：" + list.size());
        System.out.println(list.toString());

    }

    @Test
    public void test01() {
        List<Friend> friendList = friendMapper.getAllFriends();
        System.out.println(friendList.toString());
    }
//    @Test
//    public void test02() {
//        String pwd = "asd123";
//        String pwd_md5Hex = DigestUtils.md5Hex(pwd);
//        System.out.println(pwd_md5Hex);
//    }

}
