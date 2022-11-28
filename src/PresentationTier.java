import java.sql.SQLException;
import java.util.Scanner;

public class PresentationTier {
    void presentationTier() throws Exception {

        ApplicationTier ap = new ApplicationTier();

            // UI

        while (true) {
            System.out.println("Welcome to KWIK MEDICAL SYSTEM");
            System.out.println("Choose from one of the following options:");
            System.out.println("");
            System.out.println("1. Enter patient's info into the database.");
            System.out.println("2. Exit program.");

            //moves to application tier

            ap.applicationTier();




        }
    }
    void patientExistMsg(){
        System.out.println("Patient exist in the database! Try again!");

    }
    void enterNameMsg(){

        System.out.println("Enter patient's name:");
    }
    void enterSurnameMsg(){

        System.out.println("Enter patient's surname:");
    }
    void enterNhsNoMsg(){

        System.out.println("Enter patient's NHS registration number:");
    }
    void enterAddressMsg(){

        System.out.println("Enter patient's address:");
    }
    void enterMedicalCondMsg(){

        System.out.println("Enter patient's medical condition:");
    }
    void sendingInfoMsg(){

        System.out.println("Sending patient's info...");
    }
    void dataSendSuccessfullyMsg(){

        System.out.println("Sending patient's info...");
    }
}
