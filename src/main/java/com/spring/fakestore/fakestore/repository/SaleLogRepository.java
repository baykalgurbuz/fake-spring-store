package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.SaleLog;
import com.spring.fakestore.fakestore.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SaleLogRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<com.spring.fakestore.fakestore.models.SaleLog> getAll()
    {
        return jdbcTemplate.query("select * from \"public\".\"SALE_LOG\" order by \"id\" asc", BeanPropertyRowMapper.newInstance(SaleLog.class));
    }
    public SaleLog getById(long id)
    {
        SaleLog saleLog=null;
        String sql = "select * from \"public\".\"SALE_LOG\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        saleLog = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(SaleLog.class));
        return saleLog;
    }
    public boolean save(SaleLog saleLog)
    {
        String sql = "insert into \"public\".\"SALE_LOG\" (\"sale_log_date\", \"credit_card\", \"sale_id\",\"user_id\") values (:SALE_LOG_DATE, :CREDIT_CARD, :SALE_ID,:USER_ID)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("SALE_LOG_DATE", saleLog.getSale_log_date());
        paramMap.put("CREDIT_CARD", saleLog.getCredit_card());
        paramMap.put("SALE_ID",saleLog.getSale_id());
        paramMap.put("USER_ID",saleLog.getUser_id() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean deleteById(long id)
    {
        String sql = "delete from \"public\".\"SALE_LOG\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean update(SaleLog saleLog)
    {
        String sql = "UPDATE public.\"SALE_LOG\" SET \"sale_log_date\"= :SALE_LOG_DATE, \"credit_card\"=:CREDIT_CARD,\"sale_id\"=:SALE_ID,\"user_id\"=:USER_ID WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",saleLog.getId() );
        paramMap.put("SALE_LOG_DATE",saleLog.getSale_log_date() );
        paramMap.put("CREDIT_CARD",saleLog.getCredit_card());
        paramMap.put("SALE_ID",saleLog.getSale_id());
        paramMap.put("USER_ID",saleLog.getUser_id() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
