package com.ryan.hprose.service;

import com.ryan.hprose.service.dto.DemoDTO;

/**
 * Demo Service
 */
public interface DemoService {
    public DemoDTO selectDemoById(long id);
}
