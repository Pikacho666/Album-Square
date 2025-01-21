package com.example.demo_album.domain.dao;

import com.example.demo_album.domain.dto.LikesDTO;
import com.example.demo_album.domain.dto.ModifyUserDTO;
import com.example.demo_album.domain.dto.UserDTO;
import com.example.demo_album.domain.vo.LikesVO;
import com.example.demo_album.domain.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT COUNT(*) from user where account = #{account};")
    Integer getUserCountByAccount(String account);

    @Select("SELECT COUNT(*) from user where account = #{account} and password = #{password};")
    Integer userLogin(UserDTO userDTO);

    @Select("SELECT * from user where account = #{account};")
    UserVO getUserByAccount(String account);

    @Select("SELECT * from user where id = #{id};")
    UserVO getUserById(Integer id);

    @Insert("INSERT INTO user (userName, userAvatar, account, password, isAdmin) " +
            "VALUES (#{userName}, #{userAvatar}, #{account}, #{password}, #{isAdmin})")
    Integer insertUser(UserVO user);

    @Insert("INSERT INTO likes (photoId, userId) " +
            "VALUES (#{photoId}, #{userId})")
    Integer insertLikes(LikesDTO likesDTO);

    @Delete("delete from likes where id = #{id}")
    Integer cancleLike(Integer id);

    @Select("select * from likes where photoId = #{photoId}")
    List<LikesVO> getAllLikes(Integer photoId);

    @Update("UPDATE user\n" +
            "    SET userAvatar = #{userAvatar},\n" +
            "        password = #{password},\n" +
            "        userName = #{userName}\n" +
            "    WHERE id = #{id}")
    int modeifyUserInfo(ModifyUserDTO user);
}
