//package com.wu.library.repositories.admin;
//
//import com.wu.library.models.Role;
//import com.wu.library.models.User;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserDetail {
//
//    @Select("select user_id, username,password,status,profile_img"+
//        "from tbl_user where username=#{username}")
//    @Results({
//            @Result(property = "id", column = "user_id"),
//            @Result(property = "profileImg",column = "profile_img"),
//            @Result(property = "roles", column = "id", many =@Many(select ="getRolesByUserId"))
//    })
//    User loadUserByUsername(@Param("username") String username);
//
//    @Select("select * " + "from tbl_role tr inner join user_roles tur on tr.id = tur.id"+ "where tur.id=#{id}")
//    List<Role> getRolesByUserId(String id);
//}
