package com.dbz.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 10979
 * @description:
 * @date 2022/4/122:02
 */
@Data
public class Result {
    private boolean success;

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<String, Object>();

    private Result(){}

    public  static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }
    public  static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public Result success(boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
