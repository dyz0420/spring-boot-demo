package com.dyzhome.controller;

import com.dyzhome.common.exception.SysException;
import com.dyzhome.common.result.R;
import com.dyzhome.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dyz
 */
@RestController
public class TestController {

    @GetMapping("/test/{id}")
    public Result test(@PathVariable("id") Integer id) {
        if (id.equals(1)) {
            throw new SysException(R.FAIL_DONE);
        }
        return Result.success();
    }

}
