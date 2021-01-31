package com.sse.kaizhong.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sse.kaizhong.Const;
import com.sse.kaizhong.bean.UserAccessTable;
import com.sse.kaizhong.mapper.UserAccessTableMapper;
import com.sse.kaizhong.service.IpRecordService;
import com.sse.kaizhong.uitl.IpUtil;
import com.sse.kaizhong.uitl.SimpleHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @name: IpRecordServiceImp
 * @author: xiangyf
 * @create: 2019-10-27 07:37
 * @description:
 */
@Service
public class IpRecordServiceImpl implements IpRecordService {
    private static final Logger logger  = LoggerFactory.getLogger(IpRecordService.class);

    @Autowired
    private UserAccessTableMapper ipMapper;


    @Override
    public Boolean execute(String ip, String keyWord, List<String> deviceInfo) {
        //新获取ip对应物理地址接口
        String addressById = IpUtil.getAddressById(ip);
        String beautyAddress = IpUtil.addressAnalysis(addressById);
//        String url = Const.BASE_IP_URL+"?ip="+ip;
//        String res = SimpleHttpUtil.get(url);
        logger.info("ip对应的物理地址:{}",beautyAddress);

        if (!StringUtils.isEmpty(addressById)){
            String ipRealAddress = beautyAddress;
            String addressJson = addressById;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String accessTime = df.format(new Date());
            String createdAt = accessTime;
            String userAgent = null;
            String os = null;
            String device = null;
            if (deviceInfo.size() == 3) {
                userAgent = deviceInfo.get(0);
                os = deviceInfo.get(1);
                device = deviceInfo.get(2);
            }
            return ipMapper.insert(ip, ipRealAddress, addressJson, accessTime, keyWord, createdAt, "", userAgent, device, os);
        }
        return false;
    }

    public List<String> getLately(){
        List<UserAccessTable> list = ipMapper.selectLately();
        List<String> ret = new ArrayList<>();
        list.forEach(val -> {
            String ip = val.getIp();
            String keyWord = val.getKeyWord();
            String address = val.getIpRealAddress();
            //处理时间时有一个点需要注意，如果数据库字段类型为Date类型，java代码转换时会有问题，但是如果时间是可读，类似:2019-10-28 22:00:50,数据库该字段可以存为varchar
            String time = val.getCreatedAt();
//            String[] addressArr = address.split(",");
//            List<String> addressList = new ArrayList<>();
//            for (String s:addressArr){
//                if (!s.isEmpty()){
//                    addressList.add(s);
//                }
//            }
//            String realAddress = addressList.stream().collect(Collectors.joining("-"));
            String realAddress = address;
            String device = val.getDevice();
            String os = val.getOs();
            String possiblePerson = val.getPossiblePersonName();
            String res = "【关键字:" + keyWord + ",查询时间:" + time + ",访问ip:" + ip + ",ip对应的物理地址:" + realAddress + ",访问设备:" + device + ",设备系统:" + os + ",可能访问人:"+possiblePerson+
            "】";
            ret.add(res);
        });
        return ret;
    }

    public List<String> getAll(){
        List<UserAccessTable> list = ipMapper.selectAll();
        List<String> ret = new ArrayList<>();
        list.forEach(val -> {
            String ip = val.getIp();
            String keyWord = val.getKeyWord();
            String address = val.getIpRealAddress();
            String time = val.getCreatedAt();
//            String[] addressArr = address.split(",");
//            List<String> addressList = new ArrayList<>();
//            for (String s:addressArr){
//                if (!s.isEmpty()){
//                    addressList.add(s);
//                }
//            }
//            String realAddress = addressList.stream().collect(Collectors.joining("-"));
            String realAddress = address;
            String device = val.getDevice();
            String os = val.getOs();
            String possiblePerson = val.getPossiblePersonName();
            String res = "【关键字:" + keyWord + ",查询时间:" + time + ",访问ip:" + ip + ",ip对应的物理地址:" + realAddress + ",访问设备:" + device + ",设备系统:" + os + ",可能访问人:"+possiblePerson+
                    "】";
            ret.add(res);
        });
        return ret;
    }
}

