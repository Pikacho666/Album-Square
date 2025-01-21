package com.example.demo_album.service;

import com.example.demo_album.domain.dao.UserMapper;
import com.example.demo_album.domain.dto.LikesDTO;
import com.example.demo_album.domain.dto.ModifyUserDTO;
import com.example.demo_album.domain.dto.UserDTO;
import com.example.demo_album.domain.vo.LikesVO;
import com.example.demo_album.domain.vo.ResponseVO;
import com.example.demo_album.domain.vo.UserVO;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     */
    public ResponseVO login(UserDTO userDTO) {
        Integer count = userMapper.userLogin(userDTO);
        if (count == 0) {
            return ResponseVO.fail("账号或密码错误");
        }
        return ResponseVO.ok("登录成功！", userMapper.getUserByAccount(userDTO.getAccount()));
    }

    /**
     * 注册
     */
    public ResponseVO regist(UserVO user) {
        Integer count = userMapper.getUserCountByAccount(user.getAccount());
        if (count >= 1) {
            return ResponseVO.fail("账号已被注册");
        }
        if (user.getIsAdmin() == null) {
            user.setIsAdmin(0);
        }
        count = userMapper.insertUser(user);
        if (count == 1) {
            return ResponseVO.ok("注册成功");
        } else if (count == 0) {
            return ResponseVO.fail("注册失败");
        }
        return ResponseVO.fail("未知错误");
    }

    public ResponseVO getUserInfo(Integer id) {
        UserVO userVO = userMapper.getUserById(id);
        return ResponseVO.ok("获取成功", userVO);
    }

    public ResponseVO likes(LikesDTO likesDTO) {
        boolean isLike = false;
        int likesId = 0;
        //查询用户是否已点赞
        for (LikesVO allLike : userMapper.getAllLikes(likesDTO.getPhotoId())) {
            if (allLike.getUserId() == likesDTO.getUserId()) {
                isLike = true;
                likesId = allLike.getId();
                break;
            }
        }
        //已经点赞再次请求就是取消
        if (isLike) {
            return cancleLikes(likesId);
        }
        int count = userMapper.insertLikes(likesDTO);
        if (count != 1) {
            return ResponseVO.fail("点赞失败~");
        }
        return ResponseVO.ok("点赞成功！");
    }

    public ResponseVO cancleLikes(Integer id) {
        int count = userMapper.cancleLike(id);
        if (count != 1) {
            return ResponseVO.fail("取消点赞失败~");
        }
        return ResponseVO.ok("取消点赞成功！");
    }

    public ResponseVO queryLikes(Integer photoId) {
        List<Integer> list = new ArrayList<>();
        for (LikesVO allLike : userMapper.getAllLikes(photoId)) {
            list.add(allLike.getUserId());
        }
        return ResponseVO.ok("获取成功", list);
    }

    public ResponseVO modeifyUserInfo(ModifyUserDTO user) {
        int count = userMapper.modeifyUserInfo(user);
        if(count == 1){
            UserVO userVO = userMapper.getUserById(user.getId());
            return ResponseVO.ok("修改成功！",userVO);
        }
        return ResponseVO.fail("修改失败~");
    }
}
