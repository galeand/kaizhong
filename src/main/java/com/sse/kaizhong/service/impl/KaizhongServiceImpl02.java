package com.sse.kaizhong.service.impl;

import com.sse.kaizhong.bean.Kaizhong;
import com.sse.kaizhong.mapper.KaizhongMapper;
import com.sse.kaizhong.service.BaseService;
import com.sse.kaizhong.service.KaizhongService;
import com.sse.kaizhong.uitl.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("service02")
public class KaizhongServiceImpl02 implements KaizhongService, BaseService {
    private static final Logger logger = LoggerFactory.getLogger(KaizhongServiceImpl02.class);

    @Autowired
    private KaizhongMapper kaizhongMapper;

    @Autowired
    private IpRecordServiceImpl ipRecordService;

    //    @Qualifier("service02")
    @Autowired
    private KaizhongServiceImpl02 queryService;

    @Override
    public List<Object> beforeExecute(List<Object> objects) {
        String name = objects.get(0).toString();
        HttpServletRequest request = (HttpServletRequest)objects.get(1);

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
        List<Object> ret = new ArrayList<>();
        ret.add(choose);
        return ret;
    }

    @Override
    public List<Object> execute(List<Object> objects) {
        String choose = objects.get(0).toString();
        String name = objects.get(1).toString();

        List<Object> res = new ArrayList<>();
        switch (choose) {
            case "学校": {
                List<String> list = queryService.queryStudentByCollege(name);
                if (list.size() > 0) {
                    res.add("<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    res.add(list);
                } else {
                    res.add("大学名称错误或该届学生无报该学校！");
                }
                break;
            }
            case "名字": {
                List<String> list = queryService.queryStudentByName(name);
                if (list.size() > 0) {
                    res.add("<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    res.add(list);
                } else {
                    res.add("查无此人，请核实后再次查询！");
                }
                break;
            }
            case "专业": {
                name = name.substring(0, name.length() - 2);
                System.out.println("专业：" + name);
                List<String> list = queryService.queryStudentByMajor(name);
                if (list.size() > 0) {
                    res.add("<共查询到" + list.get(0) + "条记录>");
                    list.remove(0);
                    res.add(list);
                } else {
                    res.add("专业输入错误或该届学生无报该专业!");
                }
                break;
            }
            default:
                break;
        }
        return res;
    }

    public List<String> queryStudentByName(String name) {

        List<Kaizhong> list = kaizhongMapper.getStudentByName(name);
        List<String> res = new ArrayList<>();
        if (list.size() > 0) {
            res.add(String.valueOf(list.size()));
            for (Kaizhong k : list) {
                res.add("姓名：" + k.getName());
                res.add("录取时间：" + k.getAdmissionTime());
                res.add("是否录取：" + k.getAdmission());
                res.add("录取批：" + k.getAdmissionBatch());
                res.add("录取类型：" + k.getAdmissionType());
                res.add("是否定向：" + k.getDirectionOrNot());
                res.add("录取大学：" + k.getAdmissionCollege());
                res.add("专业：" + k.getMajor());
                res.add("毕业高中：" + k.getSeniorSchool());
                res.add("班次：" + k.getClassNumber());
                res.add("a:" + k.getA());
                res.add("1:" + k.getOne());
                res.add("0:" + k.getZero());
                res.add("编号：" + k.getIdentifier());
                res.add("代号：" + k.getCodeName());
                res.add("0129:" + k.getX1());
                res.add("id:" + k.getId());
                res.add("a1:" + k.getA1());
                res.add("------------------------------");
            }
        }
        return res;
    }

    public List<String> queryStudentByCollege(String collegeName) {
        List<Kaizhong> list = kaizhongMapper.getStudentByCollege(collegeName);
        List<String> res = new ArrayList<>();
        if (list.size() > 0) {
            res.add(String.valueOf(list.size()));
            for (Kaizhong k : list) {
//                res.add(k.toString());
                String mid = "【 姓名：" + k.getName() + ";录取大学：" + k.getAdmissionCollege() + ";专业：" + k.getMajor() + "；录取类型：" + k.getAdmissionType() + " 】";
                res.add(mid);
            }
        }
        return res;
    }

    public List<String> queryStudentByMajor(String majorName) {
        List<Kaizhong> list = kaizhongMapper.getStudentByMajor(majorName);
        List<Kaizhong> list2 = list.stream().sorted(Comparator.comparing(Kaizhong::getAdmissionCollege)).collect(Collectors.toList());
        List<String> res = new ArrayList<>();
        if (list2.size() > 0) {
            res.add(String.valueOf(list2.size()));
            for (Kaizhong k : list2) {
//                res.add(k.toString());
                String mid = "【 录取专业：" + k.getMajor() + "；录取大学：" + k.getAdmissionCollege() + "；姓名：" + k.getName() + " 】";
                res.add(mid);

            }
        }
        return res;
    }

}
