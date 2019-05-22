package com.ecxls.integral.util.regex;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <p>ClassName: RegexUtil<p>
 * <p>Description: 提供邮箱、手机、银行账户正则校验<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年10月26日 下午2:12:23
 */
public class RegexUtil {
    private static final Pattern EMAIL_PATTERN        = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^(13[0-9]|15[0-9]|16[0-9]|17[0-9]|14[7|5]|18[0-9])\\d{8}$");

    private static final Pattern BANK_ACCOUNT_PATTERN = Pattern.compile("^(\\d{16}|\\d{19})$");

    private static final Pattern AMT_PATTERN          = Pattern.compile("^[1-9]\\d*$");

    public static boolean isEmail(String email) {
        return StringUtils.isBlank(email) ? false : EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isPhone(String mobilePhone) {
        return StringUtils.isBlank(mobilePhone) ? false : MOBILE_PHONE_PATTERN.matcher(mobilePhone).matches();
    }

    public static boolean isBankAccount(String bankAccount) {
        return StringUtils.isBlank(bankAccount) ? false : BANK_ACCOUNT_PATTERN.matcher(bankAccount).matches();
    }

    public static boolean isAmt(String amt) {
        return StringUtils.isBlank(amt) ? false : AMT_PATTERN.matcher(amt).matches();
    }

    /** 
     * 将以分为单位的金额字符串转换为以元为单位的金额字符串 
     * 0.00 <- 0 
     * 0.01 <- 1 
     * 532.3 <- 53230 
     * "" -> 0.00 
     * null -> 0.00 
     * @param s 以分为单位的金额字符串 
     * @return 以元为单位的金额字符串 
     */
    public static String amtAddPoint(double amt) {
        if (amt == 0) {
            return "0.00";
        }
        amt = amt * 100;
        try {
            DecimalFormat format = new DecimalFormat("0.00");
            return format.format(amt / 100);
        } catch (Exception e) {
            return "0.00";
        }
    }
}
