package com.sse.kaizhong.service;

import java.util.List;

public interface FriendService {
    List<String> getAllFriends();

    List<String> getFriends();

    List<String> getOneFriend(String name);
}
