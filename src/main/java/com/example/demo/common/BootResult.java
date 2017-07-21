package com.example.demo.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 * 
 * @author Toppy
 *
 */
public class BootResult implements Serializable {
    private static final long serialVersionUID = -5440424819480013927L;
    public static final int OK = 200;
    public static final int WARN = 203;
    public static final int ERROR = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int SERVER_ERROR = 500;

    private int status;
    private Map<String, Object[]> msgs = new HashMap<String, Object[]>(3);
    private Object data;

    public BootResult(int status, Map<String, Object[]> msgs, Object data) {
        this.status = status;
        this.msgs = msgs;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object[]> getMsgs() {
        return msgs;
    }

    public void setMsgs(Map<String, Object[]> msgs) {
        this.msgs = msgs;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArmyResponse [status=" + status + ", msgs=" + msgs + ", data=" + data + "]";
    }

    /**
     * 验证是否成功
     * 
     * @param response
     * @return
     */
    public static final boolean isOK(BootResult response) {
        return response.getStatus() == OK;
    }

    /**
     * 是否验证成功
     * 
     * @param response
     * @return
     */
    public static final boolean isNotOK(BootResult response) {
        return !isOK(response);
    }

    /**
     * 无权限响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult serverError(String key) {
        return serverError(key, null);
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult serverError(String key, Object[] params) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(SERVER_ERROR, msg, null);
    }

    /**
     * 未认证
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult unauthorized(String key) {
        return unauthorized(key, null);
    }

    /**
     * 未认证
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult unauthorized(String key, Object[] params) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(UNAUTHORIZED, msg, null);
    }

    /**
     * 无权限响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult forbidden(String key) {
        return forbidden(key, null);
    }

    /**
     * 无权限响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult forbidden(String key, Object[] params) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(FORBIDDEN, msg, null);
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final BootResult error(String key) {
        return error(key, null, null);
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @return
     */
    public static final BootResult error(String key, Object... params) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(ERROR, msg, null);
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @param content 返回结果
     * @return
     */
    public static final BootResult error(String key, Object[] params, Object content) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(ERROR, msg, content);
    }

    /**
     * 返回内容
     * 
     * @param content
     * @return
     */
    public static final BootResult of(Object content) {
        return of(OK, new HashMap<String, Object[]>(), content);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息Key
     * @return
     */
    public static final BootResult ok(String key) {
        return ok(key, null, null);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息Key
     * @return
     */
    public static final BootResult ok(String key, Object content) {
        return ok(key, null, content);
    }

    /**
     * 添加返回参数设置
     * 
     * @param key
     * @param params
     * @return
     */
    public static final BootResult ok(String key, Object[] params) {
        return ok(key, params, null);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息key
     * @param params 消息参数
     * @return
     */
    public static final BootResult ok(String key, Object[] params, Object content) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(OK, msg, content);
    }

    /**
     * 返回成功响应结果
     * 
     * @return
     */
    public static final BootResult OK() {
        return of(OK, null, null);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息key
     * @return
     */
    public static final BootResult warn(String key) {
        return warn(key, null, null);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息key
     * @param params 消息参数
     * @return
     */
    public static final BootResult warn(String key, Object content) {
        return warn(key, null, content);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @param content 返回结果
     * @return
     */
    public static final BootResult warn(String key, Object[] params, Object content) {
        Map<String, Object[]> msg = new HashMap<String, Object[]>();
        msg.put(key, params);
        return of(WARN, msg, content);
    }

    /**
     * 返回响应结果
     * 
     * @param status 状态
     * @param msg 消息Key和参数的MAP集合
     * @param content 内容
     * @return
     */
    public static final BootResult of(int status, Map<String, Object[]> msgs, Object content) {
        return new BootResult(status, msgs, content);
    }
}
