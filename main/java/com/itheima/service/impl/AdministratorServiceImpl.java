package com.itheima.service.impl;
import com.itheima.domain.Administrator;
import com.itheima.mapper.AdministratorMapper;
import com.itheima.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *用户接口实现类
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    //注入userMapper
    @Autowired
    private AdministratorMapper administratorMapper;
    //通过User的用户账号和用户密码查询用户信息
    @Override
    public Administrator login(Administrator administrator) {
        return administratorMapper.login(administrator);
    }
}
