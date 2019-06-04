package com.sse.kaizhong.service.impl;

import com.sse.kaizhong.service.KaizhongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KaizhongServiceImpl implements KaizhongService {
    private static final Logger logger = LoggerFactory.getLogger("QueryService.class");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> queryStudentByName(String name) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from kaizhongs where `姓名` = " + "'" + name + "'");
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString());
        List<String> list = new ArrayList<>();
//        logger.info(mapList.toString());
        if (mapList.size() > 0) {
            int num = mapList.size();
            list.add(String.valueOf(num));
//            String check = mapList.get(0).get("姓名").toString();
//            logger.info(check);
            mapList.get(0).forEach((k, v) -> {
                list.add(k + ": " + v);
            });

        }
        return list;
    }

    public List<String> queryStudentByCollege(String collegeName) {
//        String collegeName = "重庆师范大学";
        StringBuffer sql = new StringBuffer();
        sql.append("select `录取大学`,`专业`,`姓名`,`录取类型` from kaizhongs where `录取大学` like " + "'%" + collegeName + "%'");
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());
        List<String> resList = new ArrayList<>();
        if (queryForList.size() > 0) {
            int size = queryForList.size();
            resList.add(String.valueOf(size));
            queryForList.forEach(val -> {
                List<String> sentence = new ArrayList<>();
                val.forEach((k, v) -> {
                    String str = k + ": " + v;
                    sentence.add(str);
                });
                resList.add(sentence.toString());
            });
        }
        return resList;
    }

    public List<String> queryStudentByMajor(String majorName) {

        StringBuffer sql = new StringBuffer();
        sql.append("select `录取大学`,`专业`,`姓名` from kaizhongs where `专业` like " + "'%" + majorName + "%'");
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql.toString());
        List<String> resList = new ArrayList<>();
        if (queryForList.size() > 0) {
            int num = queryForList.size();
            resList.add(String.valueOf(num));
            queryForList.forEach(val -> {
                List<String> sent = new ArrayList<>();
                val.forEach((k, v) -> {
                    String str = k + ": " + v;
                    sent.add(str);
                });
                resList.add(sent.toString());
            });
        }
        return resList;
    }
}
