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

public class StudentEntry {
    static String student_ID;
    String firstName;
    String lastName;
    
    public StudentEntry(String student_ID, String firstName, String lastName){
        this.student_ID = student_ID;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getStudent_ID(){
        return this.student_ID;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }


}
