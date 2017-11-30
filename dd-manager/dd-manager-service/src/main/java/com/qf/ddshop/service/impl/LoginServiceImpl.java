package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.dto.MessageResult;
import com.qf.ddshop.common.jedis.JedisClient;
import com.qf.ddshop.common.util.CookieUtils;
import com.qf.ddshop.common.util.JsonUtils;
import com.qf.ddshop.dao.TbUserMapper;
import com.qf.ddshop.pojo.po.TbUser;
import com.qf.ddshop.pojo.po.TbUserExample;
import com.qf.ddshop.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;


@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public MessageResult userLogin(String username, String password) {
        MessageResult mr = new MessageResult();

        try {
            //创建模板
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            //执行查询
            List<TbUser> list = tbUserMapper.selectByExample(example);
            if (list== null || list.size()==0){
                 //登陆失败
                mr.setSuccess(false);
                mr.setMessage("用户名不存在");
                return mr;
            }

            //用户名已存在,从数据库来 的
            TbUser tbUser = list.get(0);
            String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
            if (!md5Pass.equals(tbUser.getPassword())){
                mr.setSuccess(false);
                mr.setMessage("用户名或密码错误");
                return  mr;
            }

//用户名密码都正确
            String token = UUID.randomUUID().toString();
//            写入到Redis,设置过期时间
            //把登录信息写入到Redis
            tbUser.setPassword(null);
            jedisClient.set("TT_TOKEN:"+token, JsonUtils.objectToJson(tbUser));
            jedisClient.expire("TT_TOKEN:"+token,1800);
            //正确情况下，返回MessageResult
            mr.setSuccess(true);
            mr.setMessage("登陆成功");
            mr.setData(token);


        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  mr;
    }
}
