package com.sse.kaizhong.service.impl;

import com.sse.kaizhong.bean.Kaizhong;
import com.sse.kaizhong.mapper.KaizhongMapper;
import com.sse.kaizhong.service.KaizhongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("service02")
public class KaizhongServiceImpl02 implements KaizhongService {
    private static final Logger logger = LoggerFactory.getLogger(KaizhongServiceImpl02.class);

    @Autowired
    private KaizhongMapper kaizhongMapper;


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
                res.add(k.toString());
            }
        }
        return res;
    }

    public List<String> queryStudentByMajor(String majorName) {
        List<Kaizhong> list = kaizhongMapper.getStudentByMajor(majorName);
        List<String> res = new ArrayList<>();
        if (list.size() > 0) {
            res.add(String.valueOf(list.size()));
            for (Kaizhong k : list) {
                res.add(k.toString());
            }
        }
        return res;
    }

}
