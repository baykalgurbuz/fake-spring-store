package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<Users> getAll()
    {
        return jdbcTemplate.query("select * from \"public\".\"USERS\" order by \"id\" asc", BeanPropertyRowMapper.newInstance(Users.class));
    }
}
