/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Report;
import Entity.Student;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Quang Hiep
 */
public class StudentManager {

    //Show menu
    public static int menu() {
        System.out.println("1. Create");
        System.out.println("2. Find and sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputLimit(1, 5);
        return choice;
    }
    //Allow user create new student

    public static void createStudent(ArrayList<Student> student) {
        //if number of students greater than 10 ask user continue or not 
        if (student.size() >= 3) {
            System.out.println("Do you want to continue(Y/N): ");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
        //loop until user input not duplicate
        while (true) {
            System.out.print("Enter ID: ");
            String id = Validation.checkInputString();
            System.out.print("Enter name student: ");
            String name = Validation.checkInputString();
            if (!Validation.checkIdExist(student, id, name)) {
                System.err.println("Id has exist in student list. "
                        + "Please enter true name with that's id or create new id");
                continue;
            }
            System.out.print("Enter semester: ");
            String semester = Validation.checkInputString();
            System.out.print("Enter name course: ");
            String course = Validation.checkInputCourse();
            //check student exist or not
            if (Validation.checkStudentExist(student, id, name, semester, course)) {
                student.add(new Student(id, name, semester, course));
                return;
            }
            System.out.println("Duplicate");
        }
    }

    public static void findAndSort(ArrayList<Student> student) {
        //check list empty
        if (student.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Student> listStudentFindByName = listStudentFindByName(student);
//        System.out.println("Enter name to searching: ");
//        String name = Validation.checkInputString();
//        for (Student student1 : listStudentFindByName) {
//            if (student1.getStudentName().contains(name)) {
//                listStudentFindByName.add(student1);
//            }
//        }
        if (listStudentFindByName.isEmpty()) {
            System.out.println("Student doesn't exist");
        } else {
            Collections.sort(listStudentFindByName);
            System.out.printf("%-15s%-15s%-15s", "Student Name", "semester", "Course Name");
            //loop from first to last element of list student
            for (Student student1 : listStudentFindByName) {
                student1.print();
            }
        }
    }

    public static ArrayList<Student> listStudentFindByName(ArrayList<Student> student) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.print("Enter name to search: ");
        String name = Validation.checkInputString();
        for (Student student1 : student) {
            //check student have name contains input
            if (student1.getStudentName().contains(name)) {
                listStudentFindByName.add(student1);
            }
        }
        return listStudentFindByName;
    }

    public static void updateOrDelete(ArrayList<Student> student) {
        //check list empty
        if (student.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter id: ");
        String searchId = Validation.checkInputString();
        ArrayList<Student> listStudentFindByID = new ArrayList<>();
        for (Student student1 : student) {
            if (searchId.equalsIgnoreCase(student1.getId())) {
                listStudentFindByID.add(student1);
            }
        }
        //checklistempty
        if (listStudentFindByID.isEmpty()) {
            System.err.println("Not found student");
            return;
        } else {
            System.out.println("List student found: ");
            int count = 1;
            System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                    "semester", "Course Name");
            //display list student found
            for (Student student1 : listStudentFindByID) {
                System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                        student1.getStudentName(), student1.getSemester(),
                        student1.getCourseName());
                count++;

            }
            System.out.print("Enter student: ");
            int choice = Validation.checkInputLimit(1, listStudentFindByID.size());
            Student chooseStudent = listStudentFindByID.get(choice - 1);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            //check user want to update or delete
            if (Validation.checkInputUD()) {
                System.out.print("Enter id student: ");
                String id = Validation.checkInputString();
                System.out.print("Enter name student: ");
                String name = Validation.checkInputString();
                System.out.print("Enter semester: ");
                String semester = Validation.checkInputString();
                System.out.print("Enter name course: ");
                String course = Validation.checkInputString();
                //check student exist or not
                if (Validation.checkStudentExist(student, id, name, semester, course)) {
                    chooseStudent.setId(id);
                    chooseStudent.setStudentName(name);
                    chooseStudent.setSemester(semester);
                    chooseStudent.setCourseName(course);
                    System.out.println("Update success");
                } else {
                    System.out.println("Duplicate");
                    return;
                }
            } else {
                student.remove(chooseStudent);
                System.out.println("Delete success");
                return;
            }
        }
    }

    public static void report(ArrayList<Student> student) {
        if (student.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> report = new ArrayList<>();
        for (int i = 0; i < student.size(); i++) {

            for (Student student1 : student) {
                String id = student1.getId();
                int total = 0;
                for (Student student2 : student) {
                    if (id.equalsIgnoreCase(student2.getId())
                            && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                        total++;
                    }
                }
                if (Validation.checkReportExist(report, student1.getStudentName(), student1.getCourseName(),
                        total)) {
                    report.add(new Report(student1.getStudentName(), student1.getCourseName(), total));
                }
            }
        }
        // print Report
        for (int i = 0; i < report.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", report.get(i).getStudentName(),
                    report.get(i).getCourseName(), report.get(i).getTotalCourse());
        }
    }

}
