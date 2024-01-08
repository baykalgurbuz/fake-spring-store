package com.spring.fakestore.fakestore.repository;

import com.spring.fakestore.fakestore.models.BookingModel;
import com.spring.fakestore.fakestore.models.Proposal;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProposalRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProposalRepository(JdbcTemplate jdbcTemplate,NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    public List<Proposal> getAll(){

        return jdbcTemplate.query("select * from \"public\".\"PROPOSAL\" order by \"id\" asc", BeanPropertyRowMapper.newInstance(Proposal.class));

    }
    public Proposal getById(long id){
        Proposal proposal=null;
        String sql = "select * from \"public\".\"PROPOSAL\" where \"id\" = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return  namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Proposal.class));

    }
    public boolean updateStatus(long id)
    {
        String sql = "UPDATE public.\"PROPOSAL\" SET \"status\"=:STATUS WHERE \"id\"=:ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id );
        paramMap.put("STATUS",true);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
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
    public boolean Update(Proposal proposal){
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
