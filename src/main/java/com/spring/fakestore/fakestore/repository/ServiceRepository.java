package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.Service;
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
public class ServiceRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<Service> getAll()
    {
        return jdbcTemplate.query("select * from \"public\".\"SERVICE\" order by \"id\" asc", BeanPropertyRowMapper.newInstance(Service.class));
    }
    public Service getById(long id)
    {
        Service service=null;
        String sql = "select * from \"public\".\"SERVICE\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        service = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Service.class));
        return service;
    }
    public boolean save(Service service)
    {
        String sql = "insert into \"public\".\"SERVICE\" (\"description\", \"duration\", \"laptop\",\"desktop\",\"mac\") values (:DESCRIPTION, :DURATION, :LAPTOP,:DESKTOP,:MAC)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("DESCRIPTION", service.getDescription());
        paramMap.put("DURATION", service.getDuration());
        paramMap.put("LAPTOP",service.getLaptop() );
        paramMap.put("DESKTOP",service.getDesktop() );
        paramMap.put("MAC",service.getMac() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean deleteById(long id)
    {
        String sql = "delete from \"public\".\"SERVICE\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean update(Service service)
    {
        String sql = "UPDATE public.\"SERVICE\" SET \"description\"= :DESCRIPTION, \"duration\"=:DURATION,\"laptop\"=:LAPTOP,\"desktop\"=:DESKTOP,\"mac\"=:MAC WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",service.getId() );
        paramMap.put("DESCRIPTION",service.getDescription() );
        paramMap.put("DURATION",service.getDuration());
        paramMap.put("LAPTOP",service.getLaptop());
        paramMap.put("DESKTOP",service.getDesktop());
        paramMap.put("MAC",service.getMac() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
