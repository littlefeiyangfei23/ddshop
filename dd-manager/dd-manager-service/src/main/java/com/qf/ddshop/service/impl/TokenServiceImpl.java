package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.MessageResult;
import com.qf.ddshop.common.jedis.JedisClient;
import com.qf.ddshop.common.util.JsonUtils;
import com.qf.ddshop.pojo.po.TbUser;
import com.qf.ddshop.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult getUserByToken(String tokenId) {
        MessageResult mr = new MessageResult();

        try {
            //根据token到redis取用户信息
            String userJson = jedisClient.get("TT_TOKEN:" + tokenId);
            //StringUtils来自commons.lang3
            //userJson为空时
            if (StringUtils.isBlank(userJson)){
                //证明为NULL
                mr.setSuccess(false);
                mr.setMessage("登录已经过期");
                return  mr;
            }

//            充值半小时,        redis集群中存在改用户名且有效
            //取到用户信息，
            jedisClient.expire("TT_TOKEN:"+tokenId,1800);

            TbUser tbUser = JsonUtils.jsonToPojo(userJson, TbUser.class);

            //返回messageResult
            mr.setSuccess(true);
            mr.setMessage("欢迎回来");
            mr.setData(tbUser);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mr;
    }
}
