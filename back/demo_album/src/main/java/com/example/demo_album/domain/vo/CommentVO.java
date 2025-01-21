package com.example.demo_album.domain.vo;

import lombok.Data;

@Data
public class CommentVO {
    private Integer id;
    private String content;
    private String ownerName;
    private String ownerAvatar;
    private String commentTime;
    private Integer photoId;
    private Integer ownerId;
}
