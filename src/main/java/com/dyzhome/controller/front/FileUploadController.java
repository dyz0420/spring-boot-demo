package com.dyzhome.controller.front;

import cn.hutool.core.util.StrUtil;
import com.dyzhome.common.constant.SystemConstant;
import com.dyzhome.common.result.Result;
import com.dyzhome.common.util.FileUtil;
import com.dyzhome.model.dto.UploadDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dyz
 */
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/upload")
public class FileUploadController {

    @Resource
    private FileUtil fileUtil;

    @ApiOperation("文件上传")
    @PostMapping
    public Result uploadFile(UploadDTO param) {
        String newName = null;
        if (StrUtil.equalsIgnoreCase(param.getType(), SystemConstant.VIDEO_UPLOAD)) {
            newName = fileUtil.uploadVideo(param.getFile());
        } else if (StrUtil.equalsIgnoreCase(param.getType(), SystemConstant.AVATAR_UPLOAD)) {
            newName = fileUtil.uploadAvatar(param.getFile());
        } else if (StrUtil.equalsIgnoreCase(param.getType(), SystemConstant.COVER_UPLOAD)) {
            newName = fileUtil.uploadCover(param.getFile());
        }
        return Result.success(newName);
    }
}
