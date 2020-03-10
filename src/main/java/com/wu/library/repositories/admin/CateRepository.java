package com.wu.library.repositories.admin;

import com.wu.library.models.Category;
import com.wu.library.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CateRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CateRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Category> getAll(){
        String sql = "Select * from tbl_category where status=true";
       return jdbcTemplate.query(sql,new CateMapper());
    }

    private static final class CateMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getString("id"));
            category.setName(resultSet.getString("name"));
            category.setStatus(resultSet.getBoolean("status"));
            return category;
        }
    }

    public int update (Category _category, String _id){
        String sql = "UPDATE tbl_category SET name = :name where id= :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", _category.getName());
        params.addValue("id",_id);
        return namedParameterJdbcTemplate.update(sql,params);
    }

    public int insert(Category category){
        String sql = "INSERT INTO tbl_category VALUES ('"+category.getId()+"','"+category.getName()+"','true') ";
        return jdbcTemplate.update(sql);
    }

    public int delete(String _id) throws DataIntegrityViolationException //Exception relationship
    {
        String sql = "DELETE from tbl_category WHERE id ="+"'"+_id+"'";
//
//        MapSqlParameterSource param = new MapSqlParameterSource();
//        param.addValue("name",_name);
        return jdbcTemplate.update(sql);
    }

    public List<Category> selectCategoryById(String _id)
    {
        String sql = "SELECT * from tbl_category WHERE id ="+"'"+_id+"'";
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        parameterSource.addValue("id",_id);
        return jdbcTemplate.query(sql,new CateMapper());

//        return namedParameterJdbcTemplate.query(sql,parameterSource,rs->{
//            List<Category> categories = new ArrayList<>();
//            while (rs.next())
//            {
//                categories.add(rsToCategory(rs));
//            }
//            return categories;
//        });
    }
    public Category rsToCategory(ResultSet _rs) throws SQLException
    {
        Category category = new Category();
        category.setName(_rs.getString("name"));
        category.setId(_rs.getString("id"));
        category.setStatus(_rs.getBoolean("status"));
        return category;
    }

//    public List<User> test (){
//        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
//
//        String query = "SELECT u.user_id, u.password,r.role, u.username\n" +
//                "FROM user_roles ur\n" +
//                "\tINNER JOIN tbl_user u \n" +
//                "\t\ton u.user_id = ur.user_id\n" +
//                "\tINNER JOIN tbl_role r\n" +
//                "\t\ton r.id = ur.id \n" +
//                "WHERE u.username= 'admin' and u.status= 'true'";
//        return this.jdbcTemplate.query(query,mapper);
//    }


}
