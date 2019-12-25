package com.zhd.ultimate.sociology.utils;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LogUtils {

    /**
     * common log
     */
    public static final Logger COMMON = LogManager.getLogger("common");

    /**
     * message log
     */
    public static final Logger SMS = LogManager.getLogger("sms");

    /**
     * error log
     */
    public static final Logger ERROR = LogManager.getLogger("error");

    /**
     * order log
     */
    public static final Logger ORDER = LogManager.getLogger("order");

    /**
     * push log
     */
    public static final Logger PUSH = LogManager.getLogger("push");

    /**
     * payment log
     */
    public static final Logger PAY = LogManager.getLogger("pay");

    /**
     * apollo log
     */
    public static final Logger APOLLO = LogManager.getLogger("apollo");

    private static final String SEPARATOR = " >> ";
    private static final String COLON = " : ";
    private static final String SEMICOLON = "; ";


    public static String createLog(String msg) {
        return new StringBuffer().append(Thread.currentThread().getName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getClassName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getMethodName())
                .append(SEPARATOR).append(msg).toString();
    }

    public static String createLog(String argName, Object arg) {
        return new StringBuffer().append(Thread.currentThread().getName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getClassName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getMethodName())
                .append(SEPARATOR).append(argName)
                .append(COLON).append(JSON.toJSONString(arg)).toString();
    }

    public static String createLog(String msg, String argName, Object arg) {
        return new StringBuffer().append(Thread.currentThread().getName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getClassName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getMethodName())
                .append(" >> Message : ").append(msg)
                .append(SEMICOLON).append(argName)
                .append(COLON).append(JSON.toJSONString(arg)).toString();
    }

    public static String createLog(String[] argNames, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(Thread.currentThread().getName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getClassName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getMethodName())
                .append(SEPARATOR);

        for (int i = 0; i < argNames.length; i++) {
            if (args.length <= i) {
                break;
            }
            sb.append(argNames[i]).append(" : ").append(JSON.toJSONString(args[i])).append("; ");
        }

        return sb.toString();
    }

    public static String createLog(String msg, String[] argNames, Object... args) {
        StringBuilder sb = new StringBuilder();
        new StringBuffer().append(Thread.currentThread().getName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getClassName())
                .append(SEPARATOR).append(Thread.currentThread().getStackTrace()[2].getMethodName())
                .append(" >> Message : ").append(msg)
                .append(SEMICOLON);
        for (int i = 0; i < argNames.length; i++) {
            if (args.length <= i) {
                break;
            }
            sb.append(argNames[i]).append(" : ").append(JSON.toJSONString(args[i])).append("; ");
        }
        return sb.toString();
    }

}
