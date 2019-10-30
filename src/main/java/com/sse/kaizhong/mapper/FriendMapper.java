package com.sse.kaizhong.mapper;

import com.sse.kaizhong.bean.Friend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Results(id = "friendMap", value = {@Result(column = "大学名称", property = "collegeName"),
            @Result(column = "专业名称", property = "major"),
            @Result(column = "姓名", property = "name"),
            @Result(column = "同学关系", property = "relationship"),
            @Result(column = "研究生学校", property = "graduateSchool"),
            @Result(column = "研究生院", property = "graduateSchool_academy"),
            @Result(column = "研究生专业", property = "graduateSchool_major"),
            @Result(column = "MoreInfo", property = "moreInfo"),
            @Result(column = "id", property = "id")
    })
    @Select("SELECT * FROM friend_college")
    List<Friend> getAllFriends();

    @ResultMap(value = "friendMap")
    @Select("SELECT `姓名`,MoreInfo FROM friend_college")
    List<Friend> getFriends();

    @ResultMap(value = "friendMap")
    @Select("SELECT * FROM friend_college where `姓名` like CONCAT('%',#{name},'%')")
    List<Friend> getOneFriend(String name);

    @ResultMap(value = "friendMap")
    @Insert("INSERT INTO `kaizhong`.`friend_college`(`大学名称`, `专业名称`, `姓名`, `同学关系`, `研究生学校`, `研究生院`, `研究生专业`, `MoreInfo`) VALUES (#{collegeName}, #{major}, #{name}, #{relationship}, #{graduateSchool}, #{graduateSchool_academy}, #{graduateSchool_major}, #{moreInfo})")
    Boolean insertFriend(@Param("collegeName") String collegeName, @Param("major") String major, @Param("name") String name, @Param("relationship") String relationship, @Param("graduateSchool") String graduateSchool, @Param("graduateSchool_academy") String graduateSchool_academy, @Param("graduateSchool_major") String graduateSchool_major, @Param("moreInfo") String moreInfo);
}
