package com.spring.fakestore.fakestore.repository;

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
    public List<GrantedAuthority> getUserRoles(String username)
    {
        String sql = "SELECT \"authority\" FROM \"public\".\"authorities\" where \"username\" = :USERNAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("USERNAME", username);
        List<String> liste = namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class);


        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : liste)
        {
            roles.add(new Role(role));
        }
        return roles;
    }
}
