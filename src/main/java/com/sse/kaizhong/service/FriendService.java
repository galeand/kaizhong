package com.sse.kaizhong.service;

import com.sse.kaizhong.bean.Friend;

import java.util.List;

public interface FriendService {
    List<String> getAllFriends();

    List<String> getFriends();

    List<String> getOneFriend(String name);

    Boolean insertFriend(Friend friend);
}
