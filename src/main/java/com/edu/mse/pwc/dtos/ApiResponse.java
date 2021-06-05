package com.edu.mse.pwc.dtos;

import lombok.Data;

/**
 * Class that hold's result data from processing after REST call. This result
 * can contains status, message and List with objects as result.
 *
 * @param <T> App entity
 * @author petar ivanov
 */
@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private Object result;
    private long count;
    private long editorId;
//    private boolean create;
//    private boolean edit;
//    private boolean delete;


    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int status, String message, Object result, long count) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.count = count;
    }

}
