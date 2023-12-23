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

    public Users getById(long id)
    {
        Users users=null;
        String sql = "select * from \"public\".\"USERS\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        users = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Users.class));
        return users;
    }
    public boolean save(Users user)
    {
        String sql = "insert into \"public\".\"USERS\" (\"username\", \"email\", \"password\",\"role\") values (:USERNAME, :EMAIL, :PASSWORD,:ROLE)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("USERNAME", user.getUsername());
        paramMap.put("EMAIL", user.getEmail());
        paramMap.put("PASSWORD",user.getPassword() );
        paramMap.put("ROLE",user.getRole() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean deleteById(long id)
    {
        String sql = "delete from \"public\".\"USERS\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
    public boolean update(Users users)
    {
        String sql = "UPDATE public.\"USERS\" SET \"username\"= :USERNAME, \"email\"=:EMAIL,\"password\"=:PASSWORD,\"role\"=:ROLE WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",users.getId() );
        paramMap.put("USERNAME",users.getUsername() );
        paramMap.put("EMAIL",users.getEmail() );
        paramMap.put("PASSWORD",users.getPassword() );
        paramMap.put("ROLE",users.getRole() );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
