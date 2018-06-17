/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Student;
import java.util.ArrayList;

/**
 *
 * @author Quang Hiep
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Student> student = new ArrayList<>();
        Validation validation = new Validation();
        // loop until user want to exit program
        while (true) {
            int choice = StudentManager.menu();
            switch (choice) {
                case 1:
                    StudentManager.createStudent(student);
                    break;
                case 2:
                    StudentManager.findAndSort(student);
                    break;
                case 3:
                    StudentManager.updateOrDelete(student);
                    break;
                case 4:
                    StudentManager.report(student);
                    break;
                case 5:
                    return;
            }
        }
    }
}
