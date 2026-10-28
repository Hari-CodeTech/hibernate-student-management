//
//package com.studentmanagement.main;
//
//import java.util.Optional;
//
//import com.studentmanagement.dao.StudentDao;
//import com.studentmanagement.entity.Student;
//import com.studentmanagement.util.HibernateUtil;
//
//public class TestHibernate {
//
//    public static void main(String[] args) {
//
//        try {
//            StudentDao studentDao = new StudentDao();
//
//            int searchId = 1;
//
//            Optional<Student> result = studentDao.getStudentById(searchId);
//
//            if (result.isPresent()) {
//                System.out.println("Student Found:");
//                System.out.println(result.get());
//            } else {
//                System.out.println("Student not found with ID: " + searchId);
//            }
//
//        } finally {
//            HibernateUtil.shutdown();
//        }
//    }
//}


// Update Student
//package com.studentmanagement.main;
//
//import com.studentmanagement.dao.StudentDao;
//import com.studentmanagement.util.HibernateUtil;
//
//public class TestHibernate {
//
//    public static void main(String[] args) {
//
//        try {
//            StudentDao studentDao = new StudentDao();
//
//            studentDao.updateStudent(
//                    1,
//                    "Hari Puvvala",
//                    "hari.puvvala@example.com",
//                    23
//            );
//
//        } finally {
//            HibernateUtil.shutdown();
//        }
//    }
//}

// To Delete Student Record
//package com.studentmanagement.main;
//
//import com.studentmanagement.dao.StudentDao;
//import com.studentmanagement.util.HibernateUtil;
//
//public class TestHibernate {
//
//    public static void main(String[] args) {
//
//        try {
//            StudentDao studentDao = new StudentDao();
//
//            studentDao.deleteStudent(1);
//
//        } finally {
//            HibernateUtil.shutdown();
//        }
//    }
//}



package com.studentmanagement.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.entity.Student;
import com.studentmanagement.util.HibernateUtil;

public class TestHibernate {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDao();
        
      
        try (Scanner sc = new Scanner(System.in)) {
        	
        	System.out.println("Total Students: " + studentDao.countStudents());

            int choice;

            do {
                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by ID");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        sc.nextLine();

                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter age: ");
                        int age = sc.nextInt();

                        studentDao.saveStudent(new Student(name, email, age));
                        break;

                    case 2:
                        List<Student> students = studentDao.getAllStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            students.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        int searchId = sc.nextInt();

                        Optional<Student> result =
                                studentDao.getStudentById(searchId);

                        if (result.isPresent()) {
                            System.out.println(result.get());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter student ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter new email: ");
                        String newEmail = sc.nextLine();

                        System.out.print("Enter new age: ");
                        int newAge = sc.nextInt();

                        studentDao.updateStudent(
                                updateId, newName, newEmail, newAge
                        );
                        break;

                    case 5:
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = sc.nextInt();

                        studentDao.deleteStudent(deleteId);
                        break;

                    case 0:
                        System.out.println("Exiting Student Management System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 0);

        } finally {
            HibernateUtil.shutdown();
        }
    }
}

