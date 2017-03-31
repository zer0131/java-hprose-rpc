package com.ryan.hprose.dao;

import com.ryan.hprose.dao.mapper.DemoMapper;
import com.ryan.hprose.dao.pojo.Demo;

/**
 * Demo DAO Impl
 */
public class DemoDAOImpl implements DemoDAO {

    private DemoMapper demoMapper;

    @Override
    public Demo selectDemoById(long id) {
        return demoMapper.selectDemoById(id);
    }

    public void setDemoMapper(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }
}
