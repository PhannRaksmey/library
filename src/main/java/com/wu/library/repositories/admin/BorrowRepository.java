package com.wu.library.repositories.admin;

import com.wu.library.models.Book;
import com.wu.library.models.Borrow;
import com.wu.library.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BorrowRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public int borrow(String _bookId){

        String sql = "UPDATE tbl_book SET status= false where id= :bookId";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("bookId", _bookId);
        return jdbcTemplate.update(sql,param);
    }


    public int insert(String _approverId, String _approver , String _bookId, String _memberId){
        String sql ="INSERT INTO tbl_borrow (book_id, member_id, approver, approver_id) VALUES(:bookId, :memberId, :approver, :approverId)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("bookId", _bookId);
        parameterSource.addValue("memberId", _memberId);
        parameterSource.addValue("approver", _approver);
        parameterSource.addValue("approverId",_approverId);
        return jdbcTemplate.update(sql,parameterSource);
    }

    public List<Borrow> getAll()
    {
        String sql = String.format("select br.id, br.approver, br.book_id,br.member_id, br.borrow_date,me.id, me.major,me.year,me.section,me.carrer, me.name, me.sex, me.contact, bo.title from tbl_borrow br\n" +
                "\tinner JOIN tbl_book bo on br.book_id = bo.id\n" +
                "\tinner join tbl_member me on br.member_id = me.id \n");

       return jdbcTemplate.query(sql, rs->{
           List<Borrow> borrows = new ArrayList<>();
           while (rs.next())
           {
               borrows.add(rsToBorrow(rs));
           }
           return borrows;
       });
    }

    public List<Borrow> getBorrowByDate(String _startDate, String _endDate)
    {
        String sql = String.format("select br.id, br.approver, br.book_id,br.member_id, br.borrow_date,me.id, me.major,me.year,me.section,me.carrer, me.name, me.sex, me.contact, bo.title from tbl_borrow br\n" +
                "\tinner JOIN tbl_book bo on br.book_id = bo.id\n" +
                "\tinner join tbl_member me on br.member_id = me.id where borrow_date >"+"'"+_startDate+"'" +"And borrow_date <"+"'"+_endDate+"'");

        return jdbcTemplate.query(sql, rs->{
            List<Borrow> borrows = new ArrayList<>();
            while (rs.next())
            {
                borrows.add(rsToBorrow(rs));
            }
            return borrows;
        });
    }

    public int delete(int _id){
        String sql = "DELETE from tbl_borrow WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",_id);
        return jdbcTemplate.update(sql,params);
    }

    private Borrow rsToBorrow(ResultSet _rs)
        throws SQLException
    {
        Borrow borrow = new Borrow();
        borrow.setId(_rs.getLong("id"));
        borrow.setApprover(_rs.getString("approver"));
        borrow.setBorrow_date(_rs.getDate("borrow_date"));
        borrow.setBook(new Book(_rs.getString("title"),_rs.getString("book_id")));
        borrow.setMember(new Member(_rs.getString("member_id"),_rs.getString("name"),_rs.getString("sex"),_rs.getString("major"),_rs.getInt("year"),_rs.getString("section"),_rs.getString("contact"),_rs.getString("carrer")));
        return borrow;
    }
}
