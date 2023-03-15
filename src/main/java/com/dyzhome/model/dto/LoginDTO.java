package com.dyzhome.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Dyz
 */
@Data
@Accessors(chain = true)
@ApiModel("LoginVO")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮箱",required = true)
    @NotBlank(message = "请输入邮箱账号")
    @Email(message = "请输入正确的邮箱")
    private String email;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "请输入密码")
    private String password;

    @ApiModelProperty(value = "验证码",required = true)
    @NotBlank(message = "请输入验证码")
    private String validCode;
}
