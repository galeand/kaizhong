package com.sse.kaizhong.controller;

import com.sse.kaizhong.bean.Friend;
import com.sse.kaizhong.service.FriendService;
import com.sse.kaizhong.service.KaizhongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class KaizhongController {
    private static final Logger logger = LoggerFactory.getLogger(KaizhongController.class);
    private static List<String> logsList = new ArrayList<>(100);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Qualifier("service02")
    @Autowired
    private KaizhongService queryService;

    @Autowired
    private FriendService friendService;

//    @ResponseBody
//    @RequestMapping("/query")
//    public Map<String, Object> query() {
//        Map<String, Object> map = jdbcTemplate.queryForMap("select * from kaizhongs WHERE `姓名` = '曾云莲'");
//        return map;
//    }

    @RequestMapping("/searchfriend")
    public String searchMyFriend(Map<String,Object> map,@RequestParam("name") String name){
        List<String> list = friendService.getOneFriend(name);
        list.remove(0);//第一个存的有多少条记录
        list.forEach(friend -> {
            map.put("friends", list);
        });
        return "searchfriend";
    }

    @RequestMapping("/friend")
    public String getMyFriendInfo(Map<String, Object> map) {
        List<String> list = friendService.getFriends();
        list.remove(0);//第一个存的有多少条记录
        list.forEach(friend -> {
            map.put("friends", list);
        });
        return "friends";
    }


    @RequestMapping("/kz")
    public String queryFromKaizhong(Map<String, Object> map, @RequestParam("name") String name) {
        String choose = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        logger.info("查询关键字：{}", name);
        name = name.trim();
        if (name.length() > 0) {
            logsList.add("关键字：" + name + "  ;查询时间:" + time);
        }
        if (name.endsWith("大学") || name.endsWith("学院")) {
            choose = "学校";
        } else if (name.endsWith("专业")) {
            choose = "专业";
        } else {
            choose = "名字";
        }
        switch (choose) {
            case "学校": {
                List<String> list = queryService.queryStudentByCollege(name);
                if (list.size() > 0) {
                    map.put("num", "<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    map.put("students", list);
                } else {
                    map.put("students", "大学名称错误或该届学生无报该学校！");
                }
                break;
            }
            case "名字": {
                List<String> list = queryService.queryStudentByName(name);
                if (list.size() > 0) {
                    map.put("num", "<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    map.put("students", list);
                } else {
                    map.put("students", Arrays.asList("查无此人，请核实后再次查询！"));
                }
                break;
            }
            case "专业": {
                name = name.substring(0, name.length() - 2);
                System.out.println("专业：" + name);
                List<String> list = queryService.queryStudentByMajor(name);
                if (list.size() > 0) {
                    map.put("num", "<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    map.put("students", list);
                } else {
                    map.put("students", Arrays.asList("专业输入错误或该届学生无报该专业！"));
                }
                break;
            }
            default:
                break;
        }

        return "index";
    }


    @PostMapping("/login")
    public String login(@RequestParam("user") String user,
                        @RequestParam("pwd") String pwd,
                        Map<String, Object> logsMap) {
        if (user.equals("admin") && pwd.equals("12345")) {
            logsMap.put("logs", logsList);
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
