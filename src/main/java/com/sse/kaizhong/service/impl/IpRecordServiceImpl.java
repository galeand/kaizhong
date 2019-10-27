package com.sse.kaizhong.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sse.kaizhong.Const;
import com.sse.kaizhong.mapper.IpMapper;
import com.sse.kaizhong.service.IpRecordService;
import com.sse.kaizhong.uitl.SimpleHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @name: IpRecordServiceImp
 * @author: xiangyf
 * @create: 2019-10-27 07:37
 * @description:
 */
@Service
public class IpRecordServiceImpl implements IpRecordService {
    @Autowired
    private IpMapper ipMapper;

    @Override
    public Boolean execute(String ip, String keyWord) {
        String url = Const.BASE_IP_URL+"?ip="+ip;
        String res = SimpleHttpUtil.get(url);
        String ret = null;
        if (!StringUtils.isEmpty(res)) {
            JSONObject jsonObject = JSON.parseObject(res);
            if (jsonObject.containsKey("data")){
                ret = jsonObject.getString("data");
            }
        }
        if (!StringUtils.isEmpty(ret)){
            JSONObject retJSON = JSON.parseObject(ret);
            String ipRealAddress = retJSON.getString("country")+","+retJSON.getString("area")+","
                    +retJSON.getString("regison")+","+retJSON.getString("city")+retJSON.getString("county");
            String addressJson = ret;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String accessTime = df.format(new Date());
            String createdAt = accessTime;

            return ipMapper.insert("", ip, ipRealAddress, addressJson, accessTime, keyWord, createdAt, "");
        }
        return false;
    }
}
