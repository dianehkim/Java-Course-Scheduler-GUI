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

public class CourseEntry {
    String currentSemester;
    String courseCode;
    String description;
    int seats;
    

    public CourseEntry(String currentSemester, String courseCode, String description, int seats){
        this.currentSemester = currentSemester;
        this.courseCode = courseCode;
        this.description = description;
        this.seats = seats;
    }

   
    public String getSemester() {
        return this.currentSemester;
    }
    
    public String getCourseCode() {
        return this.courseCode;  
    }  
    
    public String getDescription() {
        return this.description;
        
    }  
    
    public int getSeats() {
        return this.seats;
    }
}
