package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.Proposal;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProposalRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProposalRepository(JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<Proposal> getAll(){

        String sql="";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Proposal.class));

    }
    public List<Proposal>getById(long id){
        String sql=;
        HashMap<String,Object> map= new HashMap<>();
        map.put("id",id);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Proposal.class));

    }
    public Proposal getByPrice(int price){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("Price",price);
        return namedParameterJdbcTemplate.queryForObject(sql,map,BeanPropertyRowMapper.newInstance(Proposal.class));
    }

    public Proposal getByNote(int note){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("Note",note);
        return namedParameterJdbcTemplate.queryForObject(sql,map, BeanPropertyRowMapper.newInstance(Proposal.class));
    }
    // UPDATE FONKSİYONLARI
    public boolean UpdatePrice(Proposal proposal){
        String sql="";
        HashMap<String,Object> map =new HashMap<>();
        map.put("Price",proposal.getPrice());
        map.put("Note",proposal.getNote());
        map.put("Status",proposal.getStatus());
        // 1 e eşit olması demek herhangi bir işlev yerine getirilmesi demek //
        return jdbcTemplate.update(sql,map) == 1;
    }

    // DELETE FONKSİYONU
    public boolean deleteById(long id){
        String sql="";
        HashMap<Object,Object> map = new HashMap<>();
        map.put("ID",id);
        return jdbcTemplate.update(sql,map)==1;
    }
    // GET ALL LİKE FONKSİYONU
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
    public int getAllLikeStatus(String status){
        String sql="";
        HashMap<String,Object> map = new HashMap<>();
        map.put("Status",status);
        return namedParameterJdbcTemplate.update(sql,map);

    }

}
