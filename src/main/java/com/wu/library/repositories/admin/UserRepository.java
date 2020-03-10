package com.wu.library.repositories.admin;

import com.wu.library.models.Role;
import com.wu.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public User selectUserNoRole(String username){
       String sql = "select * from tbl_user where username=?";
       return jdbcTemplate.queryForObject(sql, new Object[] {username}, new UserMapper());

    }

    public int deleteUserRole (String _id)
    {
        String sql = "DELETE from user_roles WHERE user_id ="+"'"+_id+"'";
        return jdbcTemplate.update(sql);
    }
    public int delete (String _id)
    {
        String sql = "DELETE from tbl_user WHERE user_id ="+"'"+_id+"'";
        return jdbcTemplate.update(sql);
    }


    public User loadUserByUsername(String username){
        User user = this.selectUserNoRole(username);
        user.setRoles(this.getRoleByUserId(user.getId()));
        return user;
    }
//    private User rsToUser(ResultSet _rs) throws SQLException{
//        User user = new User();
//        user.setId(resultSet.getString("user_id"));
//        user.setUsername(resultSet.getString("username"));
//        user.setPassword(resultSet.getString("password"));
//        user.setStatus(resultSet.getString("status"));
//        user.setProfileImg(resultSet.getString("profile_img"));
//        return user;
//    }
//    return jdbcTemplate.query(query, params, rs -> {
//        List<Db> dbList = new ArrayList<>();
//        while(rs.next())
//        {
//            dbList.add(rsToDb(rs));
//        }
//        return dbList;
//    });
//    public List<User> getUser(String _status){
//        String query = String.format("Select * from tbl_user where status =:_status");
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("status", _status);
//        return jdbcTemplate.query(query,params, rs ->{
//            List<User> userList = new ArrayList<>();
//            while ((rs.next()))
//            {
//                userList.add(rsToUser(rs));
//            }
//            return userList;
//        });
//    }

    public List<User> getAllUser(){
        String sql ="Select * from tbl_user";
       return jdbcTemplate.query(sql,new UserMapper());
    }

    public List<User> getAllUserUpdate(String _userId){
        String sql ="Select * from tbl_user where not user_id ="+"'"+_userId+"'" ;
        return jdbcTemplate.query(sql,new UserMapper());
    }

    public List<Role> getRoleByUserId(String id){
        String sql ="SELECT tbl_role.role, tbl_role.id from tbl_role inner join user_roles on tbl_role.id = user_roles.id where user_roles.user_id=?";
        return jdbcTemplate.query(sql,new Object[]{id},new RoleMapper());
    }

    private static final class UserMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getString("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            user.setName(resultSet.getString("name"));
            user.setAddress(resultSet.getString("address"));
            user.setStatus(resultSet.getBoolean("status"));
            user.setProfileImg(resultSet.getString("profile_img"));
            return user;
        }
    }

    private static final class RoleMapper implements RowMapper<Role>{
        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setRole(resultSet.getString("role"));
            role.setId(resultSet.getString("id"));
            return role;
        }
    }

//    public int insert (Book book)
//    {
//        String sql = "INSERT INTO tbl_book VALUES ('"+book.getId()+"','"+book.getAuthor()+"','"+book.getIsbn()+"','"+book.getPage()+"','"+book.getPersonal()+"','"+book.getPublisher()+"','true','"+book.getTitle()+"','"+book.getYear()+"','"+book.getCategory_id()+"') ";
//        return jdbcTemplate.update(sql);
//    }

    public int insertUser (User user)
    {
        //String sql ="INSERT INTO tbl_user VALUES ('"+user.getId()+"','"+user.getPassword()+"','"+user.getProfileImg()+"',true,"+user.getUsername()+"','"+user.getName()+"','"+user.getPhone()+"','"+user.getAddress()+"')";
        System.out.printf("=====repostitory====" + user.getProfileImg());
        String sql = String.format("INSERT INTO tbl_user (user_id, address, name, password, phone, profile_img,status, username) VALUES (:id, :address, :name, :password, :phone , :profile_img, :status, :username) ");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("address", user.getAddress());
        params.addValue("name", user.getName());
        params.addValue("password", user.getPassword());
        params.addValue("phone", user.getPhone());
        params.addValue("profile_img",user.getProfileImg());
        params.addValue("status",true);
        params.addValue("username", user.getUsername());
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public int updateUser (User user, String userId)
    {
        String sql = String.format("Update tbl_user SET address = :address, name= :name, phone= :phone, profile_img =:profile_img,status= :status, username= :username where user_id= :id");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        params.addValue("address", user.getAddress());
        params.addValue("name", user.getName());
//        params.addValue("password", user.getPassword());
        params.addValue("phone", user.getPhone());
        params.addValue("profile_img",user.getProfileImg());
        params.addValue("status",true);
        params.addValue("username", user.getUsername());
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public int updateUserNoPicture (User user, String userId)
    {
        String sql = String.format("Update tbl_user SET address = :address, name= :name, phone= :phone, status= :status, username= :username where user_id= :id");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", userId);
        params.addValue("address", user.getAddress());
        params.addValue("name", user.getName());
//        params.addValue("password", user.getPassword());
        params.addValue("phone", user.getPhone());
//        params.addValue("profile_img",user.getProfileImg());
        params.addValue("status",true);
        params.addValue("username", user.getUsername());
        return namedParameterJdbcTemplate.update(sql,params);
    }
    public int insertUserRole(String _roleId, String _userId){
//        String sql="INSERT INTO  user_roles VALUES ('"+user_id+"','"+role+"')";
        String sql = String.format("INSERT INTO user_roles (user_id, id) VALUES (:userId, :roleId)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", _userId);
        params.addValue("roleId", _roleId);
        return namedParameterJdbcTemplate.update(sql,params);
    }
    public int updateUserRole(String _userId, String _roleId){
        String sql = String.format("UPDATE user_roles SET id =:roleId WHERE user_id =:userId");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId",_roleId);
        params.addValue("userId", _userId);
        return namedParameterJdbcTemplate.update(sql,params);
    }

}
