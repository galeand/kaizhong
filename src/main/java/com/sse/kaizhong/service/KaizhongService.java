package com.sse.kaizhong.service;

import java.util.List;

public interface KaizhongService {
    List<String> queryStudentByName(String name);

    List<String> queryStudentByCollege(String collegeName);

    List<String> queryStudentByMajor(String majorName);
}
