package com.sse.kaizhong.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @name: IpMapper
 * @author: xiangyf
 * @create: 2019-10-27 07:43
 * @description:
 */
@Mapper
public interface IpMapper {

    @Results(id = "IdMap", value = {@Result(column = "id", property = "id"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "ip_real_address", property = "ipRealAddress"),
            @Result(column = "address_json", property = "addressJson"),
            @Result(column = "access_time", property = "accessTime"),
            @Result(column = "key_word", property = "keyWord"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "possible_person_name", property = "possiblePersonName")
    })
    @Insert("INSERT INTO `kaizhong`.`user_access`(`ip`, `ip_real_address`, `address_json`, `access_time`, `key_word`, `created_at`, `possible_person_name`) " +
            "VALUES (#{ip}, #{ipRealAddress}, #{addressJson}, #{accessTime}, #{keyWord}, #{createdAt}, #{possiblePersonName})")
    Boolean insert(@Param("id") String id,
                         @Param("ip") String ip,
                         @Param("ipRealAddress") String ipRealAddress,
                         @Param("addressJson") String addressJson,
                         @Param("accessTime") String accessTime,
                         @Param("keyWord") String keyWord,
                         @Param("createdAt") String createdAt,
                         @Param("possiblePersonName") String possiblePersonName);

}
