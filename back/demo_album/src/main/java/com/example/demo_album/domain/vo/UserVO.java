package com.example.demo_album.domain.vo;

import lombok.Data;

@Data
public class UserVO {
    private String userName;
    private String userAvatar;
    private String account;
    private String password;
    private Integer isAdmin;
    private Integer id;
}
