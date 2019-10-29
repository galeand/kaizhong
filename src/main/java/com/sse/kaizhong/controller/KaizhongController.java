package com.sse.kaizhong.controller;

import com.sse.kaizhong.bean.Friend;
import com.sse.kaizhong.service.FriendService;
import com.sse.kaizhong.service.IpRecordService;
import com.sse.kaizhong.service.KaizhongService;
import com.sse.kaizhong.service.impl.IpRecordServiceImpl;
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

    @Qualifier("service02")
    @Autowired
    private KaizhongService queryService;

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


    @RequestMapping("/kz")
    public String queryFromKaizhong(Map<String, Object> map,
                                    @RequestParam("name") String name,
                                    HttpServletRequest request) {
        if (StringUtils.isEmpty(name.trim())){
            map.put("students","输入不能为空!");
            return "index";
        }
        String choose = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        logger.info("查询关键字:{}", name);
        logger.info("查询时间:{}",time);
        //记录查询ip，对应解析ip地址对应的物理地址
        String ip = IpUtil.getIpAddr(request);
        logger.info("请求的ip:{}", ip);
        List<String> deviceInfoList = IpUtil.getDeviceAndOS(request);
        logger.info("请求的设备信息:{}", deviceInfoList);
        if (!ipRecordService.execute(ip, name, deviceInfoList)){
            logger.info("记录访问ip失败,请求:{}", request);
        }

        name = name.trim();
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
