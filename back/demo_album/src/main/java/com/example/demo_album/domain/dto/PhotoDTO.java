package com.example.demo_album.domain.dto;

import lombok.Data;

@Data
public class PhotoDTO {
    private String imgBase64;
    private String createTime;
    private Integer owner;
    private String title;
    private Integer commentNum;
    private Integer likesNum;
}
