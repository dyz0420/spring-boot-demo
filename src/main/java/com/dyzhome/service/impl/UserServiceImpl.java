package com.dyzhome.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyzhome.mapper.UserMapper;
import com.dyzhome.model.entity.User;
import com.dyzhome.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Dyz
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
}
