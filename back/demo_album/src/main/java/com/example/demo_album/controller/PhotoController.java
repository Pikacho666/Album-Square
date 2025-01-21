package com.example.demo_album.controller;

import com.example.demo_album.domain.dto.PhotoDTO;
import com.example.demo_album.domain.vo.ResponseVO;
import com.example.demo_album.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @PostMapping("/insertPhoto")
    private ResponseVO insertPhoto(@RequestBody PhotoDTO photoDto){
        return photoService.insertPhoto(photoDto);
    }
    @GetMapping("/getAllPhotos")
    private ResponseVO getAllPhotos(){
        return photoService.getAllPhotos();
    }
    @GetMapping("/getUserAllPhotos")
    private ResponseVO getUserAllPhotos(@RequestParam Integer userID){
        return photoService.getUserAllPhotos(userID);
    }
    @GetMapping("/deletePhoto")
    private ResponseVO deletePhoto(@RequestParam Integer photoId){
        return photoService.deletePhoto(photoId);
    }
}
