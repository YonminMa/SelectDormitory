package com.pku.dormitory.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 统一返回结果的类
 *
 * @author Yonmin
 * @date 2022/11/24 16:31
 */
public class Result {

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("success");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        result.setMessage("error");
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(ResultCode.ERROR);
        result.setMessage(message);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        for (String key : map.keySet()) {
            this.data(key, map.get(key));
        }
        return this;
    }

}
