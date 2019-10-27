package com.sse.kaizhong.uitl;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @name: IpUtil
 * @author: xiangyf
 * @create: 2019-10-26 13:58
 * @description:
 */
public class IpUtil {

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
}
