/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P0068;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Quang Hiep
 */
public class Main {

    private static final Scanner src = new Scanner(System.in);

    // check input String name
    private static String checkInputString() {
        // loop until user enter true input
        while (true) {
            String result = src.nextLine();
            if (result.trim().length() == 0) {
                System.err.println("Not Empty");
            } else {
                return result;
            }
        }
    }

    // check input mark
    private static float checkInputMark() {
        // loop until user enter true mark
        while (true) {
            try {
                float result = Float.parseFloat(src.nextLine());
                if (result > 10 || result < 0) {
                    throw new NumberFormatException();
                } else {
                    return result;
                }
            } catch (NumberFormatException ex) {
                System.err.println("Re-input");
            }

        }
    }

    // check user enter yes no
    private static boolean checkInputYN() {
        // loop until user enter Y or N
        while (true) {
            String result = src.nextLine();
            if (result.length() == 1 && result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.length() == 1 && result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Re-input.");
        }
    }

    // add student to arraylist
    private static void addStudent(ArrayList<Student> student) {
        System.out.println("====== Collection Sort Program ======");
        System.out.println("Please input student information");
        System.out.println("Name: ");
        String name = checkInputString();
        System.out.println("Classes: ");
        String classes = checkInputString();
        System.out.println("Mark: ");
        Float mark = checkInputMark();
        student.add(new Student(name, mark, classes));
    }

    // show list students after sort
    private static void show(ArrayList<Student> student) {
        // check empty list
        if (student.isEmpty()) {
            System.err.println("Empty List");
        }
        Collections.sort(student);
        for (int i = 0; i < student.size(); i++) {
            System.out.println("--------Student " + i + 1 + "--------");
            System.out.println("Name: " + student.get(i).getName());
            System.out.println("Classes: " + student.get(i).getClasses());
            System.out.println("Mark: " + student.get(i).getMark());
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> student = new ArrayList<>();
        addStudent(student);
        while (true) {
            System.out.println("Do you want to enter more student information? (Y/N)");
            if (checkInputYN()) {
                addStudent(student);
            } else {
                break;
            }
        }
        show(student);
    }
}
