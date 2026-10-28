
package com.studentmanagement.dao;


import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.studentmanagement.entity.Student;
import com.studentmanagement.util.HibernateUtil;

public class StudentDao {

    public void saveStudent(Student student) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

            System.out.println("Student saved successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Failed to save student.");
            e.printStackTrace();
        }
    }
    public List<Student> getAllStudents() {
    	
    	try(Session session = HibernateUtil.getSessionFactory().openSession()){
    		return session.createQuery("FROM Student",Student.class)
    				.getResultList();
    		
    	} catch(Exception e) {
    		
    		System.out.println("Failed to retrive students");
    		e.printStackTrace();
    		
    		return List.of();
    	}
    	
    
    }
    // Search Student by ID
    
    
    public Optional<Student> getStudentById(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Student student = session.find(Student.class, id);
            return Optional.ofNullable(student);

        } catch (Exception e) {
            System.out.println("Failed to search student.");
            e.printStackTrace();
            return Optional.empty();
        }
    }
    
 // Update Student Details
    public void updateStudent(int id, String name, String email, int age) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student = session.find(Student.class, id);

            if (student != null) {
                student.setName(name);
                student.setEmail(email);
                student.setAge(age);

                transaction.commit();
                System.out.println("Student updated successfully!");
            } else {
                transaction.rollback();
                System.out.println("Student not found with ID: " + id);
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Failed to update student.");
            e.printStackTrace();
        }
    }
    
 // Delete Student by ID
    public void deleteStudent(int id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student = session.find(Student.class, id);

            if (student != null) {
                session.remove(student);
                transaction.commit();

                System.out.println("Student deleted successfully!");
            } else {
                transaction.rollback();
                System.out.println("Student not found with ID: " + id);
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Failed to delete student.");
            e.printStackTrace();
        }
    }
 // Count total students
    public Long countStudents() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "SELECT COUNT(s) FROM Student s",
                    Long.class
            ).getSingleResult();

        } catch (Exception e) {
            System.out.println("Failed to count students.");
            e.printStackTrace();
            return 0L;
        }
    }
    }

