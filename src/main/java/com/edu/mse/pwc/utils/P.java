package com.edu.mse.pwc.utils;

import com.edu.mse.pwc.dtos.UserDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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

    public static UserDto getLoggedUserFormRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");


        return null;
    }

    public static void clearUserSensitiveData(UserDto user) {
        user.setPassword("*");
        user.setUsername("*");
    }
}
