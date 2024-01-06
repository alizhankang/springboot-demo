package com.lizhankang.springbootdemo.exception;

/**
 * 业务异常基类
 */
public class BizException extends Exception{
    public String code;
    public String msg;
}
