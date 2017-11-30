package com.qf.ddshop.service;

import com.qf.ddshop.common.dto.MessageResult;

public interface TokenService {
    MessageResult getUserByToken(String tokenId);
}
