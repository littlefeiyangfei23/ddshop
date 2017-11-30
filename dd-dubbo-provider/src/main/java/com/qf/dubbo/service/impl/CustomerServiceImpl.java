package com.qf.dubbo.service.impl;

import com.qf.dubbo.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public String say(String words) {
        return "say"+words;
    }
}
