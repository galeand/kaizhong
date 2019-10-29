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
        String header0 = "Mozilla/5.0 (Linux; Android 9; VTR-AL00 Build HUAWEIVTR-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 MQQBrowser/6.2 TBS/045005 Mobile Safari/537.36 V1_AND_SQ_8.1.5_1258_YYB_D QQ/8.1.5.4215 NetType/WIFI WebP/0.3.0 Pixel/1080 StatusBarHeight/72 SimpleUISwitch/0\n";

        String s1 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36";

        String s2 = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_1_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 QQ/8.1.5.461 V1_IPH_SQ_8.1.5_1_APP_A Pixel/750 Core/WKWebView Device/Apple(iPhone 8) NetType/WIFI QBWebViewType/1 WKType/1";

        String header = "Mozilla/5.0 (Linux; Android 9; HWI-TL00 Build/HUAWEIHWI-TL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.126 MQQBrowser/6.2 TBS/044904 Mobile Safari/537.36 V1_AND_SQ_8.1.5_1258_YYB_D QQ/8.1.5.4215 NetType/WIFI WebP/0.3.0 Pixel/1080 StatusBarHeight/72 SimpleUISwitch/0";
        String userInfo = header.substring(header.indexOf("(")+1, header.indexOf(")"));
        String[] userInfoArr = userInfo.split(";");
        String os = null;
        String device = null;
        if (userInfoArr.length >= 3){
            //手机访问
            os = userInfoArr[1] + "\t\t" + userInfoArr[0];
            String[] tempArr = userInfoArr[2].split("/");
            if (tempArr.length >= 2){
                device = tempArr[1];
            }else if (tempArr.length == 1){
                device = tempArr[0];
            }else {
                device = null;
            }
        }else if (userInfoArr.length == 2){
            //浏览器访问
            os = userInfoArr[1] + "\t\t" + userInfoArr[0];
            device = "Brower";
        }
        System.out.println("os:" + os + ";device:" + device);
    }
//    @Test
//    public void test02() {
//        String pwd = "asd123";
//        String pwd_md5Hex = DigestUtils.md5Hex(pwd);
//        System.out.println(pwd_md5Hex);
//    }



}
