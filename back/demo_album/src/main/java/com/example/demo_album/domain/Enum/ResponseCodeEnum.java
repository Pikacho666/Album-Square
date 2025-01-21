package com.example.demo_album.domain.Enum;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    CODE_200(200, "请求成功"),
    CODE_500(500, "操作失败"),
    CODE_404(404, "请求地址不存在");
    private Integer code;
    private String msg;
    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
