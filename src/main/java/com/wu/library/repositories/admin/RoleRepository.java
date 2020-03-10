package com.wu.library.repositories.admin;

import com.wu.library.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveRole(Role role){
        String query ="insert into tbl_role values ('"+role.getId()+"','"+role.getRole()+"')";
        return jdbcTemplate.update(query);


    }
}
