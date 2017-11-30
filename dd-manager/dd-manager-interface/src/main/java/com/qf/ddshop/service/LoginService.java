package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.MessageResult;

public interface LoginService {
    MessageResult userLogin(String username, String password);
}
