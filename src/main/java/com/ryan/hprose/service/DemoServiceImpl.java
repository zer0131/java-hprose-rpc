package com.ryan.hprose.service;

import com.ryan.hprose.service.dto.DemoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo Service Impl
 */
public class DemoServiceImpl implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public DemoDTO selectDemoById(long id) {
        return null;
    }
}
