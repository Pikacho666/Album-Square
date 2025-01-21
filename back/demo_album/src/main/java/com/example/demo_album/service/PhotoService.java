package com.example.demo_album.service;

import com.example.demo_album.domain.dao.CommentMapper;
import com.example.demo_album.domain.dao.PhotoMapper;
import com.example.demo_album.domain.dao.UserMapper;
import com.example.demo_album.domain.dto.PhotoDTO;
import com.example.demo_album.domain.vo.PhotoVO;
import com.example.demo_album.domain.vo.ResponseVO;
import com.example.demo_album.domain.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoMapper photoMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;

    /**
     * 添加相片
     */
    public ResponseVO insertPhoto(PhotoDTO photoDto) {
        int count = photoMapper.insertPhoto(photoDto);
        if (count == 1) {
            return ResponseVO.ok("添加成功！");
        } else if (count == 0) {
            return ResponseVO.fail("添加失败！");
        }
        return ResponseVO.fail("未知错误！");
    }

    /**
     * 返回所有图片
     */
    public ResponseVO getAllPhotos() {
        List<PhotoVO> list = new ArrayList<>();
        list.addAll(photoMapper.getAllPhotos());
        if (list.isEmpty()) {
            return ResponseVO.fail("暂无数据");
        } else {
            list.forEach(photoVO -> {
                UserVO userVO = userMapper.getUserById(photoVO.getOwner());
                photoVO.setOwnerAvatar(userVO.getUserAvatar());
                photoVO.setOwnerName(userVO.getUserName());
                photoVO.setCommentNum(commentMapper.getPhotoComments(photoVO.getId()).size());
                photoVO.setLikesNum(userMapper.getAllLikes(photoVO.getId()).size());
            });
            return ResponseVO.ok("获取成功", list);
        }
    }

    /**
     * 返回个人所有图片
     */
    public ResponseVO getUserAllPhotos(Integer id) {
        List<PhotoVO> list = new ArrayList<>();
        list.addAll(photoMapper.getPhotoByUserId(id));
        list.forEach(photoVO -> {
            UserVO userVO = userMapper.getUserById(photoVO.getOwner());
            photoVO.setOwnerAvatar(userVO.getUserAvatar());
            photoVO.setOwnerName(userVO.getUserName());
            photoVO.setCommentNum(commentMapper.getPhotoComments(photoVO.getId()).size());
            photoVO.setLikesNum(userMapper.getAllLikes(photoVO.getId()).size());
        });
        return ResponseVO.ok("获取成功", list);
    }

    public ResponseVO deletePhoto(Integer photoId) {
        Integer count = photoMapper.deletePhoto(photoId);
        if (count == 1) {
            return ResponseVO.ok("删除成功~");
        }
        return ResponseVO.fail("删除失败");
    }
}
