package com.springcore;

import com.springcore.dao.StudentDao;
import com.springcore.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean go = true;

        while (go) {
            System.out.println("==== Student Management System ====");
            System.out.println("1. Add new student");
            System.out.println("2. Display all students");
            System.out.println("3. Get details of a single student");
            System.out.println("4. Delete a student");
            System.out.println("5. Update a student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int input = Integer.parseInt(br.readLine());

                switch (input) {
                    case 1: // Add new student
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(br.readLine());
                        System.out.print("Enter student name: ");
                        String name = br.readLine();
                        System.out.print("Enter student city: ");
                        String city = br.readLine();

                        Student student = new Student(id, name, city);
                        int result = studentDao.insert(student);
                        System.out.println("Student added successfully with ID: " + result);
                        break;

                    case 2: // Display all students
                        System.out.println("Displaying all students:");
                        studentDao.getAllStudents().forEach(System.out::println);
                        break;

                    case 3: // Get details of a single student
                        System.out.print("Enter student ID: ");
                        int searchId = Integer.parseInt(br.readLine());
                        Student foundStudent = studentDao.getStudent(searchId);
                        if (foundStudent != null) {
                            System.out.println("Student details: " + foundStudent);
                        } else {
                            System.out.println("No student found with ID: " + searchId);
                        }
                        break;

                    case 4: // Delete a student
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = Integer.parseInt(br.readLine());
                        studentDao.deleteStudent(deleteId);
                        System.out.println("Student deleted successfully.");
                        break;

                    case 5: // Update a student
                        System.out.print("Enter student ID to update: ");
                        int updateId = Integer.parseInt(br.readLine());
                        System.out.print("Enter new student name: ");
                        String newName = br.readLine();
                        System.out.print("Enter new student city: ");
                        String newCity = br.readLine();

                        Student updatedStudent = new Student(updateId, newName, newCity);
                        studentDao.updateStudent(updatedStudent);
                        System.out.println("Student updated successfully.");
                        break;

                    case 6: // Exit
                        go = false;
                        System.out.println("Exiting. Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
