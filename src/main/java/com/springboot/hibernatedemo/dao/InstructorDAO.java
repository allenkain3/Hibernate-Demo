package com.springboot.hibernatedemo.dao;


import com.springboot.hibernatedemo.entity.Course;
import com.springboot.hibernatedemo.entity.Instructor;
import com.springboot.hibernatedemo.entity.InstructorDetail;
import com.springboot.hibernatedemo.entity.Student;

import java.util.List;

public interface InstructorDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);
    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int courseId);

    void deleteCourseById(int courseId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCourseByStudentId(int theId);

    void update(Student student);

    void deleteStudentById(int theId);

}
