package com.example.demo_album.domain.dao;

import com.example.demo_album.domain.dto.CommentDTO;
import com.example.demo_album.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    /**
     * 返回指定的照片下评论
     * */
    @Select("select * from comments where photoId = #{id}")
    List<CommentVO> getPhotoComments(Integer id);
    /**
     * 添加评论
     * */
    @Insert("INSERT INTO comments (content, ownerName, ownerAvatar, commentTime, photoId , ownerId) " +
            "VALUES (#{content}, #{ownerName}, #{ownerAvatar}, #{commentTime}, #{photoId} , #{ownerId})")
    Integer insertComment(CommentDTO comment);
    /**
     * 删除评论
     * */
    @Delete("DELETE FROM comments WHERE id = #{id}")
    Integer deleteComment(Integer id);
}
