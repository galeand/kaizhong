package com.sse.kaizhong;

import org.apache.ibatis.annotations.Mapper;

public interface MyMapper<T>{// extends //Mapper<T>,MysqlMapper {
    //我之前看的那个springcloud-vue的工程，是这么写然后来操作数据库的
    //但是springcloud-vue这个工程使用的mybatis是导入只“tk.mybatis.mapper.common.Mapper”这个jar包的
    //但是我现在在这个工程里面写的mybatis，通过注解来操作数据库，是导入自“org.apache.ibatis.annotations.Mapper”这个jar包的，注意一下
}
