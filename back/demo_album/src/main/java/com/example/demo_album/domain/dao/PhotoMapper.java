package com.example.demo_album.domain.dao;

import com.example.demo_album.domain.dto.PhotoDTO;
import com.example.demo_album.domain.vo.PhotoVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PhotoMapper {
    /**
     * 增添照片
     * */
    @Insert("INSERT INTO photos (imgBase64,createTime, owner, title) " +
            "VALUES (#{imgBase64}, #{createTime}, #{owner}, #{title})")
    Integer insertPhoto(PhotoDTO photoDto);
    /**
     * 依据用户id返回照片
     * */
    @Select("SELECT * from photos where owner = #{id}")
    List<PhotoVO> getPhotoByUserId(Integer id);
    /**
     * 依据title返回照片
     * */
    @Select("SELECT * from photos where id = #{title}")
    PhotoVO getPhotoByTitle(String title);
    /**
     * 返回全部照片
     * */
    @Select("SELECT * from photos")
    List<PhotoVO> getAllPhotos();

    @Delete("DELETE FROM photos WHERE id = #{photoId} ")
    Integer deletePhoto(Integer photoId);
}
