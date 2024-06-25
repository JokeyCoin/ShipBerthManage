package com.itheima.service;
import com.itheima.domain.Administrator;

/**
 *用户接口
 */
public interface AdministratorService {
    //通过User的用户账号和用户密码查询用户信息
    Administrator login(Administrator administrator);
}
