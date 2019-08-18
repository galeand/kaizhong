package com.sse.kaizhong.mapper;

import com.sse.kaizhong.bean.Kaizhong;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KaizhongMapper {

    @Results(id = "kzMap", value = {@Result(column = "录取时间", property = "admissionTime"),
            @Result(column = "姓名", property = "name"),
            @Result(column = "录取", property = "admission"),
            @Result(column = "a", property = "a"),
            @Result(column = "录取批", property = "admissionBatch"),
            @Result(column = "1", property = "one"),
            @Result(column = "录取类型", property = "admissionType"),
            @Result(column = "0", property = "zero"),
            @Result(column = "是否定向", property = "directionOrNot"),
            @Result(column = "编号", property = "identifier"),
            @Result(column = "录取大学", property = "admissionCollege"),
            @Result(column = "代号", property = "codeName"),
            @Result(column = "专业", property = "major"),
            @Result(column = "0129", property = "x1"),
            @Result(column = "id", property = "id"),
            @Result(column = "毕业高中", property = "seniorSchool"),
            @Result(column = "a1", property = "a1"),
            @Result(column = "班次", property = "classNumber")})
    @Select("SELECT * FROM kaizhongs WHERE `姓名` LIKE CONCAT('%',#{name},'%')")
    List<Kaizhong> getStudentByName(String name);

    @ResultMap(value = "kzMap")
    @Select("select `录取大学`,`专业`,`姓名`,`录取类型` from kaizhongs where `录取大学` like concat('%',#{college},'%')")
    List<Kaizhong> getStudentByCollege(String college);

    @ResultMap(value = "kzMap")
    @Select("select `录取大学`,`专业`,`姓名` from kaizhongs where `专业` like concat('%',#{major},'%')")
    List<Kaizhong> getStudentByMajor(String major);




}
