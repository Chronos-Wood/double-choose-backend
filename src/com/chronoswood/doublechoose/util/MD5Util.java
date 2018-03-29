package com.chronoswood.doublechoose.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

public class MD5Util {
    private static final String salt = "hasodifhsoifhosidfh";

    public static String md5(String src) {
        if (StringUtils.hasText(src)) {
            return DigestUtils.md5DigestAsHex(src.getBytes());
        }
        return null;
    }

    public static String inputPassFormPass(String inputPass) {
        if (inputPass == null) return null;
        return DigestUtils.md5DigestAsHex((salt + inputPass).getBytes());
    }

    public static String formPass2DBPass(String formPass, String randomSalt) {
        if (formPass == null || randomSalt == null) return null;
        return DigestUtils.md5DigestAsHex((randomSalt + formPass).getBytes());
    }

    /**
     * 把明文密码经过两次md5加密成数据库中的密文串
     * @param pass 密码
     * @param dbSalt 盐值
     * @return 加密串
     */
    public static String inputPass2DBPass(String pass, String dbSalt) {
        if (StringUtils.hasText(pass) && StringUtils.hasText(dbSalt)) {
            return formPass2DBPass(inputPassFormPass(pass), dbSalt);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(inputPass2DBPass("123456", "c8bff869c20841fa8ada46510461d841"));
        //System.out.println(inputPassFormPass("123456"));
        System.out.println(formPass2DBPass("def5e35d3d2a9e985e5bbb6b02465fd5", "c8bff869c20841fa8ada46510461d841"));
    }
}
