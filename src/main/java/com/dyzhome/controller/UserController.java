package com.dyzhome.controller;

import cn.hutool.core.util.ObjectUtil;
import com.dyzhome.common.exception.SysException;
import com.dyzhome.common.result.R;
import com.dyzhome.common.result.Result;
import com.dyzhome.model.entity.User;
import com.dyzhome.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dyz
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/test/{id}")
    public Result test(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        if (ObjectUtil.isNull(user)) {
            throw new SysException(R.FAIL_DONE);
        }
        return Result.success(user);
    }

}
