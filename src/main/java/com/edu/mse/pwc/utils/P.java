package com.edu.mse.pwc.utils;

import org.springframework.stereotype.Component;

/**
 * @author petar ivanov
 */
@Component
public class P {
    /**
     * Private constructor for utility classes
     */
    private P() {
    }

    /**
     * Method for display message with class and row number info
     */
    public static void syso(Object object) {


        String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        System.out.println(getTimeStampFromMilliseconds(System.currentTimeMillis()) + " : " + className + "."
                + methodName + "():" + lineNumber + ":" + object);

    }

    public static java.sql.Timestamp getTimeStampFromMilliseconds(Long milliseconds) {
        return new java.sql.Timestamp(milliseconds);
    }
}
