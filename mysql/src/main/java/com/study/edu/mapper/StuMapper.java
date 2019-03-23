package com.study.edu.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.edu.entity.Stu;
@Mapper
public interface StuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stu record);

    int insertSelective(Stu record);

    Stu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stu record);

    int updateByPrimaryKey(Stu record);
}