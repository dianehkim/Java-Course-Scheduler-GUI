/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diane
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

public class ScheduleEntry {
 
    String semester;
    private String student_ID;
    String course_code;
    private String status;
    private Timestamp timestamp;
    
    
    public ScheduleEntry(String semester, String student_ID, String course_code, String status, Timestamp timestamp)
    {
        this.semester = semester;
        this.student_ID = student_ID;
        this.course_code = course_code;
        this.status = status;
        this.timestamp = timestamp;
    }
    
    public String getSemester() {
        return this.semester;
    }
   
    public String getStudent_ID() {
        return this.student_ID;
    }    
    
    public String getCourse_Code() {
        return this.course_code;
    }    
    
    public String getStatus() {
        return this.status;
    }    
    
    public Timestamp getTimestamp() {
        return this.timestamp;
    }
    
}
