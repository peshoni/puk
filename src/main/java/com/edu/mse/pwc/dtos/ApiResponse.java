package com.edu.mse.pwc.dtos;

/**
 * Class that hold's result data from processing after REST call. This result
 * can contains status, message and List with objects as result.
 *
 * @param <T> App entity
 * @author petar ivanov
 */
public class ApiResponse<T> {
    private int status;
    private String message;
    private Object result;
    private long count;

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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
