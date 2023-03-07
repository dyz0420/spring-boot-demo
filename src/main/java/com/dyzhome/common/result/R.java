package com.dyzhome.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果枚举类
 * @author Dyz
 */
@AllArgsConstructor
@Getter
public enum R {
    //200-->可提示给用户的成功消息
    SUCCESS_LOGIN(true, 200, "登录成功"),
    SUCCESS_LOGOUT(true, 200, "您已退出登录"),
    SUCCESS_CHANGE_HEAD(true, 200, "头像更换成功"),
    SUCCESS_SENDMAIL(true, 200, "邮件已经成功发送至您的邮箱"),
    SUCCESS_REGISTER(true, 200, "注册成功，请登录"),
    SUCCESS_UPLOAD_VIDEO(true, 200, "视频上传成功！请等待管理员审核！"),
    SUCCESS_CHANGE_INFO(true, 200, "信息修改成功"),
    SUCCESS_OPTION(true, 200, "操作成功！"),
    SUCCESS_COMMENT(true, 200, "评论成功！"),
    SUCCESS_REPLY(true, 200, "回复成功！"),
    SUCCESS_DEL(true, 200, "删除成功！"),
    SUCCESS_CHANGE_PASS(true, 200, "密码修改成功，请重新登录。"),
    //250-->不用提示给用户的成功消息
    SUCCESSFUL_DONE(true, 250, "成功！"),

    //400-->可提示给用户的错误/失败消息
    NOT_LOGIN(false, 444, "您尚未登录，请先登录"),
    Login_TIMEOUT(false, 444, "登录超时，请重新登录"),
    NOT_PERMISSION(false, 446, "当前帐号为普通用户账号，您无权操作！请向管理员申请权限！"),
    NOTFOUND_ERROR(false, 445, "该资源不存在"),
    ERROR_EMAIL(false, 450, "请填写正确的邮箱！"),
    ERROR_RESET_PASS(false, 450, "重置密码出错，请重新验证身份！"),
    ACCOUNT_BANNED(false, 450, "该账号封禁中，请联系管理员！"),
    ERROR_ACCOUNT(false, 450, "用户名或密码错误"),
    ERROR_KEYCODE(false, 450, "验证码错误，请重试"),
    UNKNOWN_ERROR(false, 450, "系统异常，请联系管理员！"),
    FILE_UPLOAD_FAIL(false, 450, "文件上传失败！"),
    FAIL_CHANGE_HEAD(false, 450, "头像修改失败，请重试"),
    FAIL_CHANGE_INFO(false, 450, "信息修改失败，请重试"),
    FAIL_UPLOAD_VIDEO(false, 450, "视频上传失败，请重试"),
    KEYCODE_TIMEOUT(false, 450, "验证码过期，请刷新"),
    MAIL_CODE_TIMEOUT(false, 450, "验证码过期，请重新发送"),
    PASSWORD_NOT_MATCH(false, 450, "两次密码不一致，请重试"),
    REGISTER_FAIL(false, 450, "注册失败"),
    ERROR_PASSWORD(false, 450, "原密码错误，请重试"),
    OLD_EQUALS_NEW(false, 450, "新密码不能和旧密码相同哦~"),
    REPEAT_TYPE(false, 450, "当前分类已存在，不可重复添加！"),
    WARNING(false, 450, "您无权操作该账号(2310028336@qq.com)！"),
    FAIL_DONE(false, 450, "操作失败！"),
    FAIL_REPLY(false, 450, "回复失败！"),
    FAIL_DEL(false, 450, "删除失败！"),
    CANNOT_DEL_TYPE(false, 450, "当前所选分类存在视频与之绑定，不可删除！"),
    FAIL_COMMENT(false, 450, "评论失败！"),
    DATA_TOO_LONG(false, 450, "分类名不可超过6个字符！"),
    //450-->不提示的失败消息
    FAIL_OPERATION(false, 400, "失败！");

    private final Boolean success;
    private final Integer code;
    private final String message;
}
