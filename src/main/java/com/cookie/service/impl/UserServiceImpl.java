package com.cookie.service.impl;

import com.cookie.enums.ResponseEnum;
import com.cookie.mapper.UserMapper;
import com.cookie.pojo.User;
import com.cookie.service.IUserService;
import com.cookie.vo.ResponseVo;
import com.cookie.vo.SessionUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther hff
 * @time 2021/5/2 - 13:21
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseVo selectUserByUsernameAndPassword(String username,String password) {
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if(user == null) {
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        SessionUser sessionUser = new SessionUser();
        BeanUtils.copyProperties(user,sessionUser);
        return ResponseVo.success(ResponseEnum.SUCCESS,sessionUser);
    }
}
