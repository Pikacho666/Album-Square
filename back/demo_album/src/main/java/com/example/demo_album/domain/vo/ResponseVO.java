package com.example.demo_album.domain.vo;

import com.example.demo_album.domain.Enum.ResponseCodeEnum;
import lombok.Data;

@Data
public class ResponseVO<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseVO() {}

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(ResponseCodeEnum code, T data) {
        this.code = code.getCode();
        this.message = code.getMsg();
        this.data = data;
    }

    /**
     * 返回成功
     * @param message 信息
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> ok(String message){
        return new ResponseVO<>(ResponseCodeEnum.CODE_200.getCode(), message);
    }

    /**
     * 返回成功
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> ok(T data){
        return new ResponseVO<>(ResponseCodeEnum.CODE_200, data);
    }

    /**
     * 返回成功
     * @param message 信息
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> ok(String message, T data){
        return new ResponseVO<>(ResponseCodeEnum.CODE_200.getCode(), message, data);
    }

    /**
     * 返回失败
     * @param message 信息
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> fail(String message){
        return new ResponseVO<>(ResponseCodeEnum.CODE_500.getCode(), message);
    }

    /**
     * 返回失败
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> fail(T data){
        return new ResponseVO<>(ResponseCodeEnum.CODE_500, data);
    }

    /**
     * 返回失败
     * @param message 信息
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResponseVO<T> fail(String message, T data){
        return new ResponseVO<>(ResponseCodeEnum.CODE_500.getCode(), message, data);
    }
}
