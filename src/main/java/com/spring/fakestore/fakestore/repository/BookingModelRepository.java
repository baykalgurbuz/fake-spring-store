package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.BookingModel;
import com.spring.fakestore.fakestore.models.Users;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<BookingModel> getAllWithDate()
    {
        return jdbcTemplate.query("select * from \"public\".\"BOOKING\" order by \"booking_date\" desc", BeanPropertyRowMapper.newInstance(BookingModel.class));
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
    public Long save(BookingModel bookingModel)
    {
        LocalDate localDate =LocalDate.now();

        Long durationTime =getDuration(localDate);


        String sql = "insert into \"public\".\"BOOKING\" (\"id\",\"note\", \"booking_date\", \"status\",\"service_id\",\"user_id\") values (:ID,:NOTE, :BOOKING_DATE, 'bekliyor',:SERVICE_ID,:USER_ID)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", lastItem() + 1);
        paramMap.put("NOTE", bookingModel.getNote());
        paramMap.put("SERVICE_ID", bookingModel.getService_id());
        paramMap.put("USER_ID", bookingModel.getUser_id());


        String serviceDurationSql = "SELECT duration FROM \"SERVICE\" WHERE id = "+bookingModel.getService_id();
        Long serviceDuration = jdbcTemplate.queryForObject(serviceDurationSql, Long.class);
        if (durationTime+serviceDuration<=10)
        {
            paramMap.put("BOOKING_DATE", localDate);
        }
        else {
            while (true) {
                Long totalDuration = getDuration(localDate);
                if (totalDuration == null) {

                    break;
                }

                if (totalDuration + serviceDuration > 10) {
                    localDate = localDate.plusDays(1);
                } else {
                    break;
                }
            }
            paramMap.put("BOOKING_DATE", localDate);
        }
        int rowsAffected = namedParameterJdbcTemplate.update(sql, paramMap);

        if (rowsAffected > 0) {
            return lastItem() + 1;
        } else {
            return null;
        }
    }
    public Long lastItem()
    {
        String lastIdSql = "SELECT id FROM \"public\".\"BOOKING\" ORDER BY \"id\" DESC LIMIT 1";
        Long lastId = jdbcTemplate.queryForObject(lastIdSql, Long.class);
        return lastId;
    }
    public Long getDuration(LocalDate date)
    {
        String totalDurationSql = "SELECT SUM(\"SERVICE\".duration)\n" +
                "FROM \"public\".\"BOOKING\"\n" +
                "INNER JOIN \"public\".\"SERVICE\" ON \"public\".\"BOOKING\".\"service_id\" = \"public\".\"SERVICE\".\"id\"\n" +
                "WHERE \"public\".\"BOOKING\".\"booking_date\" = ?\n" +
                "GROUP BY \"public\".\"BOOKING\".\"booking_date\"";
        try{
            Long serviceDuration = jdbcTemplate.queryForObject(totalDurationSql, Long.class,date);
            return serviceDuration;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }

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
    public boolean updateStatus(long id)
    {
        String sql = "UPDATE public.\"BOOKING\" SET \"status\"=:STATUS WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        paramMap.put("STATUS","isleme alındı");
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean completeStatus(long id)
    {
        String sql = "UPDATE public.\"BOOKING\" SET \"status\"=:STATUS WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        paramMap.put("STATUS","tamamlandı");
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

}
