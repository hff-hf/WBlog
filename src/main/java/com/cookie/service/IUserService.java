package com.cookie.service;

import com.cookie.vo.ResponseVo;

/**
 * @auther hff
 * @time 2021/5/2 - 13:10
 **/
public interface IUserService {

    ResponseVo selectUserByUsernameAndPassword(String username,String password);
}
