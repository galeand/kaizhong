package com.sse.kaizhong.service.impl;

import com.sse.kaizhong.bean.Friend;
import com.sse.kaizhong.mapper.FriendMapper;
import com.sse.kaizhong.service.FriendService;
import com.sse.kaizhong.service.KaizhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;

    @Override
    public Boolean insertFriend(Friend friend) {
        System.out.println("111"+friend.toString());
        Boolean isSuccess = friendMapper.insertFriend(
                friend.getCollegeName(),
                friend.getMajor(),
                friend.getName(),
                friend.getRelationship(),
                friend.getGraduateSchool(),
                friend.getGraduateSchool_academy(),
                friend.getGraduateSchool_major(),
                friend.getMoreInfo());
        if (isSuccess) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllFriends() {
        List<Friend> friendList = friendMapper.getAllFriends();
        List<String> res = new ArrayList<>();
        if (friendList.size() > 0) {
            res.add(String.valueOf(friendList.size()));
            for (Friend f : friendList) {
                String mid = "【 大学名称：" + f.getCollegeName() + ";专业名称：" + f.getMajor() + ";姓名：" + f.getName() + "；同学关系：" + f.getRelationship() + ";研究生学校:" + f.getGraduateSchool() + ";研究生专业：" + f.getGraduateSchool_major() +
                        ";研究生学院：" + f.getGraduateSchool_academy() + "；更多信息：" + f.getMoreInfo() + " 】";
                res.add(mid);
            }
        }
        return res;
    }

    @Override
    public List<String> getFriends() {
        List<Friend> friendList = friendMapper.getFriends();
        List<String> res = new ArrayList<>();
        if (friendList.size() > 0) {
            res.add(String.valueOf(friendList.size()));
            for (Friend f : friendList) {
                String mid = "【姓名：" + f.getName() + "；更多信息：" + f.getMoreInfo() + " 】";
                res.add(mid);
            }
        }
        return res;
    }

    @Override
    public List<String> getOneFriend(String name) { //getOneFriend
        List<Friend> friendList = friendMapper.getOneFriend(name);
        List<String> res = new ArrayList<>();
        if (friendList.size() > 0) {
            res.add(String.valueOf(friendList.size()));
            for (Friend f : friendList) {
                String mid = "【 大学名称：" + f.getCollegeName() + ";专业名称：" + f.getMajor() + ";姓名：" + f.getName() + "；同学关系：" + f.getRelationship() + ";研究生学校:" + f.getGraduateSchool() + ";研究生专业：" + f.getGraduateSchool_major() +
                        ";研究生学院：" + f.getGraduateSchool_academy() + "；更多信息：" + f.getMoreInfo() + " 】";
                res.add(mid);
            }
        }
        return res;
    }

}
