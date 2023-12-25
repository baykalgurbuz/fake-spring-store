package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.Role;
import com.spring.fakestore.fakestore.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PasswordEncoder passwordEncoder;

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
        String sql = "SELECT \"authority\" FROM \"public\".\"AUTHORITIES\" where \"username\" = :USERNAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("USERNAME", username);
        // beanpropertymapper kullanıyorduk ama burada gerek yok çünkü tek sütun select
        // yapıyorum
        List<String> liste = namedParameterJdbcTemplate.queryForList(sql, paramMap, String.class);
        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : liste)
        {
            roles.add(new Role(role));
        }
        return roles;
    }
    public Users getByUsername(String username){
        String sql="select * from \"public\".\"users\" where \"username\" = :Username";
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("Username", username);
        return namedParameterJdbcTemplate.queryForObject(sql,paramMap,BeanPropertyRowMapper.newInstance(Users.class));

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
    public boolean save (Users users){


        String insertUsers = "insert into \"public\".\"USERS\" (\"username\",\"email\",\"password\") values (:username, :email, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username" , users.getUsername());
        paramMap.put("email" , users.getEmail());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        paramMap.put("password" , users.getPassword());
        boolean result =namedParameterJdbcTemplate.update(insertUsers,paramMap)==1;

        //İnsert user Roles
        String insertRoles= "insert into \"public\".\"AUTHORITIES\" (\"username\",\"authority\") values (:username, :authority)";
        Map<String,Object> paramMap2= new HashMap<>();
        paramMap2.put("authority","ROLE_USER");
        paramMap2.put("username",users.getUsername());


        if(result==true){
            return namedParameterJdbcTemplate.update(insertRoles,paramMap2)==1;
        }
        return false;
    }
    public boolean deleteById(long id)
    {
        String sql = "delete from \"public\".\"USERS\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
//    public boolean update(Users users)
//    {
//        String sql = "UPDATE public.\"USERS\" SET \"username\"= :USERNAME, \"email\"=:EMAIL,\"password\"=:PASSWORD,\"role\"=:ROLE WHERE \"id\"=:ID";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("ID",users.getId() );
//        paramMap.put("USERNAME",users.getUsername() );
//        paramMap.put("EMAIL",users.getEmail() );
//        paramMap.put("PASSWORD",users.getPassword() );
//        paramMap.put("ROLE",users.getRole() );
//        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
//    }
}
