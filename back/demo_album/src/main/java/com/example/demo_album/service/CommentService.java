package com.example.demo_album.service;

import com.example.demo_album.domain.dao.CommentMapper;
import com.example.demo_album.domain.dto.CommentDTO;
import com.example.demo_album.domain.vo.CommentVO;
import com.example.demo_album.domain.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    public ResponseVO insertComment(CommentDTO comment){
        Integer count = commentMapper.insertComment(comment);
        if(count != 1){
            return ResponseVO.fail("评论失败~");
        }
        return ResponseVO.ok("评论成功~");
    }
    public ResponseVO deleteComment(Integer id){
        Integer count = commentMapper.deleteComment(id);
        if(count != 1){
            return ResponseVO.fail("评论删除失败~");
        }
        return ResponseVO.ok("评论删除成功~");
    }
    public ResponseVO getComment(Integer id){
        List<CommentVO> list = commentMapper.getPhotoComments(id);
        if(list.isEmpty()){
            return ResponseVO.fail("暂无评论",list);
        }
        return ResponseVO.ok("评论成功！",list);
    }
}
