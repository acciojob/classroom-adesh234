package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class StudentRepository {
    HashMap<String,Student> studentMap = new HashMap<>();
    HashMap<String,Teacher> teacherMap = new HashMap<>();
    HashMap<String,List<String>> studentTeacherPair = new HashMap<>();

    public StudentRepository(){
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.studentTeacherPair = new HashMap<>();
    }
    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }
    public void pairStudentTeacher(String student,String teacher){
        if(studentTeacherPair.containsKey(teacher)){
            List<String> l = studentTeacherPair.get(teacher);
            l.add(student);
            studentTeacherPair.put(teacher,l);
        }
        else{
            List<String> l = new ArrayList<>();
            l.add(student);
            studentTeacherPair.put(teacher,l);
        }
    }
    public Student getStudentByName(String name){
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentTeacherPair.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> l = new ArrayList<>();
        for(String s : studentMap.keySet())
            l.add(s);

        return l;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> l = studentTeacherPair.get(teacher);
        for(String s : l){
            studentMap.remove(s);
        }
        teacherMap.remove(teacher);
        studentTeacherPair.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(List<String> l : studentTeacherPair.values()){
            for(String s : l)
                studentMap.remove(s);
        }
        studentTeacherPair.clear();
        teacherMap.clear();
    }
}
