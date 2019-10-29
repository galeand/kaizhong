package com.sse.kaizhong.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @name: IpUtil
 * @author: xiangyf
 * @create: 2019-10-26 13:58
 * @description:
 */
public class IpUtil {
    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    public static String getIpAddr(HttpServletRequest request){
        String ipAddr = null;
        try{
            ipAddr = request.getHeader("x-forwarded-for");
            if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)){
                ipAddr = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)){
                ipAddr = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)){
                ipAddr = request.getRemoteAddr();
                if (ipAddr.equals("127.0.0.1")){
                    //根据网卡取本机配置的ip
                    InetAddress inet = null;
                    inet = InetAddress.getLocalHost();
                    ipAddr = inet.getHostAddress();
                }
            }
            //// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddr != null && ipAddr.length() > 15){
                if (ipAddr.indexOf(",") > 0){
                    ipAddr = ipAddr.substring(0, ipAddr.indexOf(","));
                }
            }
        }catch (Exception e){
            ipAddr = "";
        }
        return ipAddr;
    }

    public static List<String> getDeviceAndOS(HttpServletRequest request){
        String header = request.getHeader("User-Agent");
        logger.info("用户请求头:{}",header);
        String userInfo = header.substring(header.indexOf("(")+1, header.indexOf(")"));
        String[] userInfoArr = userInfo.split(";");
        String os = null;
        String device = null;
        if (userInfoArr.length >= 3){
            //手机访问
            os = userInfoArr[1] + "\t\t" + userInfoArr[0];
            //这里获取手机设备型号时有问题，还没有太好的解决方法
            device = userInfoArr[2];
        }else if (userInfoArr.length == 2){
            //浏览器访问
            os = userInfoArr[1] + "\t\t" + userInfoArr[0];
            device = "Brower";
        }
        List<String> list = new ArrayList<>();
        list.add(header);
        list.add(os);
        list.add(device);
        return list;
    }
}
