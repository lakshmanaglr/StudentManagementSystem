package com.example.studentmanagement.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository public class SubjectDAO {
 private final JdbcTemplate jdbc; public SubjectDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
 public List<Map<String,Object>> findAll(){return jdbc.queryForList("SELECT id,name,course FROM subjects ORDER BY name");}
}
