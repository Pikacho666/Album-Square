package com.example.demo_album.controller;

import com.example.demo_album.domain.dto.CommentDTO;
import com.example.demo_album.domain.vo.ResponseVO;
import com.example.demo_album.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/insertComment")
    private ResponseVO insertComment(@RequestBody CommentDTO commentDTO){
        return commentService.insertComment(commentDTO);
    }
    @GetMapping("/getComment")
    private ResponseVO getComment(@RequestParam Integer id){
        return commentService.getComment(id);
    }
    @GetMapping("/deleteComment")
    private ResponseVO deleteComment(@RequestParam Integer id){
        return commentService.deleteComment(id);
    }
}
