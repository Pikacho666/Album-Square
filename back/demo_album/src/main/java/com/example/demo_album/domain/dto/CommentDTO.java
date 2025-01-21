package com.example.demo_album.domain.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private String ownerName;
    private String ownerAvatar;
    private String commentTime;
    private Integer photoId;
    private Integer ownerId;
}
