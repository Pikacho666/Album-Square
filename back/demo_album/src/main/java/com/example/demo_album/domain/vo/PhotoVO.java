package com.example.demo_album.domain.vo;

import lombok.Data;

@Data
public class PhotoVO {
    private String imgBase64;
    private Integer id;
    private String createTime;
    private Integer owner;
    private String title;
    private Integer commentNum;
    private Integer likesNum;
    private String ownerAvatar;
    private String ownerName;
}
