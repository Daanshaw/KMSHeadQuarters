import java.sql.SQLException;
import java.util.Scanner;

public class PresentationTier {
    void presentationTier() throws Exception {

        ApplicationTier ap = new ApplicationTier();

            // UI

        while (true) {
            System.out.println("Welcome to KWIK MEDICAL SYSTEM");
            System.out.println("Choose form one of the following options:");
            System.out.println("");
            System.out.println("1. Enter patient's info into the database.");
            System.out.println("2. Exit program.");

            //moves to application tier

            ap.applicationTier();




        }
    }
    void patientExistMessage(){
        System.out.println("Patient exist in the database! Try again!");

    }
    void enterName(){

        System.out.println("Enter patient's name:");
    }
    void enterSurname(){

        System.out.println("Enter patient's surname:");
    }
    void enterNhsNo(){

        System.out.println("Enter patient's NHS registration number:");
    }
    void enterAddress(){

        System.out.println("Enter patient's address:");
    }
    void enterMedicalCond(){

        System.out.println("Enter patient's medical condition:");
    }
}
