package com.springboot.hibernatedemo;

import com.springboot.hibernatedemo.dao.InstructorDaoImpl;
import com.springboot.hibernatedemo.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class HibernatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDaoImpl instructorDao){
		return runner -> {
			//createInstructor(instructorDao);
			//findInstructor(instructorDao);
			//deleteInstructor(instructorDao);
			//findInstuctorDetail(instructorDao);
			//deleteInstructorDetail(instructorDao);
			//createInstructorWithCourses(instructorDao);
			//findInstructorWithCourses(instructorDao);
			//findCourseForInstructor(instructorDao);
			//findInstructorWithCoursesJoinFetch(instructorDao);
			//updateInstructor(instructorDao);
			//updateCourse(instructorDao);
			//createCourseAndReviews(instructorDao);
			//retrieveCourseWithReviews(instructorDao);
			//createCourseAndStudents(instructorDao);
			//findCourseAndStudents(instructorDao);
			//findStudentAndCourses(instructorDao);
			//addMoreCoursesToStudents(instructorDao);
			//deleteCourse(instructorDao);
			deleteStudent(instructorDao);


		};
	}

	private void deleteStudent(InstructorDaoImpl instructorDao) {
		int theId = 1;

		System.out.println("Deleting student id: " + theId);

		instructorDao.deleteStudentById(theId);

		System.out.println("Done!");
	}

	private void deleteCourse(InstructorDaoImpl instructorDao) {

		int theId = 10;
		System.out.println("Deleting course id: " + theId);

		instructorDao.deleteCourseById(theId);
		System.out.println("Done!");


	}


	private void addMoreCoursesToStudents(InstructorDaoImpl instructorDao) {

		int theId = 2;

		Student student = instructorDao.findStudentAndCourseByStudentId(theId);

		//more courses
		Course course1 = new Course("Critical Thinking SKills");
		Course course2 = new Course("How to deploy project to AWS");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student: " + student);
		System.out.println("associated courses: " + student.getCourses());

		instructorDao.update(student);

		System.out.println("Done!");

	}

	private void findStudentAndCourses(InstructorDaoImpl instructorDao) {
		int theId = 2;

		Student student = instructorDao.findStudentAndCourseByStudentId(theId);

		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(InstructorDaoImpl instructorDao) {
		int theId = 10;

		Course course = instructorDao.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + course);
		System.out.println("Students " + course.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(InstructorDaoImpl instructorDao) {

		Course course = new Course("How to become a web developer");

		Student student1 = new Student("Jen","Nguyen","jennguyen@gmail.com");
		Student student2 = new Student("Bon","Nguyen","bonnguyen@gmail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Saving the course: " + course);
		System.out.println("associated students: " + course.getStudents());

		instructorDao.save(course);

		System.out.println("Done!");

	}

	private void retrieveCourseWithReviews(InstructorDaoImpl instructorDao) {

			int theId = 11;

			Course course = instructorDao.findCourseAndReviewsByCourseId(theId);

			System.out.println(course);

			System.out.println(course.getReviews());

			System.out.println("Done!");
	}

	private void createCourseAndReviews(InstructorDaoImpl instructorDao) {

		Course course = new Course("Front-end Development");

		course.addReview(new Review("Great course"));
		course.addReview(new Review("Very easy to follow."));

		//Saving the course
		System.out.println("Saving the course...");
		System.out.println(course);
		System.out.println(course.getReviews());

		instructorDao.save(course);
		System.out.println("Done!");
	}

	private void updateCourse(InstructorDaoImpl instructorDao) {

		int courseId = 10;

		System.out.println("Find course id: " + courseId);
		Course course = instructorDao.findCourseById(courseId);

		System.out.println("Updating course id: " + courseId);
		course.setTitle("Back-end Developer Beginner");

		instructorDao.updateCourse(course);

		System.out.println("Done!");
	}

	private void updateInstructor(InstructorDaoImpl instructorDao) {
		int theId = 2;

		System.out.println("Findind instructor with id: " + theId);
		Instructor instructor = instructorDao.findInstructorById(theId);

		System.out.println("Update instructor id: " + theId);

		instructor.setEmail("kainguyen@gmail.com");
		instructorDao.updateInstructor(instructor);

		System.out.println("Done!");
	}


	//Find Instructor AND Courses with Join Fetch
	private void findInstructorWithCoursesJoinFetch(InstructorDaoImpl instructorDao) {

		int theId = 2;

		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = instructorDao.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	//Lazy find courses
	private void findCourseForInstructor(InstructorDaoImpl instructorDao) {
		int theId = 2;
		System.out.println("Find instructor id: " + theId);

		Instructor instructor = instructorDao.findInstructorById(theId);
		System.out.println("Instructor : " + instructor);

		//find courses
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = instructorDao.findCoursesByInstructorId(theId);

		instructor.setCourses(courses);

		System.out.println("the associated courses: " + instructor.getCourses());
		System.out.println("Done!");

	}

	//Eager find Instructor with courses
	private void findInstructorWithCourses(InstructorDaoImpl instructorDao) {

		int theid = 2;
		System.out.println("Find instructor id: " + theid);

		Instructor instructor = instructorDao.findInstructorById(theid);

		System.out.println("Instructor : " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(InstructorDaoImpl instructorDao) {
		Instructor instructor = new Instructor("Kai","Nguyen","allenkain3@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com/allenkain3","coding");

		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Javascript beginner");
		Course course2 = new Course("Python beginner");

		instructor.add(course1);
		instructor.add(course2);

		System.out.println("Saving instructor: " + instructor);
		System.out.println("The Courses: " + instructor.getCourses());
		instructorDao.save(instructor);
		System.out.println("Done!");

	}

	private void deleteInstructorDetail(InstructorDaoImpl instructorDao) {
		int theId =3;
		System.out.println("Deleting Instructor's Detail with id: " + theId);
		instructorDao.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstuctorDetail(InstructorDaoImpl instructorDao) {

		int theId = 2;
		InstructorDetail instructorDetail = instructorDao.findInstructorDetailById(theId);
		System.out.println("Instructor's Details: " + instructorDetail);
		System.out.println("Instructor associated with these details: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(InstructorDaoImpl instructorDao) {
		int theId = 1;
		System.out.println("Deleting instructor with id: " + theId);
		instructorDao.deleteInstructorById(theId);
		System.out.println("Done!!!");
	}

	private void findInstructor(InstructorDaoImpl instructorDao) {
		int theId = 1;
		Instructor instructor =instructorDao.findInstructorById(theId);

		System.out.println("Searching for instructor with id: " + theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("Instructor's detail: " +instructor.getInstructorDetail());
	}

	private void createInstructor(InstructorDaoImpl instructorDao) {

		Instructor instructor = new Instructor("Kai","Nguyen","allenkain3@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com/allenkain3","coding");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: " + instructor);
		instructorDao.save(instructor);

		System.out.println("Done!!!");

	}


}
