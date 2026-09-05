package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.Attendance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AttendanceDAO {
    private final JdbcTemplate jdbc;
    public AttendanceDAO(JdbcTemplate jdbc){this.jdbc=jdbc;}
    private Attendance map(java.sql.ResultSet rs,int n)throws java.sql.SQLException{
        int total=rs.getInt("total_classes"), attended=rs.getInt("attended_classes");
        double pct=total==0?0.0:(attended*100.0/total);
        return new Attendance(rs.getInt("id"),rs.getInt("student_id"),rs.getInt("subject_id"),rs.getString("subject_name"),total,attended,pct);
    }
    public List<Attendance> byStudent(int studentId){
        return jdbc.query("SELECT a.*, s.name subject_name FROM attendance a JOIN subjects s ON a.subject_id=s.id WHERE a.student_id=? ORDER BY a.id DESC",this::map,studentId);
    }
    public Attendance save(Attendance a){
        jdbc.update("INSERT INTO attendance(student_id,subject_id,total_classes,attended_classes) VALUES(?,?,?,?)",a.getStudentId(),a.getSubjectId(),a.getTotalClasses(),a.getAttendedClasses());
        return a;
    }
    public int update(int id,Attendance a){return jdbc.update("UPDATE attendance SET student_id=?,subject_id=?,total_classes=?,attended_classes=? WHERE id=?",a.getStudentId(),a.getSubjectId(),a.getTotalClasses(),a.getAttendedClasses(),id);}
    public int delete(int id){return jdbc.update("DELETE FROM attendance WHERE id=?",id);}
}
