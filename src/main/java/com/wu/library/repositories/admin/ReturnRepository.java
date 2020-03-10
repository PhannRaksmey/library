package com.wu.library.repositories.admin;

import com.wu.library.models.Book;
import com.wu.library.models.Member;
import com.wu.library.models.ReturnBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReturnRepository
{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public int returnBook(String _bookId){

        String sql = "UPDATE tbl_book SET status= true where id= :bookId";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("bookId", _bookId);
        return jdbcTemplate.update(sql,param);
    }

    public int insert(String _approverId, String _approver, String _bookId, String _memberId ){
        String sql = String.format("INSERT INTO tbl_return(book_id, member_id, approver, approver_id) VALUES(:bookId, :memberId, :approver, :approverId)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bookId",_bookId);
        params.addValue("memberId",_memberId);
        params.addValue("approver",_approver);
        params.addValue("approverId",_approverId);
        return jdbcTemplate.update(sql,params);
    }

    public List<ReturnBook> getAll()
    {
        String sql = String.format("select r.id,r.approver, r.book_id, r.member_id, r.return_date,me.id, me.major,me.year,me.section,me.carrer, me.name, me.sex, me.contact, bo.title, bo.id from tbl_return r\n" +
                "\tinner JOIN tbl_book bo on r.book_id = bo.id\n" +
                "\tinner join tbl_member me on r.member_id = me.id \n");

        return jdbcTemplate.query(sql, rs->{
            List<ReturnBook> returnBooks = new ArrayList<>();
            while (rs.next())
            {
                returnBooks.add(rsToReturn(rs));
            }
            return returnBooks;
        });
    }

    public int delete(int _id){
        String sql = "DELETE from tbl_return WHERE id =:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",_id);
        return jdbcTemplate.update(sql,params);
    }

    private ReturnBook rsToReturn(ResultSet _rs)
            throws SQLException
    {
        ReturnBook returnBook = new ReturnBook();
        returnBook.setApprover(_rs.getString("approver"));
        returnBook.setId(_rs.getString("id"));
        returnBook.setReturn_date(_rs.getDate("return_date"));
        returnBook.setBook(new Book(_rs.getString("title"),_rs.getString("book_id")));
//        returnBook.setMember(new Member(_rs.getString("name"), _rs.getString("sex"), _rs.getString("contact")));
        returnBook.setMember(new Member(_rs.getString("member_id"),_rs.getString("name"),_rs.getString("sex"),_rs.getString("major"),_rs.getInt("year"),_rs.getString("section"),_rs.getString("contact"),_rs.getString("carrer")));
        return returnBook;
    }

    public List<ReturnBook> getReturnByDate(String _startDate, String _endDate)
    {
        String sql = String.format("select r.id,r.approver, r.book_id, r.member_id, r.return_date,me.id, me.major,me.year,me.section,me.carrer, me.name, me.sex, me.contact, bo.title, bo.id from tbl_return r\n" +
                "\tinner JOIN tbl_book bo on r.book_id = bo.id\n" +
                "\tinner join tbl_member me on r.member_id = me.id where return_date >"+"'"+_startDate+"'" +"And return_date <"+"'"+_endDate+"'");

        return jdbcTemplate.query(sql, rs->{
            List<ReturnBook> returnBooks = new ArrayList<>();
            while (rs.next())
            {
                returnBooks.add(rsToReturn(rs));
            }
            return returnBooks;
        });
    }



}
