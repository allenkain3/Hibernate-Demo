package com.springboot.hibernatedemo.dao;

import com.springboot.hibernatedemo.entity.Course;
import com.springboot.hibernatedemo.entity.Instructor;
import com.springboot.hibernatedemo.entity.InstructorDetail;
import com.springboot.hibernatedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDAO{

    private EntityManager entityManager;

    @Autowired
    public InstructorDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
            entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class,theId);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,theId);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query

        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        //execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                    "select i from Instructor i "
                                                            + "JOIN FETCH i.courses "
                                                            + "JOIN FETCH i.instructorDetail "
                                                            + "where i.id = :data", Instructor.class);
        query.setParameter("data",theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
            entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class,courseId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        Course course = entityManager.find(Course.class,courseId);
        entityManager.remove(course);
    }


    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
       //create query

        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +  "JOIN FETCH c.reviews "
                        +  "where c.id = :data", Course.class);

        query.setParameter("data",theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        //query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +  "JOIN FETCH c.students "
                        +  "where c.id = :data", Course.class);

        query.setParameter("data",theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int theId) {
        //query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        +  "JOIN FETCH s.courses "
                        +  "where s.id = :data", Student.class);

        query.setParameter("data",theId);

        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = entityManager.find(Student.class,theId);
        entityManager.remove(student);
    }
}
