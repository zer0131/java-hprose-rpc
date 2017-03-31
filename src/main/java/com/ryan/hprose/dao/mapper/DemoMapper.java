package com.ryan.hprose.dao.mapper;

import com.ryan.hprose.dao.pojo.Demo;
import org.apache.ibatis.annotations.Param;

/**
 * Demo Mapper
 */
public interface DemoMapper {
    public Demo selectDemoById(@Param("id") long id);
}
