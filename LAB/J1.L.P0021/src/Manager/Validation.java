/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Report;
import Entity.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Quang Hiep
 */
public class Validation {

    public static final Scanner src = new Scanner(System.in);

    //check user input number in range 1 to 5
    public static int checkInputLimit(int min, int max) {
        //loop until user enter true input
        while (true) {
            try {
                int result = Integer.parseInt(src.nextLine());
                if (result < min || result > max) {
                    throw new Exception();
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Please input number in range [" + min + "," + max + " ]");
            }
        }
    }

    //check user input yes/ no
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return true if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
        }
    }

    //check user input string
    public static String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = src.nextLine().trim();
            if (result.length() == 0) {
                System.err.println("Wrong input");
            } else {
                return result;
            }
        }
    }

    //check id exist
    public static boolean checkIdExist(ArrayList<Student> student, String id, String name) {
        for (Student student1 : student) {
            if (id.equalsIgnoreCase(student1.getId()) && !name.equalsIgnoreCase(student1.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkStudentExist(ArrayList<Student> student, String id, String name, String semester, String course) {
        for (Student student1 : student) {
            if (id.equalsIgnoreCase(student1.getId())
                    && name.equalsIgnoreCase(student1.getStudentName())
                    && semester.equalsIgnoreCase(student1.getSemester())
                    && course.equalsIgnoreCase(student1.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkInputUD() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
        }
    }

    //check report exist
    public static boolean checkReportExist(ArrayList<Report> report, String studentName, String courseName, int total) {
        for (Report report1 : report) {
            if (studentName.equalsIgnoreCase(report1.getStudentName())
                    && courseName.equalsIgnoreCase(report1.getCourseName())
                    && total == report1.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    //check user input course
    public static String checkInputCourse() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
        }
    }
}
