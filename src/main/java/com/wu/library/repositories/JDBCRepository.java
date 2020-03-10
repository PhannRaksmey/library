//package com.wu.library.repositories;
//
//import com.wu.library.models.Employee;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class JDBCRepository  {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public JDBCRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveEmployee(Employee e){
//        System.out.println(e.getId()+e.getName()+e.getSalary());
//        String query= " insert into Employee values('"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";
//        return jdbcTemplate.update(query);
//    }
//}
