package com.wu.library.repositories.admin;

import com.wu.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {

    JdbcTemplate jdbcTemplate;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long countBook(){
        String sql= "SELECT COUNT (id) FROM tbl_book";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql,params, Long.class);
    }
//    public void dis(){
//        String sql ="select pg_terminate_backend(pid) from pg_stat_activity where datname='wu-library'";
//
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
//        namedParameterJdbcTemplate.queryForObject(sql,parameterSource,Long.class);
//    }


    public Long countBorrow(){
        String sql= "SELECT COUNT (id) FROM tbl_book WHERE status = false";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql,params, Long.class);
    }

    public Long countAvailableBook(){
        String sql= "SELECT COUNT (id) FROM tbl_book WHERE status = true";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql,params, Long.class);
    }
    public Long countMember(){
        String sql= "SELECT COUNT (id) FROM tbl_member";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql,params, Long.class);
    }

    public Long countUser(){
        String sql= "SELECT COUNT (user_id) FROM tbl_user";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql,params, Long.class);
    }


    public List<Book> getAll(){
        String sql= "select * from tbl_book";
        return jdbcTemplate.query(sql,new BookMapper());
    }

    public List<Book> getBooksByCateId(String _cate_id)
    {
        String sql = "select * from tbl_book where category_id="+"'"+_cate_id+"'";
        return jdbcTemplate.query(sql,new BookMapper());
    }

    public List<Book> getBooksByDate(String _startDate, String _endDate)
    {
        String sql = "select * from tbl_book where create_date >"+"'"+_startDate+"'" +"And create_date <"+"'"+_endDate+"'";
        System.out.println(sql);
        return jdbcTemplate.query(sql,new BookMapper());
    }

    public List<Book> getById(String _id)
    {
        String sql = "select * from tbl_book where id="+"'"+_id+"'";
        return jdbcTemplate.query(sql,new BookMapper());
    }


    private static final class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();

            book.setId(resultSet.getString("id"));
            book.setAuthor(resultSet.getString("author"));
//            book.getCategory().setName(resultSet.getString("cate_name"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setPage(resultSet.getInt("page"));
            book.setPersonal(resultSet.getString("personal"));
            book.setPublisher(resultSet.getString("publisher"));
            book.setTitle(resultSet.getString("title"));
            book.setYear(resultSet.getInt("year"));
            //book.getCategory().setCate_id(resultSet.getString("category_cate_id"));
            book.setStatus(resultSet.getBoolean("status"));
            //book.setCate_id(resultSet.getString("cate_id"));
            return book;
        }
    }

    public int insert (Book book)
    {
//        String sql = "INSERT INTO tbl_book VALUES ('"+book.getId()+"','"+book.getAuthor()+"','"+book.getIsbn()+"',"+book.getPage()+",'"+book.getPersonal()+"','"+book.getPublisher()+"','true','"+book.getTitle()+"','"+book.getYear()+"','"+book.getCategory_id()+"') ";
//        return jdbcTemplate.update(sql);
        String sql = "INSERT INTO tbl_book (id, author, category_id, isbn, page, personal, publisher,status,title,year) VALUES (:id, :author, :cateId, :isbn, :page, :personal, :publisher, :status, :title, :year) ";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", book.getId());
        mapSqlParameterSource.addValue("author", book.getAuthor());
        mapSqlParameterSource.addValue("cateId", book.getCategory_id());
        mapSqlParameterSource.addValue("isbn", book.getIsbn());
        mapSqlParameterSource.addValue("page", book.getPage());
        mapSqlParameterSource.addValue("personal", book.getPersonal());
        mapSqlParameterSource.addValue("publisher",book.getPublisher());
        mapSqlParameterSource.addValue("status",true);
        mapSqlParameterSource.addValue("title",book.getTitle());
        mapSqlParameterSource.addValue("year",book.getYear());

        return namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
    }

    public int delete (String _id)
    {
        String sql = "DELETE from tbl_book WHERE id ="+"'"+_id+"'";
        return jdbcTemplate.update(sql);
    }

    public int update(Book book, String _id)
    {
        String sql = "UPDATE tbl_book SET author = :author, isbn= :isbn, page= :page, personal= :personal, publisher= :publisher, title= :title, year = :year where id= :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", _id);
        params.addValue("author",book.getAuthor());
        params.addValue("isbn", book.getIsbn());
        params.addValue("page", book.getPage());
        params.addValue("personal", book.getPersonal());
        params.addValue("publisher",book.getPublisher());
        params.addValue("title", book.getTitle());
        params.addValue("year", book.getYear());
        return namedParameterJdbcTemplate.update(sql,params);
    }


}
