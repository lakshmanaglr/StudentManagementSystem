package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentDAO {
    private final JdbcTemplate jdbc;
    public StudentDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
    private Student map(java.sql.ResultSet rs,int n)throws java.sql.SQLException{
        return new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"),rs.getString("course"),rs.getInt("year"));
    }
    public List<Student> findAll(){return jdbc.query("SELECT * FROM students ORDER BY id DESC",this::map);}
    public List<Student> search(String q){String x="%"+q+"%"; return jdbc.query("SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ? ORDER BY id DESC",this::map,x,x,x);}
    public Student findById(int id){List<Student> l=jdbc.query("SELECT * FROM students WHERE id=?",this::map,id);return l.isEmpty()?null:l.get(0);}
    public Student save(Student s){
        KeyHolder kh=new GeneratedKeyHolder();
        jdbc.update(c->{PreparedStatement p=c.prepareStatement("INSERT INTO students(name,email,phone,course,year) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);p.setString(1,s.getName());p.setString(2,s.getEmail());p.setString(3,s.getPhone());p.setString(4,s.getCourse());p.setInt(5,s.getYear());return p;},kh);
        s.setId(kh.getKey().intValue()); return s;
    }
    public int update(int id,Student s){return jdbc.update("UPDATE students SET name=?,email=?,phone=?,course=?,year=? WHERE id=?",s.getName(),s.getEmail(),s.getPhone(),s.getCourse(),s.getYear(),id);}
    public int delete(int id){return jdbc.update("DELETE FROM students WHERE id=?",id);}
}
