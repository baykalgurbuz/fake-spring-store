package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.BookingModel;
import com.spring.fakestore.fakestore.models.Role;
import com.spring.fakestore.fakestore.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingModelRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<BookingModel> getAll()
    {
        return jdbcTemplate.query("select * from \"public\".\"BOOKING\" order by \"id\" asc", BeanPropertyRowMapper.newInstance(BookingModel.class));
    }
    public BookingModel getById(long id)
    {
        BookingModel bookingModel=null;
        String sql = "select * from \"public\".\"BOOKING\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        bookingModel = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(BookingModel.class));
        return bookingModel;
    }
    public boolean save(BookingModel bookingModel)
    {
        String sql = "insert into \"public\".\"BOOKING\" (\"note\", \"booking_date\", \"status\",\"service_id\",\"user_id\") values (:NOTE, :BOOKING_DATE, :STATUS,:SERVICE_ID,USER_ID)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NOTE", bookingModel.getNote());
        paramMap.put("BOOKING_DATE",bookingModel.getBooking_date() );
        paramMap.put("STATUS",bookingModel.getStatus() );
        paramMap.put("SERVICE_ID",bookingModel.getService_id());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean deleteById(long id)
    {
        String sql = "delete from \"public\".\"BOOKING\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean update(BookingModel bookingModel)
    {
        String sql = "UPDATE public.\"BOOKING\" SET \"note\"= :NOTE, \"booking_date\"=:BOOKING_DATE,\"status\"=:STATUS,\"service_id\"=:SERVICE_ID,\"user_id\"=:USER_ID WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",bookingModel.getId() );
        paramMap.put("NOTE", bookingModel.getNote());
        paramMap.put("BOOKING_DATE",bookingModel.getBooking_date() );
        paramMap.put("STATUS",bookingModel.getStatus() );
        paramMap.put("SERVICE_ID",bookingModel.getService_id());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

}
