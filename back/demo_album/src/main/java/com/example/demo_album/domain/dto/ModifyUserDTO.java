package com.example.demo_album.domain.dto;

import lombok.Data;

@Data
public class ModifyUserDTO {
    private Integer id;
    private String userAvatar;
    private String password;
    private String userName;
}
