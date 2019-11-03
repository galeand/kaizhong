package com.sse.kaizhong.controller;

import com.sse.kaizhong.bean.Friend;
import com.sse.kaizhong.service.FriendService;
import com.sse.kaizhong.service.IpRecordService;
import com.sse.kaizhong.service.KaizhongService;
import com.sse.kaizhong.service.impl.IpRecordServiceImpl;
import com.sse.kaizhong.service.impl.KaizhongServiceImpl02;
import com.sse.kaizhong.uitl.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class KaizhongController {
    private static final Logger logger = LoggerFactory.getLogger(KaizhongController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Qualifier("service02")
    @Autowired
    private KaizhongServiceImpl02 queryService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private IpRecordServiceImpl ipRecordService;


    //    @ResponseBody
    @RequestMapping("/add")
    public String addFriendInfo(Map<String, Object> map,
                                @RequestParam("name") String name,
                                @RequestParam("collegeName") String collegeName,
                                @RequestParam("major") String major,
                                @RequestParam("relationship") String relationship,
                                @RequestParam("postgraduate_college") String postgraduate_college,
                                @RequestParam("postgraduate_major") String postgraduate_major,
                                @RequestParam("postgraduate_academy") String postgraduate_academy,
                                @RequestParam("moreinfo") String moreinfo) {
        Friend friend = new Friend(collegeName, major, name, relationship, postgraduate_college, postgraduate_academy, postgraduate_major, moreinfo);
        System.out.println(friend.toString());
        Boolean res = friendService.insertFriend(friend);
        if (res) {
            List<String> list = friendService.getFriends();
            list.remove(0);//第一个存的有多少条记录
            list.forEach(val -> {
                map.put("friends", list);
            });
            return "friends";
        }
        return "friends";
    }

    public String editFriendInfo(Map<String, Object> map,
                                 @RequestParam("name") String name,
                                 @RequestParam("collegeName") String collegeName,
                                 @RequestParam("major") String major,
                                 @RequestParam("relationship") String relationship,
                                 @RequestParam("postgraduate_college") String postgraduate_college,
                                 @RequestParam("postgraduate_major") String postgraduate_major,
                                 @RequestParam("postgraduate_academy") String postgraduate_academy,
                                 @RequestParam("moreinfo") String moreinfo) {


        return null;
    }

    @RequestMapping("/searchfriend")
    public String searchMyFriend(Map<String, Object> map, @RequestParam("name") String name) {
        name = name.trim();
        List<String> list = friendService.getOneFriend(name);
        System.out.println(name);
        if (list.size() <= 0) {
            map.put("friends", Arrays.asList("输入有误或查无此人！", "请按照下面列出的人名进行搜索"));

        } else {
            list.remove(0);//第一个存的有多少条记录
            list.forEach(friend -> {
                map.put("friends", list);
            });
        }
        return "searchfriend";
    }

    @RequestMapping("/friend")
    public String getMyFriendInfo(@RequestParam("user") String user,
                                  @RequestParam("pwd") String pwd,
                                  Map<String, Object> map) {
        boolean session = false;
        if (session || user.equals("admin") && pwd.equals("asd123") ) {
            session = true;
            List<String> list = friendService.getFriends();
            list.remove(0);//第一个存的有多少条记录
            list.forEach(friend -> {
                map.put("friends", list);
            });
            return "friends";
        }
        return "friendlogin";
    }


    @RequestMapping("/kaizhong")
    public String queryFromKaizhong(Map<String, Object> map,
                                    @RequestParam("name") String name,
                                    HttpServletRequest request) {
        if (StringUtils.isEmpty(name.trim())){
            map.put("students","输入不能为空!");
            return "index";
        }
        List<Object> objects = new ArrayList<>();
        objects.add(name);
        objects.add(request);
        List<Object> res = queryService.beforeExecute(objects);
        res.add(name);

        List<Object> ret = queryService.execute(res);
        if (ret.size() == 2){
            //正常返回，查询到结果
            map.put("num", ret.get(0));
            map.put("students", ret.get(1));
        }else if (ret.size() == 1){
            //未查询到结果
            map.put("students", ret.get(0));
        }else {
            //兜底
            map.put("students", "输入有误，请重新输入！");
        }

        return "index";
    }

    @RequestMapping("/logall")
    public String logQuery(Map<String, Object> map){
        List<String> list = ipRecordService.getAll();
        map.put("logs", list);
        return "log";
    }


    @RequestMapping("/login")
    public String login(@RequestParam("user") String user,
                        @RequestParam("pwd") String pwd,
                        Map<String, Object> logsMap) {
        boolean session = false;
        if (session || user.equals("admin") && pwd.equals("12345") ) {
            //简单做了一个临时登录会话
            session = true;
            List<String> list = ipRecordService.getLately();
            logger.info("最近ip访问日志:{}",list);
            logsMap.put("logs", list);
            return "main";
        }
        return "login";
    }

    @ResponseBody
    @GetMapping("/rpckz")
    public String rpcQuery(@RequestParam String content) {
        content = content.trim();
        if (content.endsWith("大学") || content.endsWith("学院")) {
            return queryService.queryStudentByCollege(content).toString();
        } else if (content.endsWith("专业")) {
            return queryService.queryStudentByMajor(content).toString();
        } else {
            return queryService.queryStudentByName(content).toString();
        }
    }
}
