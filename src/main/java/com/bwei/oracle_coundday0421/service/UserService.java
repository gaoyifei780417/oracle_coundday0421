package com.bwei.oracle_coundday0421.service;

import cn.hutool.crypto.SecureUtil;
import com.bwei.oracle_coundday0421.bean.TUser;
import com.bwei.oracle_coundday0421.bean.TUserExample;
import com.bwei.oracle_coundday0421.dao.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @User feifei
 * @ClassName UserService  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 16:05  时间
 * @Version 1.0 版本
 */
@Service
public class UserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Transactional
    public int zhuce(TUser tUser) {
        return tUserMapper.insert(tUser);
    }

    public String checkPhone(String phone){
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andPhoneEqualTo(phone);
        //select * from t_user where phone=#{phone}
        List<TUser> tUsers = tUserMapper.selectByExample(tUserExample);
        //如果返回的集合中的数据大于零则当前数据存在
        if(tUsers!=null && tUsers.size()>0){
           //已存在
            return "0";
        }else{
            return "1";
        }
    }

    public TUser login(TUser tUser) {
        tUser.setPassword(SecureUtil.md5(tUser.getPassword()));
        return tUserMapper.login(tUser);
    }

    public TUser checkPasswordAndUserName(String loginName, String password) {
        TUserExample tUserExample = new TUserExample();
        //给密码加密
        String md5 = SecureUtil.md5(password);

        tUserExample.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(md5);
        List<TUser> tUsers=tUserMapper.selectByExample(tUserExample);
        if(null!=tUsers && tUsers.size()>0){
            return tUsers.get(0);
        }
        return null;
    }
}
