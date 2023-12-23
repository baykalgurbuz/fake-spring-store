package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SaleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SaleRepository(JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;

    }

    public List<Sale> getAll(){

        String sql="";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sale.class));

    }
    public Sale getById(long id){
        String sql="";
        HashMap<String,Object> map= new HashMap<>();
        map.put("ozi",id);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Sale.class));

    }
    public Sale getByPrice(int price){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("ozi",price);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Sale.class));
    }

    public Sale getByNote(int note){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("ozi",note);
        return namedParameterJdbcTemplate.queryForObject(sql,map, BeanPropertyRowMapper.newInstance(Sale.class));
    }

    public boolean UpdatePrice(Sale sale){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("Price",sale.getPrice());
        // 1 e eşit olması demek herhangi bir işlev yerine getirilmesi demek //
        return jdbcTemplate.update(sql,map) == 1;
    }

    public boolean UpdateNote(Sale sale){
        String sql="";
        HashMap<String,Object> map= new HashMap<>();
        map.put("Note",sale.getNote());
        return jdbcTemplate.update(sql,map) == 1;
    }

    public boolean deleteById(long id){
        String sql="";
        HashMap<Object,Object> map = new HashMap<>();
        map.put("ID",id);
        return jdbcTemplate.update(sql,map)==1;
    }

    public int getAllLikeNote(int note){
        String sql="";
        HashMap<String,Object> map = new HashMap<>();
        map.put("Note",note);
        return namedParameterJdbcTemplate.update(sql,map);

    }
    public int getAllLikePrice(int price){
        String sql="";
        HashMap<String,Object> map = new HashMap<>();
        map.put("Price",price);
        return namedParameterJdbcTemplate.update(sql,map);

    }
    public String deneme(int price){
        String sql="";
        HashMap<String,Object> map = new HashMap<>();
        map.put("Price",price);
        return "deneme";

    }
}