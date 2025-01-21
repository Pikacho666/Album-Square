package com.example.demo_album.controller;

import com.example.demo_album.domain.dto.LikesDTO;
import com.example.demo_album.domain.dto.ModifyUserDTO;
import com.example.demo_album.domain.dto.UserDTO;
import com.example.demo_album.domain.vo.ResponseVO;
import com.example.demo_album.domain.vo.UserVO;
import com.example.demo_album.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    private ResponseVO login(@RequestBody UserDTO user){
        return userService.login(user);
    }
    @PostMapping("/regist")
    private ResponseVO regist(@RequestBody UserVO user){
        return userService.regist(user);
    }
    @GetMapping("/getUserInfo")
    private ResponseVO getUserInfo(@RequestParam Integer id){
        return userService.getUserInfo(id);
    }
    @PostMapping("/likes")
    private ResponseVO likes(@RequestBody LikesDTO likesDTO){
        return userService.likes(likesDTO);
    }
    @GetMapping("/queryLikes")
    private ResponseVO queryLikes(@RequestParam Integer photoId){
        return userService.queryLikes(photoId);
    }
    @PostMapping("/modeifyUserInfo")
    private ResponseVO modeifyUserInfo(@RequestBody ModifyUserDTO user){
        return userService.modeifyUserInfo(user);
    }
}
