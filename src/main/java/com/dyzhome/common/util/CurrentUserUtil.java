package com.dyzhome.common.util;

/**
 * 获取当前登录用户工具类
 * @author Dyz
 */
public class CurrentUserUtil {
        private CurrentUserUtil() {
        }

        private static final ThreadLocal<Long> LOCAL = new ThreadLocal<>();

        public static void putUserId(Long userId) {
            LOCAL.set(userId);
        }

        public static Long getCurrentUser() {
            return LOCAL.get();
        }

        public static void remove() {
            LOCAL.remove();
        }

}
