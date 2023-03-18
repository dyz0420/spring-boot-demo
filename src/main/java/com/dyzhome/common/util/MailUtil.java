package com.dyzhome.common.util;

import cn.hutool.core.util.RandomUtil;

/**
 * 邮件发送工具类
 * @author Dyz
 */
public class MailUtil {

    public static String sendEmail(String email){
        String code = RandomUtil.randomNumbers(6);
        cn.hutool.extra.mail.MailUtil.send(email, "微视频验证码", "<div style=\"border-radius:10px;margin:0 auto;padding:30px;width:400px;height:400px;background-color:#eee;\">\n" +
                "    <h2 style=\"text-align: center;padding-bottom: 20px;\">微视频验证码</h2>\n" +
                "    <div style=\"font-size: 17px;padding-left: 8%;padding-bottom: 30px;\">尊敬的用户 您好: </div>\n" +
                "    <div style=\"font-size: 17px;padding-left: 8%;\">您的验证码为：<span style=\"color: rgb(58, 127, 255);\">" + code + "</span> </div>\n" +
                "    <div style=\"font-size: 17px;padding-left: 8%;padding-top: 30px;\">有效期十分钟，不区分大小写。 </div>\n" +
                "</div>", true);
        return code;
    }
}
