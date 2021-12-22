


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


public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement getWaitlistedStudent;
    private static PreparedStatement updateStudent;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement courseCodeByStudent;
    private static PreparedStatement semesterByStudent;
    private static PreparedStatement dropStudentAllSchedule;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropCourseStudentList;
    private static PreparedStatement getStatus;
    private static PreparedStatement getCourseCode;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry) 
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, student_id, course_code, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudent_ID());
            addScheduleEntry.setString(3, entry.getCourse_Code());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String student_ID) //does this need to be static
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleEntry = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select (course_code, status) from app.schedule where semester = ? and student_id = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, student_ID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                scheduleEntry.add((ScheduleEntry) resultSet.getObject(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduleEntry;
        
    }
    
    public static ArrayList<String> getCourseCode(String semester, String student_ID)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseCode = connection.prepareStatement("select course_code from app.schedule where semester = ? and student_ID = ?");
            getCourseCode.setString(1, semester);
            getCourseCode.setString(2, student_ID);
            resultSet = getCourseCode.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
    
    public static String getStatus(String semester, String student_ID, String course_code)
    {
        connection = DBConnection.getConnection();
        String status = "";
        try{
            getStatus = connection.prepareStatement("select status from app.schedule where semester = ? and student_id = ? and course_code = ?");
            getStatus.setString(1, semester);
            getStatus.setString(2, student_ID);
            getStatus.setString(3, course_code);
            resultSet = getStatus.executeQuery();
            
            while (resultSet.next()){
                status = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return status;
        
    }
    
    public static int getScheduledStudentCount(String semester, String course_code) //look at this again
    {   

        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            PreparedStatement scheduledStudentCount = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ?");
            scheduledStudentCount.setString(1, semester);
            scheduledStudentCount.setString(2, course_code);
            resultSet = scheduledStudentCount.executeQuery();
            
            while (resultSet.next())
            {
                count = count + 1;
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String course_code){
        
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> ScheduledStudentsByCourse = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ? and status = ?");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, course_code);
            getScheduledStudentsByCourse.setString(3, "s");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                ScheduledStudentsByCourse.add((ScheduleEntry) resultSet.getObject(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return ScheduledStudentsByCourse;
        
    }
    
    public static ArrayList<String> getScheduledStudentsByCourse_String(String semester, String course_code){
        
        connection = DBConnection.getConnection();
        ArrayList<String> ScheduledStudentsByCourse = new ArrayList<String>();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ? and status = ?");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, course_code);
            getScheduledStudentsByCourse.setString(3, "s");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                ScheduledStudentsByCourse.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return ScheduledStudentsByCourse;
        
    }
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String course_code){
        
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> WaitlistedStudentsByCourse = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ? and status = ?");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, course_code);
            getWaitlistedStudentsByCourse.setString(3, "w");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                WaitlistedStudentsByCourse.add((ScheduleEntry) resultSet.getObject(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return WaitlistedStudentsByCourse;
        
    }
    
    public static ArrayList<String> getWaitlistedStudentsByCourse_String(String semester, String course_code){
        
        connection = DBConnection.getConnection();
        ArrayList<String> WaitlistedStudentsByCourse = new ArrayList<String>();
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ? and status = ?");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, course_code);
            getWaitlistedStudentsByCourse.setString(3, "w");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                WaitlistedStudentsByCourse.add((String) resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return WaitlistedStudentsByCourse;
        
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and student_ID = ? and course_code = ?");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }  
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }   
    
    
    //dropping the student across all schedules
    public static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentAllSchedule = connection.prepareStatement("delete from app.schedule where student_ID = ?");
            dropStudentAllSchedule.setString(1, studentID);
            dropStudentAllSchedule.executeUpdate();
        }  
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }      
    
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and course_code = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
        }  
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void updateStudent(String semester, String courseCode, String studentID){
        connection = DBConnection.getConnection();
        try
        {
            updateStudent = connection.prepareStatement("update app.schedule set status = ? where semester = ? and course_code = ? and student_id = ?");
            updateStudent.setString(1, "s");
            updateStudent.setString(2, semester);
            updateStudent.setString(3, courseCode);
            updateStudent.setString(4, studentID);
            updateStudent.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    
    public static ArrayList<String> semesterByStudent(String studentID){
        connection = DBConnection.getConnection();
        
        ArrayList<String> semester = new ArrayList<>();
        try
        {
            semesterByStudent = connection.prepareStatement("Select semester from app.schedule where student_id = ?");
            semesterByStudent.setString(1, studentID);
            resultSet = semesterByStudent.executeQuery();
            while(resultSet.next())
            {
                semester.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return semester;
    }
    
    public static ArrayList<String> courseCodeByStudent(String studentID){
        connection = DBConnection.getConnection();
        
        ArrayList<String> courses = new ArrayList<>();
        try
        {
            courseCodeByStudent = connection.prepareStatement("Select course_code from app.schedule where student_id = ?");
            courseCodeByStudent.setString(1, studentID);
            resultSet = courseCodeByStudent.executeQuery();
            
            while(resultSet.next())
            {
                courses.add(resultSet.getString(1));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
    }
    
    public static String getWaitlistedStudent(String semester, String courseCode){
        connection = DBConnection.getConnection();
        String student = null;
        try
        {
            getWaitlistedStudent = connection.prepareStatement("select student_id from app.schedule where semester = ? and course_code = ? and status = ? order by timestamp");
            getWaitlistedStudent.setString(1, semester);
            getWaitlistedStudent.setString(2, courseCode);
            getWaitlistedStudent.setString(3, "w");
            resultSet = getWaitlistedStudent.executeQuery();
            while(resultSet.next())
            {
                student = resultSet.getString(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry) {
        connection = DBConnection.getConnection();
        try
        {
            updateScheduleEntry = connection.prepareStatement("update app.schedule set student_id = ?, course_code = ?, status = ?, timestamp = ?  where semester = ?");
            addScheduleEntry.setString(1, "s");
            addScheduleEntry.setString(2, entry.getSemester());
            addScheduleEntry.setString(3, entry.getCourse_Code());
            addScheduleEntry.setString(4, entry.getStudent_ID());
            updateScheduleEntry.executeUpdate();
        }  
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> dropCourseStudentList(String semester, String courseCode){
        
        connection = DBConnection.getConnection();
        ArrayList<String> listStudents = new ArrayList<>();
        
        try{
            dropCourseStudentList = connection.prepareStatement("select student_ID from app.schedule where semester = ? and course_code = ?");
            dropCourseStudentList.setString(1, semester);
            dropCourseStudentList.setString(2, courseCode);
            resultSet = dropCourseStudentList.executeQuery();
            
            while(resultSet.next())
            {
                listStudents.add(resultSet.getString(1));
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return listStudents;
    }
    
}
