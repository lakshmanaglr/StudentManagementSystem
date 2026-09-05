package com.example.studentmanagement.model;

public class Attendance {
    private Integer id;
    private Integer studentId;
    private Integer subjectId;
    private String subjectName;
    private Integer totalClasses;
    private Integer attendedClasses;
    private Double percentage;

    public Attendance() {}
    public Attendance(Integer id,Integer studentId,Integer subjectId,String subjectName,Integer totalClasses,Integer attendedClasses,Double percentage){
        this.id=id;this.studentId=studentId;this.subjectId=subjectId;this.subjectName=subjectName;this.totalClasses=totalClasses;this.attendedClasses=attendedClasses;this.percentage=percentage;
    }
    public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
    public Integer getStudentId(){return studentId;} public void setStudentId(Integer studentId){this.studentId=studentId;}
    public Integer getSubjectId(){return subjectId;} public void setSubjectId(Integer subjectId){this.subjectId=subjectId;}
    public String getSubjectName(){return subjectName;} public void setSubjectName(String subjectName){this.subjectName=subjectName;}
    public Integer getTotalClasses(){return totalClasses;} public void setTotalClasses(Integer totalClasses){this.totalClasses=totalClasses;}
    public Integer getAttendedClasses(){return attendedClasses;} public void setAttendedClasses(Integer attendedClasses){this.attendedClasses=attendedClasses;}
    public Double getPercentage(){return percentage;} public void setPercentage(Double percentage){this.percentage=percentage;}
}
