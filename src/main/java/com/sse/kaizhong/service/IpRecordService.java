package com.sse.kaizhong.service;

import java.util.List;

/**
 * @name: IpRecordService
 * @author: xiangyf
 * @create: 2019-10-27 07:37
 * @description:
 */
public interface IpRecordService {

    Boolean execute(String ip, String keyWord, List<String> deviceInfo);

}
