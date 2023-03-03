package com.dyzhome.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一结果返回
 *
 * @author Dyz
 */
@Data
@Accessors(chain = true)
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    private Result() {
    }

    public static Result success() {
        Result res = new Result();
        res.setSuccess(R.SUCCESSFUL_OPERATION.getSuccess());
        res.setCode(R.SUCCESSFUL_OPERATION.getCode());
        res.setMsg(R.SUCCESSFUL_OPERATION.getMessage());
        return res;
    }

    public static Result success(Object data) {
        Result res = new Result();
        res.setSuccess(R.SUCCESSFUL_OPERATION.getSuccess());
        res.setCode(R.SUCCESSFUL_OPERATION.getCode());
        res.setMsg(R.SUCCESSFUL_OPERATION.getMessage());
        res.setData(data);
        return res;
    }

    public static Result success(R r) {
        Result res = new Result();
        res.setSuccess(r.getSuccess());
        res.setCode(r.getCode());
        res.setMsg(r.getMessage());
        return res;
    }

    public static Result success(R r, Object data) {
        Result res = new Result();
        res.setSuccess(r.getSuccess());
        res.setCode(r.getCode());
        res.setMsg(r.getMessage());
        res.setData(data);
        return res;
    }

    public static Result error(R r) {
        Result res = new Result();
        res.setSuccess(r.getSuccess());
        res.setCode(r.getCode());
        res.setMsg(r.getMessage());
        return res;
    }

    public static Result error(R r, Object data) {
        Result res = new Result();
        res.setSuccess(r.getSuccess());
        res.setCode(r.getCode());
        res.setMsg(r.getMessage());
        res.setData(data);
        return res;
    }
}
