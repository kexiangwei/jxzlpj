package com.mycode.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一结果集
 * @author kexiangwei
 *
 * @param <T>
 */
@Getter
@Setter
public class JsonResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static JsonResult<Object> success(String msg, Object object){
        JsonResult<Object> result = new JsonResult<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static JsonResult<Object> success(Object object){
        return success(null,object);
    }

    public static JsonResult<Object> success(){
        return success(null);
    }

    public static JsonResult<Object> error(Integer code, String msg){
        JsonResult<Object> result = new JsonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static JsonResult<Object> error(String msg){
        return error(500,msg);
    }

    public static JsonResult<Object> error(){
        return error(null);
    }
}
