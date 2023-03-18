package com.dyzhome.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Dyz
 */
@Data
@Accessors(chain = true)
@ApiModel("UploadDTO")
public class UploadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上传的文件")
    private MultipartFile file;

    @ApiModelProperty("上传类型 avatar/video/cover")
    private String type;
}
