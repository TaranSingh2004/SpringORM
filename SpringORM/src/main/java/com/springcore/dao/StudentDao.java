package com.springcore.dao;

import com.springcore.entities.Student;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.transaction.Transactional;
import java.util.List;


public class StudentDao {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
//save student
    @Transactional
    public int insert(Student student){
        Integer i = (Integer) this.hibernateTemplate.save(student);
        return i;
    }

    //get single data
    public Student getStudent(int studentId){
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        return student;
    }

    //get all data
    public List<Student> getAllStudents() {
        List<Student> students = this.hibernateTemplate.loadAll(Student.class);
        return students;
    }

    //delete
    @Transactional
    public void deleteStudent(int studentId){
        Student student= this.hibernateTemplate.get(Student.class, studentId);
        this.hibernateTemplate.delete(student);
    }

    //update
    @Transactional
    public void updateStudent(Student student){
        this.hibernateTemplate.update(student);
    }
}
